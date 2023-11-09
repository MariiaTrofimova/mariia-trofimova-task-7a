package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
        int price = ThreadLocalRandom.current().nextInt(maxBet);
        if (price > amount) {
            price = amount;
        }

        List<Predicate<Pair<Integer, Integer>>> predicates = new ArrayList<>(Roulette.betConditions.keySet());
        int betNum = ThreadLocalRandom.current().nextInt(predicates.size());
        Predicate<Pair<Integer, Integer>> predicate = predicates.get(betNum);
        Pair<Integer, String> pair = Roulette.betConditions.get(predicate);
        String name = pair.getSecond();
        int multiply = pair.getFirst();
        int number = ThreadLocalRandom.current().nextInt(0, Roulette.MAX_NUMBER + 1);

        return new Bet(price, predicate, number, name, multiply);
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
