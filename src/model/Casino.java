package model;

public class Casino {
    int amount;

    public Casino(int amount) {
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }
}
