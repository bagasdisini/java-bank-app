import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu {
    static Scanner input = new Scanner(System.in);

    public static void customerMenu() {
        System.out.println("**** Welcome, " + BankApp.currentCustomer.getName() + "! ****");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer");
        System.out.println("4. Transaction History");
        System.out.println("5. Account Information");
        System.out.println("6. Log Out");
        System.out.println("7. Exit App");
        System.out.println("***********************************");

        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        if (choice < 1 || choice > 7) {
            System.out.println("Invalid choice. Please try again.\n");
            customerMenu();
        }

        switch (choice) {
            case 1:
                deposit();
                break;
            case 2:
                withdrawal();
                break;
            case 3:
                transfer(BankApp.customers);
                break;
            case 4:
                transactionHistory();
                break;
            case 5:
                accountInformation();
                break;
            case 6:
                logOut();
                break;
            case 7:
                BankApp.exitApp();
                break;
        }
    }

    public static void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = input.nextDouble();
        BankApp.currentCustomer.deposit(amount);
        customerMenu();
    }

    public static void withdrawal() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = input.nextDouble();
        BankApp.currentCustomer.withdrawal(amount);
        customerMenu();
    }

    public static void transfer(ArrayList<Customer> customers) {
        System.out.print("Enter the card number to transfer to: ");
        int cardNumber = input.nextInt();
        System.out.print("Enter the amount to transfer: ");
        double amount = input.nextDouble();

        Customer receiver = null;
        for (Customer customer : customers) {
            if (customer.getCardNumber() == cardNumber) {
                receiver = customer;
                break;
            }
        }

        if (receiver == null) {
            System.out.println("Invalid card number. Please try again.\n");
            customerMenu();
            return;
        }

        BankApp.currentCustomer.transfer(receiver, amount);
        customerMenu();
    }

    public static void transactionHistory() {
        BankApp.currentCustomer.showTransactionHistory();
        customerMenu();
    }

    public static void logOut() {
        System.out.println("Goodbye, " + BankApp.currentCustomer.getName() + "! See you next time.\n");
        BankApp.currentCustomer = null;
        BankApp.mainMenu();
    }

    public static void accountInformation() {
        System.out.println("**** Account Information ****");
        System.out.println("Name: " + BankApp.currentCustomer.getName());
        System.out.println("Username: " + BankApp.currentCustomer.getUsername());
        System.out.println("Password: " + BankApp.currentCustomer.getPassword());
        System.out.println("Card Number: " + BankApp.currentCustomer.getCardNumber());
        System.out.println("Pin: " + BankApp.currentCustomer.getPin());
        System.out.println("Balance: " + BankApp.currentCustomer.getBalance());
        System.out.println("*****************************\n");
        CustomerMenu.customerMenu();
    }
}
