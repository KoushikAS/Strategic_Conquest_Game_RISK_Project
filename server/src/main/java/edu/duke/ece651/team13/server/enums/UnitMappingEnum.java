package edu.duke.ece651.team13.server.enums;

public enum UnitMappingEnum {
    LEVEL0(0, 0, 0, "Basic"),
    LEVEL1(1, 1, 3, "Infantry"),
    LEVEL2(2, 3, 11, "Cavalry"),
    LEVEL3(3, 5, 30, "Artillery"),
    LEVEL4(4, 8, 55, "Army Aviation"),
    LEVEL5(5, 11, 90, "Special Forces"),
    LEVEL6(6, 15, 140, "Combat Engineer");

    private final int level;
    private final int bonus;
    private final int cost; //the cost to upgrade unit from level0 to the target level
    private final String type;

    UnitMappingEnum(int level, int bonus, int cost, String type){
        this.level = level;
        this.bonus = bonus;
        this.cost = cost;
        this.type = type;
    }

    public int getLevel(){
        return this.level;
    }

    public int getBonus(){
        return this.bonus;
    }

    public int getCost(){
        return this.cost;
    }

    public String getType(){
        return this.type;
    }
}
