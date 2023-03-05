package edu.duke.ece651.team13.shared;

public class RiscGameBoard <T> implements Board<T>{
    private final Map map;

    @Override
    public Map getMap() {
        return map;
    }

    public RiscGameBoard(){
        // TODO: How do we keep the initial unit settings (stored in board class or?)
        map = new V1Map(10);
    }
}
