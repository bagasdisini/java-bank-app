import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Customer extends User {
    private int cardNumber;
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory = new ArrayList<>();

    public Customer(String name, String username, String password, int pin) {
        super(name, username, password, "Customer");
        this.cardNumber = (int) (Math.random() * 1_000_000_000);
        this.pin = pin;
        this.balance = 0.0;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.\n");
            return;
        }

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        this.balance += amount;
        addTransaction("Deposit of " + amount + " at" + dtf.format(now));
        System.out.println("Deposit successful! Your new balance is " + this.balance + "\n");
    }

    public void withdrawal(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.\n");
            return;
        }

        if (this.balance < amount) {
            System.out.println("Insufficient balance. Please try again.\n");
            return;
        }

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.balance -= amount;

        addTransaction("Withdrawal of " + amount + " at" + dtf.format(now));
        System.out.println("Withdrawal successful! Your new balance is " + this.balance + "\n");
    }

    public void transfer(Customer receiver, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.\n");
            return;
        }

        if (this.balance < amount) {
            System.out.println("Insufficient balance. Please try again.\n");
            return;
        }

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.balance -= amount;
        receiver.balance += amount;

        addTransaction("Transfer of " + amount + " to card number " + receiver.cardNumber + " at" + dtf.format(now));
        receiver.addTransaction("Received transfer of " + amount + " from card number " + this.cardNumber + " at" + dtf.format(now));
        System.out.println("Transfer successful! Your new balance is " + this.balance + "\n");
    }

    public void showTransactionHistory() {
        System.out.println("Transaction history:");
        for (String transaction : this.transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("*****************************");
    }
}