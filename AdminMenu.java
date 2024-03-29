import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    static Scanner input = new Scanner(System.in);

    public static void adminMenu() {
        System.out.println("**** Welcome, " + BankApp.currentAdmin.getName() + "! ****");
        System.out.println("1. List Customer");
        System.out.println("2. Transaction History");
        System.out.println("3. Manage Customer");
        System.out.println("4. Account Information");
        System.out.println("5. Log Out");
        System.out.println("6. Exit App");
        System.out.println("***********************************");

        System.out.print("Enter your choice: ");
        int choice = 0;
        try {
            choice = input.nextInt();

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.\n");
                adminMenu();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Invalid input. Please try again.\n");
            adminMenu();
        }

        switch (choice) {
            case 1:
                listCustomer(BankApp.customers);
                break;
            case 2:
                adminTransactionHistory(BankApp.customers);
                break;
            case 3:
                AdminManageMenu.adminManageMenu();
                break;
            case 4:
                accountInformation();
                break;
            case 5:
                logOut();
                break;
            case 6:
                BankApp.exitApp();
                break;
        }
    }

    public static void listCustomer(ArrayList<Customer> customers) {
        System.out.println("**** List of Customers ****");
        for (Customer customer : customers) {
            customer.displayInfo();
            System.out.println();
        }
        System.out.println("*****************************\n");
        adminMenu();
    }

    public static void adminTransactionHistory(ArrayList<Customer> customers) {
        System.out.print("Enter the card number to see the transaction history: ");
        int cardNumber = input.nextInt();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCardNumber() == cardNumber) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Invalid card number. Please try again.\n");
            AdminMenu.adminTransactionHistory(BankApp.customers);
            return;
        }

        customer.showTransactionHistory();
        adminMenu();
    }

    public static void accountInformation() {
        System.out.println("**** Account Information ****");
        BankApp.currentAdmin.displayInfo();
        System.out.println("*****************************\n");
        adminMenu();
    }

    public static void logOut() {
        System.out.println("Goodbye, " + BankApp.currentAdmin.getName() + "! See you next time.\n");
        BankApp.currentAdmin = null;
        BankApp.mainMenu();
    }
}
