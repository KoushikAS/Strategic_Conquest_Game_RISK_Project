package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;

import java.util.Optional;

/**
 * Check if the source territory's unit number is valid after executing the order
 */
public class MoveUnitNumChecker extends RuleChecker{
    public MoveUnitNumChecker(RuleChecker next) {
        super(next);
    }

    @Override
    protected void checkMyRule(OrderEntity order) throws IllegalArgumentException {
        // TODO: Check the num for each type of units
        int sourceUnitNum = getUnitEntityHelper(order.getSource(), UnitMappingEnum.LEVEL0).getUnits();
        long moveUnitNum = order.getUnitNum();
        if(sourceUnitNum < moveUnitNum) {
            throw new IllegalArgumentException("Invalid move order: Don't have sufficient unit number in the territory.");
        }
        else if(moveUnitNum < 0){
            throw new IllegalArgumentException( "Invalid move order: The unit number to move should be >= 0.");
        }
    }

    /**
     * This is a helper function that gets the UnitEntity with the specified unit type in the
     * specified territory
     *
     * @return the UnitEntity of the unitType
     */
    public static UnitEntity getUnitEntityHelper(TerritoryEntity territory, UnitMappingEnum unitType){
        Optional<UnitEntity> unit = territory.getUnits().
                stream().
                filter(t -> t.getUnitType().equals(unitType)).findAny();
        if(!unit.isPresent()){
            throw new IllegalArgumentException("The territory " + territory.getName() + " does not have" +
                    unitType + " type of unit.");
        }
        return unit.get();
    }
}
