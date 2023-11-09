package model;

import java.util.function.Predicate;

public class Bet {
    private final int price;
    private final Predicate<Pair<Integer, Integer>> condition;
    private final int number;
    private final String name;
    private final int multiply;

    public Bet(int price, Predicate<Pair<Integer, Integer>> condition, int number, String name, int multiply) {
        this.price = price;
        this.condition = condition;
        this.number = number;
        this.name = name;
        this.multiply = multiply;
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

    public String getName() {
        return name;
    }

    public int getMultiply() {
        return multiply;
    }
}
