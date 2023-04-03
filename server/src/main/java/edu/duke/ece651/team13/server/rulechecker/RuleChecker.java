package edu.duke.ece651.team13.server.rulechecker;

import edu.duke.ece651.team13.server.entity.OrderEntity;
import edu.duke.ece651.team13.server.order.Order;

public abstract class RuleChecker {
    private final RuleChecker next;

    public RuleChecker(RuleChecker next){
        this.next = next;
    }

    /**
     * Use parameter polymorphism to check on different types of orders
     * TODO: changed to pass in PlayerOrder, parametric polymorphism may not work here
     */
    protected abstract String checkMyRule(Order order);
//    protected abstract String checkMyRule(AttackOrder order);

    /**
     * Handle chaining rules, Subclasses will generally NOT override
     * this method.
     *
     * @return null if the placement is OK
     *         any non-null String: a description of what is wrong, suitable to show the user
     */
    public String checkOrder(Order order){
        //if we fail our own rule: stop the placement is not legal
        String res = checkMyRule(order);
        if (res != null) {
            return res;
        }
        //otherwise, ask the rest of the chain.
        if (next != null) {
            return next.checkOrder(order);
        }
        //if there are no more rules, then the placement is legal
        return null;
    }

    protected abstract String checkMyRule(OrderEntity order);
}
