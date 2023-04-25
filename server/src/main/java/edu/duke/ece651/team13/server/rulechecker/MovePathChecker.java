package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryConnectionEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import edu.duke.ece651.team13.server.util.GraphUtil;

import java.util.stream.Collectors;

/**
 * Checks if there is a valid path between the source and
 * destination territories in a move order.
 */
public class MovePathChecker extends RuleChecker {
    public MovePathChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order, PlayerEntity player) throws IllegalArgumentException {
        if (order.getUnitType().equals(UnitMappingEnum.SPY) && isInEnemyTerritory(order.getSource(), order.getDestination(), order.getPlayer())) {
            // Spy units have special move rules: in enemy territories, can only move to one adjacent territory at a time
            if (!isAdjacentToTerritory(order.getSource(), order.getDestination())) {
                throw new IllegalArgumentException("Invalid move of spy: In enemy territories, can only move to adjacent territories.");
            }
        }
        if (!GraphUtil.hasPath(order.getSource(), order.getDestination())) {
            throw new IllegalArgumentException("Invalid move order: There is not a valid path between the src and dst.");
        }
    }

    /**
     * Checks if a unit is in enemy territory by checking if either of the source/destination's
     * owner is an enemy
     */
    private boolean isInEnemyTerritory(TerritoryEntity source, TerritoryEntity destination, PlayerEntity player){
        return (source.getOwner() != player) || (destination.getOwner() != player);
    }

    /**
     * Checks if the destination territory is a neighbor to the source territory
     *
     * @param source      the source territory
     * @param destination the destination territory
     * @return true if it is a valid path and false otherwise
     */
    private boolean isAdjacentToTerritory(TerritoryEntity source, TerritoryEntity destination) {

        for (TerritoryEntity neighbor : source.getConnections().stream().map(TerritoryConnectionEntity::getDestinationTerritory).collect(Collectors.toList())) {
            if (destination == neighbor) {
                return true;
            }
        }
        return false;
    }
}
