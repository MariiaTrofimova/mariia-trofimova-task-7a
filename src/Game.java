import model.*;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private static final int MAX_AMOUNT_PLAYER = 1000;
    private static final int MAX_AMOUNT_CASINO = 1000_000;
    private static final int MAX_BET = 100;

    private static Player player;
    private static Casino casino;
    private static Roulette roulette;

    public static void main(String[] args) {
        startGame();

        int i = 0;
        while (i < 100) {
            i++;
            Bet bet = makeBet(i);

            int number = roulette.start();
            System.out.printf("| The Number is %d  | ", number);

            Pair<Integer, Integer> pair = new Pair<>(bet.getNumber(), number);

            if (bet.getCondition().test(pair)) {
                payToPlayer(bet);
            } else {
                payToCasino(bet);
            }
            if (casino.isBankrupt() || player.isBankrupt()) {
                break;
            }
        }
        printResult(i);
    }

    private static void startGame() {
        player = new Player("Lucky", ThreadLocalRandom.current().nextInt(MAX_AMOUNT_PLAYER));
        casino = new Casino(ThreadLocalRandom.current().nextInt(MAX_AMOUNT_CASINO));
        roulette = new Roulette();

        System.out.println("*************** Starting The Game ***************");
        System.out.printf("Player %s have $%d. Casino have $%d%n",
                player.getName(), player.getAmount(), casino.getAmount());
    }

    private static Bet makeBet(int i) {
        Bet bet = player.makeBet(MAX_BET);

        int price = bet.getPrice();
        String betName = bet.getName();

        System.out.printf("%d: %d on %s ", i, price, betName);
        if (betName.equals("number")) {
            System.out.printf("%d ", bet.getNumber());
        }
        return bet;
    }

    private static void payToPlayer(Bet bet) {
        int multiply = bet.getMultiply();
        int sum = bet.getPrice() * (multiply - 1);
        casino.decreaseAmount(sum);
        player.increaseAmount(sum);
        System.out.printf("You won %d!%n", sum);
    }

    private static void payToCasino(Bet bet) {
        int price = bet.getPrice();
        casino.increaseAmount(price);
        player.decreaseAmount(price);
        System.out.printf("You lost %d!%n", price);
    }

    private static void printResult(int bets) {
        System.out.println();
        System.out.println("******************** Results ********************");

        System.out.printf("The game lasted %d bets.%n", bets);

        if (player.getAmount() > player.getInitAmount()) {
            System.out.println("Congratulations, you cheated the casino");
            System.out.printf("You won %d%n", player.getAmount() - player.getInitAmount());
        } else if (player.getAmount() == player.getInitAmount()) {
            System.out.println("Congratulations, you saved your money");
        } else {
            System.out.println("Sorry, the casino cheated you");
            System.out.printf("You lost %d%n", player.getInitAmount() - player.getAmount());
        }
        System.out.printf("Player have $%d. Casino have $%d%n", player.getAmount(), casino.getAmount());
        System.out.println("******************* Game Over *******************");
    }
}