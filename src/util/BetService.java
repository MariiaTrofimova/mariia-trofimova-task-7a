package util;

import model.Pair;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BetService {
    private static final List<Integer> red = List.of(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);

    public static final Map<Predicate<Pair<Integer, Integer>>, Pair<Integer, String>> betConditions = Map.of(
            pair -> pair.getSecond() % 2 == 0, new Pair<>(2, "even"),
            pair -> pair.getSecond() % 2 == 1, new Pair<>(2, "odd"),
            pair -> red.contains(pair.getSecond()), new Pair<>(2, "red"),
            pair -> !red.contains(pair.getSecond()), new Pair<>(2, "black"),
            pair -> pair.getSecond() < 13, new Pair<>(3, "1st12"),
            pair -> pair.getSecond() > 12 && pair.getSecond() < 25, new Pair<>(3, "2nd12"),
            pair -> pair.getSecond() > 24, new Pair<>(3, "3rd12"),
            pair -> pair.getSecond() < 19, new Pair<>(2, "1to18"),
            pair -> pair.getSecond() > 18, new Pair<>(2, "19to36"),
            pair -> pair.getSecond() == pair.getFirst(), new Pair<>(35, "number")
    );

}
