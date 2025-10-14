package lab3.t2;

public class StoredValueCard extends Card {

    public static final double FARE_PRICE = 8.00;
    private double balance;

    public StoredValueCard(double initialBalance) {
        super("Звичайна");
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean isValid() {

        return balance >= FARE_PRICE;
    }
    public void recharge(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    @Override
    public void use() {
        if (isValid()){
            balance-=FARE_PRICE;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", баланс: " + String.format("%.2f", balance) + " грн";
    }

}
