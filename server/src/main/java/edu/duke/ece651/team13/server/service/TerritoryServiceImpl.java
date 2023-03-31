//package edu.duke.ece651.team13.server.service;
//
//import edu.duke.ece651.team13.server.entity.PlayerEntity;
//import edu.duke.ece651.team13.server.entity.TerritoryEntity;
//import edu.duke.ece651.team13.server.repository.PlayerRepository;
//import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
//import edu.duke.ece651.team13.shared.player.PlayerRO;
//import edu.duke.ece651.team13.shared.territory.Territory;
//import edu.duke.ece651.team13.shared.territory.TerritoryRO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class TerritoryServiceImpl implements TerritoryService {
//
//    @Autowired
//    private final PlayerRepository repository;
//
//    /**
//     * Set the unit number
//     * precondition: newUnitNum must be >= 0, if not, throws
//     * IllegalArgumentException
//     *
//     * @param newUnitNum is the new unitNum
//     */
//    @Override
//    public void setUnitNum(int newUnitNum) {
//        if (newUnitNum < 0)
//            throw new IllegalArgumentException("The unit number in a territory should be >= 0.");
//        this.unitNum = newUnitNum;
//    }
//
//    /**
//     * Add an attacker to the attacker list of this turn
//     *
//     * @param attacker      is the attacking player
//     * @param attackUnitNum is the number of attacking units
//     */
//    @Override
//    public void addAttacker(PlayerRO attacker, int attackUnitNum) {
//        if (attackers.containsKey(attacker)) {
//            attackers.put(attacker, attackers.get(attacker) + attackUnitNum);
//        } else {
//            attackers.put(attacker, attackUnitNum);
//        }
//    }
//
//    /**
//     * Remove all the attackers
//     */
//    @Override
//    public void clearAttackers() {
//        attackers.clear();
//    }
//
//    /**
//     * Get the attackers
//     *
//     * @return the map of attacking players to the attacking unit numbers
//     */
//    @Override
//    public HashMap<PlayerRO, Integer> getAttackers() {
//        return attackers;
//    }
//
//    /**
//     * Get the iterator of neighbours
//     */
//    @Override
//    public Iterator<TerritoryRO> getNeighbourIterartor() {
//        return neighbours.iterator();
//    }
//
//    /**
//     * Add Neighbouring terrtiory to the current Territory if it is not added
//     * already or if the neighbour is not the current territory
//     */
//    @Override
//    public void addNeighbours(TerritoryRO neighbour) {
//        if (!this.equals(neighbour) && !neighbours.contains(neighbour)) {
//            neighbours.add(neighbour);
//        }
//    }
//
//    @Override
//    public Territory replicate() {
//        return new TerritoryEntity(this);
//    }
//}
