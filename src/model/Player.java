package model;

import util.BetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Player {
    private final String name;
    private int amount;

    public Player(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Bet makeBet(int maxBet) {
        Random random = new Random();
        int price = random.nextInt(maxBet);
        if (price > amount) {
            price = amount;
        }

        List<Predicate<Pair<Integer, Integer>>> predicates = new ArrayList<>(BetService.betConditions.keySet());
        int betNum = random.nextInt(predicates.size());
        Predicate<Pair<Integer, Integer>> predicate = predicates.get(betNum);

        int number = random.nextInt(36);

        return new Bet(price, predicate, number);
    }

    public int decreaseAmount(int sum) {
        amount -= sum;
        return amount;
    }

    public int increaseAmount(int sum) {
        amount += sum;
        return amount;
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
}
