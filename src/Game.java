import model.*;
import util.BetService;

import java.util.Random;

public class Game {
    private static final int MAX_AMOUNT_PLAYER = 1000;
    private static final int MAX_AMOUNT_CASINO = 1000_000;
    private static final int MAX_BET = 100;

    private static int playerInitAmount;

    public static void main(String[] args) {
        Random random = new Random();
        Player player = new Player("Lucky", random.nextInt(MAX_AMOUNT_PLAYER));
        Casino casino = new Casino(random.nextInt(MAX_AMOUNT_CASINO));
        Roulette roulette = new Roulette();
        playerInitAmount = player.getAmount();
        System.out.println("*************** Starting The Game ***************");
        System.out.printf("Player %s have $%d. Casino have $%d%n",
                player.getName(), player.getAmount(), casino.getAmount());
        int i = 0;
        while (i < 100) {
            Bet bet = player.makeBet(MAX_BET);
            int price = bet.getPrice();
            String betName = BetService.betConditions.get(bet.getCondition()).getSecond();
            System.out.printf("%d: %d on %s ", i + 1, price, betName);
            if (betName.equals("number")) {
                System.out.printf("%d ", bet.getNumber());
            }

            int number = roulette.start();
            System.out.printf("| The Number is %d  | ", number);

            Pair<Integer, Integer> pair = new Pair<>(bet.getNumber(), number);
            if (bet.getCondition().test(pair)) {
                int multiply = BetService.betConditions.get(bet.getCondition()).getFirst();
                int sum = price * (multiply - 1);
                casino.decreaseAmount(sum);
                player.increaseAmount(sum);
                System.out.printf("You won %d!%n", sum);
            } else {
                casino.increaseAmount(price);
                player.decreaseAmount(price);
                System.out.printf("You lost %d!%n", price);
            }
            i++;
            if (casino.isBankrupt() || player.isBankrupt()) {
                break;
            }
        }
        printResult(player, casino, i);
    }

    private static void printResult(Player player, Casino casino, int bets) {
        System.out.println();
        System.out.println("******************** Results ********************");

        System.out.printf("The game lasted %d bets.%n", bets);

        if (player.getAmount() > playerInitAmount) {
            System.out.println("Congratulations, you cheated the casino");
            System.out.printf("You won %d%n", player.getAmount() - playerInitAmount);
        } else if (player.getAmount() == playerInitAmount) {
            System.out.println("Congratulations, you saved your money");
        } else {
            System.out.println("Sorry, the casino cheated you");
            System.out.printf("You lost %d%n", playerInitAmount - player.getAmount());
        }
        System.out.printf("Player have $%d. Casino have $%d%n", player.getAmount(), casino.getAmount());
        System.out.println("******************* Game Over *******************");
    }
}
