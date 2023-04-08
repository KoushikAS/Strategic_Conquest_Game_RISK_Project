package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.util.Dice;
import edu.duke.ece651.team13.server.entity.AttackerEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.TerritoryEntity;
import edu.duke.ece651.team13.server.entity.UnitEntity;
import edu.duke.ece651.team13.server.enums.UnitMappingEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class CombatResolutionServiceImpl implements CombatResolutionService {

    @Autowired
    public final AttackerService attackerService;

    @Autowired
    public final TerritoryService territoryService;

    @Autowired
    public final UnitService unitService;

    @Autowired
    private final Dice dice;

    //Making public to test
    public void reduceUnit(List<MutablePair<UnitMappingEnum, Integer>> units, UnitMappingEnum unitType) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getLeft().equals(unitType)) {
                int updatedValue = units.get(i).getRight() - 1;
                if (updatedValue <= 0) {
                    units.remove(i);
                } else {
                    units.get(i).setRight(updatedValue);
                }
                return;
            }
        }
    }

    //Making public to test
    public void fight(List<MutablePair<UnitMappingEnum, Integer>> attacker, List<MutablePair<UnitMappingEnum, Integer>> defender, Boolean isAttackerTerritoryOwner, Boolean isDefenderTerritoryOwner) {

        UnitMappingEnum attackerUnitType = attacker.stream()
                .map(MutablePair::getLeft)
                .max((Comparator.comparing(UnitMappingEnum::getLevel)))
                .orElseThrow(NoSuchElementException::new);

        UnitMappingEnum defenderUnitType = defender.stream()
                .map(MutablePair::getLeft)
                .min((Comparator.comparing(UnitMappingEnum::getLevel)))
                .orElseThrow(NoSuchElementException::new);

        while (true) {
            int attackerScore = dice.roll() + attackerUnitType.getBonus();
            int defenderScore = dice.roll() + defenderUnitType.getBonus();

            if (attackerScore > defenderScore) {
                //defender lost
                reduceUnit(defender, defenderUnitType);
                return;
            }

            if (attackerScore < defenderScore) {
                //attacker lost
                reduceUnit(attacker, attackerUnitType);
                return;
            }

            //Tie is Scored. Hence, Advantage goes to Territory Owner

            if (isAttackerTerritoryOwner) {
                //defender lost
                reduceUnit(defender, defenderUnitType);
                return;
            }

            if (isDefenderTerritoryOwner) {
                //attacker lost
                reduceUnit(attacker, attackerUnitType);
                return;
            }
        }
    }


    //Making public to test
    public Map<PlayerEntity, List<MutablePair<UnitMappingEnum, Integer>>> getWarParties(List<AttackerEntity> attackers, TerritoryEntity territory) {
        Map<PlayerEntity, List<MutablePair<UnitMappingEnum, Integer>>> warParties = new HashMap<>();

        //Add all attackers to the war party
        for (AttackerEntity attacker : attackers) {
            PlayerEntity player = attacker.getAttacker();
            if (!warParties.containsKey(player)) {
                warParties.put(player, new ArrayList<>());
            }
            warParties.get(player).add(new MutablePair<UnitMappingEnum, Integer>(attacker.getUnitType(), attacker.getUnits()));
        }

        //Adding the defender to the war party
        PlayerEntity defender = territory.getOwner();
        warParties.put(defender, new ArrayList<>());
        for (UnitEntity unit : territory.getUnits()) {
            warParties.get(defender).add(new MutablePair<UnitMappingEnum, Integer>(unit.getUnitType(), unit.getUnits()));
        }

        return warParties;
    }

    //Making public to test
    public Map.Entry<PlayerEntity, List<MutablePair<UnitMappingEnum, Integer>>> resolveWinner(TerritoryEntity territory, List<AttackerEntity> attackers) {
        Map<PlayerEntity, List<MutablePair<UnitMappingEnum, Integer>>> warPartyMap = getWarParties(attackers, territory);

        List<PlayerEntity> warParties = new ArrayList<>(warPartyMap.keySet());

        int i = 0;
        int j = 1;

        while (warParties.size() > 1) {

            PlayerEntity attacker = warParties.get(i);
            PlayerEntity defender = warParties.get(j);

            Boolean isAttackerTerritoryOwner = territory.getOwner().equals(attacker);
            Boolean isDefenderTerritoryOwner = territory.getOwner().equals(defender);

            fight(warPartyMap.get(attacker), warPartyMap.get(defender), isAttackerTerritoryOwner, isDefenderTerritoryOwner);

            if (warPartyMap.get(attacker).size() <= 0) {
                warParties.remove(i);
            }

            if (warPartyMap.get(defender).size() <= 0) {
                warParties.remove(j);
            }

            i = (i + 1) % warParties.size();
            j = (j + 1) % warParties.size();
        }

        for (Map.Entry<PlayerEntity, List<MutablePair<UnitMappingEnum, Integer>>> entry : warPartyMap.entrySet()) {
            if (warParties.contains(entry.getKey())) {
                return entry;
            }
        }

        throw new NoSuchElementException();
    }

    @Override
    public void resolveCombot(TerritoryEntity territory) {
        List<AttackerEntity> attackers = attackerService.getAttackers(territory);
        //No attackers for this territory
        if (attackers.size() == 0) {
            return;
        }

        Map.Entry<PlayerEntity, List<MutablePair<UnitMappingEnum, Integer>>> winner = resolveWinner(territory, attackers);

        Map<UnitMappingEnum, Integer> unitMapping = winner.getValue().stream().collect(Collectors.toMap(MutablePair::getLeft, MutablePair::getRight));
        territory.setOwner(winner.getKey());
        List<UnitEntity> unitEntities = new ArrayList<>();
        for (UnitEntity unit : territory.getUnits()) {
            unit.setUnits(unitMapping.get(unit.getUnitType()));
            unitEntities.add(unit);
        }

        territoryService.updateTerritoryOwner(territory, winner.getKey());
        territoryService.updateTerritoryUnits(territory, unitEntities);

        attackerService.clearAttackers(territory);
    }
}
