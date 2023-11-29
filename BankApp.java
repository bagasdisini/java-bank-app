import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    private static Scanner input = new Scanner(System.in);
    private static Customer currentCustomer = null;
    private static Admin currentAdmin = null;
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();

    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin", "admin");
        admins.add(admin);
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("**** Welcome to Bagas Bank ****");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Admin Area");
        System.out.println("4. Exit App");
        System.out.println("***********************************");

        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice. Please try again.\n");
            mainMenu();
        }

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                adminArea();
                break;
            case 4:
                exitApp();
                break;
        }
    }

    public static void login() {
        System.out.print("Enter your username:");
        String username = input.next();
        System.out.print("Enter your password:");
        String password = input.next();

        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                currentCustomer = customer;
                System.out.println("Login successful!\n");
                customerMenu();
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.\n");
        mainMenu();
    }

    public static void register() {
        System.out.print("Enter your name:");
        String name = input.next();
        System.out.print("Enter your username:");
        String username = input.next();
        System.out.print("Enter your password:");
        String password = input.next();
        System.out.print("Enter your pin:");
        int pin = input.nextInt();

        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                System.out.println("Username is already taken. Please try again.\n");
                mainMenu();
                return;
            }
        }

        Customer newCustomer = new Customer(name, username, password, pin);
        customers.add(newCustomer);
        System.out.println("Registration successful!\n");
        mainMenu();
    }

    public static void adminArea() {
        System.out.print("Enter your username:");
        String username = input.next();
        System.out.print("Enter your password:");
        String password = input.next();

        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                currentAdmin = admin;
                System.out.println("Login successful!\n");
                // Display the admin menu here
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.\n");
        mainMenu();
    }

    public static void exitApp() {
        System.out.println("Thank you for using Bagas Bank. Have a nice day!");
        input.close();
    }

    public static void customerMenu() {
        System.out.println("**** Welcome, " + currentCustomer.getName() + "! ****");
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
                transfer();
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
                exitApp();
                break;
        }
    }

    public static void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = input.nextDouble();
        currentCustomer.deposit(amount);
        customerMenu();
    }

    public static void withdrawal() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = input.nextDouble();
        currentCustomer.withdrawal(amount);
        customerMenu();
    }

    public static void transfer() {
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
            transfer();
            return;
        }

        currentCustomer.transfer(receiver, amount);
        customerMenu();
    }

    public static void transactionHistory() {
        currentCustomer.showTransactionHistory();
        customerMenu();
    }

    public static void accountInformation() {
        System.out.println("**** Account Information ****");
        System.out.println("Name: " + currentCustomer.getName());
        System.out.println("Username: " + currentCustomer.getUsername());
        System.out.println("Password: " + currentCustomer.getPassword());
        System.out.println("Card Number: " + currentCustomer.getCardNumber());
        System.out.println("Pin: " + currentCustomer.getPin());
        System.out.println("Balance: " + currentCustomer.getBalance());
        System.out.println("*****************************\n");
        customerMenu();
    }

    public static void logOut() {
        System.out.println("Goodbye, " + currentCustomer.getName() + "! See you next time.\n");
        currentCustomer = null;
        mainMenu();
    }
}