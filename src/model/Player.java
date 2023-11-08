package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Player {
    private final String name;
    private int amount;
    private final int initAmount;

    public Player(String name, int amount) {
        this.name = name;
        this.amount = amount;
        initAmount = amount;
    }

    public Bet makeBet(int maxBet) {
        Random random = new Random();
        int price = random.nextInt(maxBet);
        if (price > amount) {
            price = amount;
        }

        List<Predicate<Pair<Integer, Integer>>> predicates = new ArrayList<>(Roulette.betConditions.keySet());
        int betNum = random.nextInt(predicates.size());
        Predicate<Pair<Integer, Integer>> predicate = predicates.get(betNum);

        int number = random.nextInt(36);

        return new Bet(price, predicate, number);
    }

    public void decreaseAmount(int sum) {
        amount -= sum;
    }

    public void increaseAmount(int sum) {
        amount += sum;
    }

    public boolean isBankrupt() {
        return amount <= 0;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getInitAmount() {
        return initAmount;
    }
}
