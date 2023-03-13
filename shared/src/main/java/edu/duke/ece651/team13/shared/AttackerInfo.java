package edu.duke.ece651.team13.shared;

/**
 * This class encapsulates the information of an attacker or
 * defender's identity and their attacking unit number
 */
public class AttackerInfo {
    private final Player attacker;
    private int unitNum;

    public AttackerInfo(Player attacker, int unitNum) {
        if(unitNum < 0) throw new IllegalArgumentException("Unit number cannot be less than 0.");
        this.attacker = attacker;
        this.unitNum = unitNum;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public Player getAttacker(){
        return attacker;
    }

    public void setUnitNum(int unitNum){
        if(unitNum < 0) throw new IllegalArgumentException("Unit number cannot be less than 0.");
        this.unitNum = unitNum;
    }

}
