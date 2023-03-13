package edu.duke.ece651.team13.shared;

/**
 * This class encapsulates the information of an attacker's
 * identity and their attacking unit number
 */
public class AttackerInfo {
    private final Player attacker;
    private final int unitNum;

    public AttackerInfo(Player attacker, int unitNum) {
        this.attacker = attacker;
        this.unitNum = unitNum;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public Player getAttacker(){
        return attacker;
    }


}
