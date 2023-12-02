import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User {
    private final int cardNumber;
    private final int pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private boolean suspended;
    private int tries;

    public Customer(String name, String username, String password, int pin) {
        super(name, username, password, "Customer");
        this.cardNumber = (int) (Math.random() * 9_999_999);
        this.pin = pin;
        this.balance = 0.0;
        this.suspended = false;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getTries(){
        return tries;
    }

    public boolean getSuspended(){
        return suspended;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Card Number: " + cardNumber);
        System.out.println("PIN: " + pin);
        System.out.println("Balance: " + balance);
        System.out.println("IsSuspended: " + suspended);
    }

    public void addTries(){
        this.tries += 1;
    }

    public void setSuspended(){
        this.suspended = true;
    }

    public void resetTries(){
        this.tries = 0;
    }

    public void resetSuspended(){
        this.suspended = false;
    }

    public void addTransaction(String description) {
        Transaction transaction = new Transaction(description);
        transactionHistory.add(transaction);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.\n");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        int enteredPin;
        try {
            enteredPin = input.nextInt();

            while (enteredPin != this.pin) {
                if (getTries() == 9) {
                    setSuspended();
                    CustomerMenu.deposit();
                }

                System.out.println("Invalid PIN. Please try again.\n");
                addTries();

                System.out.print("Enter your PIN: ");
                enteredPin = input.nextInt();
            }
        } catch (InputMismatchException e) {
            if (getTries() == 9) {
                setSuspended();
                CustomerMenu.deposit();
            }

            System.out.println("Invalid PIN. Please try again.\n");
            addTries();
            CustomerMenu.deposit();
        }

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        this.balance += amount;
        addTransaction("(+) Deposit of " + amount + " at " + dtf.format(now));
        System.out.println("Deposit successful! Your new balance is " + this.balance + "\n");
        resetTries();
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

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        int enteredPin;
        try {
            enteredPin = input.nextInt();

            while (enteredPin != this.pin) {
                if (getTries() == 9) {
                    setSuspended();
                    CustomerMenu.withdrawal();
                }

                System.out.println("Invalid PIN. Please try again.\n");
                addTries();

                System.out.print("Enter your PIN: ");
                enteredPin = input.nextInt();
            }
        } catch (InputMismatchException e) {
            if (getTries() == 9) {
                setSuspended();
                CustomerMenu.withdrawal();
            }

            System.out.println("Invalid PIN. Please try again.\n");
            addTries();
            CustomerMenu.withdrawal();
        }

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.balance -= amount;

        addTransaction("(-) Withdrawal of " + amount + " at " + dtf.format(now));
        System.out.println("Withdrawal successful! Your new balance is " + this.balance + "\n");
        resetTries();
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

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        int enteredPin;
        try {
            enteredPin = input.nextInt();

            while (enteredPin != this.pin) {
                if (getTries() == 9) {
                    setSuspended();
                    CustomerMenu.transfer(BankApp.customers);
                }

                System.out.println("Invalid PIN. Please try again.\n");
                addTries();

                System.out.print("Enter your PIN: ");
                enteredPin = input.nextInt();
            }
        } catch (InputMismatchException e) {
            if (getTries() == 9) {
                setSuspended();
                CustomerMenu.transfer(BankApp.customers);
            }

            System.out.println("Invalid PIN. Please try again.\n");
            addTries();
            CustomerMenu.transfer(BankApp.customers);
        }

        DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.balance -= amount;
        receiver.balance += amount;

        addTransaction("(-) Transfer of " + amount + " to card number " + receiver.cardNumber + " at " + dtf.format(now));
        receiver.addTransaction("(+) Received transfer of " + amount + " from card number " + this.cardNumber + " at " + dtf.format(now));
        System.out.println("Transfer successful! Your new balance is " + this.balance + "\n");
        resetTries();
    }

    public void showTransactionHistory() {
        System.out.println("Transaction history: ");
        for (Transaction transaction : this.transactionHistory) {
            System.out.println(transaction.getDescription());
        }
        System.out.println("*****************************\n");
    }
}