package edu.duke.ece651.team13.shared;

import edu.duke.ece651.team13.shared.order.PlayerOrderInput;

import java.io.Serializable;
import java.util.ArrayList;

public class InputOrderList implements Serializable {

    ArrayList<PlayerOrderInput> orders;

    public InputOrderList(ArrayList<PlayerOrderInput> orders) {
        this.orders = orders;
    }

    public ArrayList<PlayerOrderInput> getOrders() {
        return orders;
    }
}
