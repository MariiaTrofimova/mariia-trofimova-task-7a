package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public class Roulette {
    private static final List<Integer> red = List.of(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
    public static final Map<Predicate<Pair<Integer, Integer>>, Pair<Integer, String>> betConditions = new HashMap<>();
    static {
        betConditions.put(pair -> pair.getSecond() % 2 == 0, new Pair<>(2, "even"));
        betConditions.put(pair -> pair.getSecond() % 2 == 1, new Pair<>(2, "odd"));
        betConditions.put(pair -> red.contains(pair.getSecond()), new Pair<>(2, "red"));
        betConditions.put(pair -> !red.contains(pair.getSecond()), new Pair<>(2, "black"));
        betConditions.put(pair -> pair.getSecond() < 13, new Pair<>(3, "1st12"));
        betConditions.put(pair -> pair.getSecond() > 12 && pair.getSecond() < 25, new Pair<>(3, "2nd12"));
        betConditions.put(pair -> pair.getSecond() > 24, new Pair<>(3, "3rd12"));
        betConditions.put(pair -> pair.getSecond() < 19, new Pair<>(2, "1to18"));
        betConditions.put(pair -> pair.getSecond() > 18, new Pair<>(2, "19to36"));
        betConditions.put(pair -> pair.getSecond().equals(pair.getFirst()), new Pair<>(35, "number"));
        betConditions.put(pair -> {
            int row = pair.getFirst() / 3;
            return row * 3 + 1 == pair.getSecond() || row * 3 + 2 == pair.getSecond() || row * 3 + 3 == pair.getSecond();
        }, new Pair<>(12, "row"));
    }

    public int start() {
        Random random = new Random();
        return random.nextInt(36);
    }
}
