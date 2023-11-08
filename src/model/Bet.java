package model;

import java.util.function.Predicate;

public class Bet {
    private final int price;
    private final Predicate<Pair<Integer, Integer>> condition;
    private final int number;

    public Bet(int price, Predicate<Pair<Integer, Integer>> condition, int number) {
        this.price = price;
        this.condition = condition;
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public Predicate<Pair<Integer, Integer>> getCondition() {
        return condition;
    }

    public int getNumber() {
        return number;
    }
}
