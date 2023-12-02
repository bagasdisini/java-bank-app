import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminManageMenu {
    static Scanner input = new Scanner(System.in);

    public static void adminManageMenu() {
        System.out.println("\n**** Manage User ****");
        System.out.println("1. Suspend User");
        System.out.println("2. Unsuspend User");
        System.out.println("3. Go Back");
        System.out.println("4. Log Out");
        System.out.println("5. Exit App");
        System.out.println("***********************************");

        System.out.print("Enter your choice: ");
        int choice = 0;
        try {
            choice = input.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                adminManageMenu();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Invalid input. Please try again.");
            adminManageMenu();
        }

        switch (choice) {
            case 1:
                suspendUser(BankApp.customers);
                break;
            case 2:
                unsuspendUser(BankApp.customers);
                break;
            case 3:
                System.out.println();
                AdminMenu.adminMenu();
                break;
            case 4:
                AdminMenu.logOut();
                break;
            case 5:
                BankApp.exitApp();
                break;
        }
    }

    public static void suspendUser(ArrayList<Customer> customers) {
        System.out.print("Enter the username to suspend: ");
        String username = input.next();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Invalid username. Please try again.\n");
            suspendUser(BankApp.customers);
            return;
        }

        customer.setSuspended();
        System.out.println("Customer : " + customer.getUsername()+ " has been suspended!");
        adminManageMenu();
    }

    public static void unsuspendUser(ArrayList<Customer> customers) {
        System.out.print("Enter the username to unsuspend: ");
        String username = input.next();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Invalid username. Please try again.\n");
            unsuspendUser(BankApp.customers);
            return;
        }

        customer.resetSuspended();
        System.out.println("Customer : " + customer.getUsername()+ "'s suspend has been removed!");
        adminManageMenu();
    }
}