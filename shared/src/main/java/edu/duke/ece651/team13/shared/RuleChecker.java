package edu.duke.ece651.team13.shared;

public abstract class RuleChecker {
    private final RuleChecker next;

    public RuleChecker(RuleChecker next){
        this.next = next;
    }

    /**
     * Use parameter polymorphism to check on different types of orders
     */
    protected abstract String checkMyRule(MoveOrder order);
//    protected abstract String checkMyRule(AttackOrder order);

    /**
     * Handle chaining rules, Subclasses will generally NOT override
     * this method.
     *
     * @return null if the placement is OK
     *         any non-null String: a description of what is wrong, suitable to show the user
     */
    public String checkOrder(PlayerOrder order){
        //if we fail our own rule: stop the placement is not legal
        String res = checkMyRule((MoveOrder) order);
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
}
