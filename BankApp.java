import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    static Scanner input = new Scanner(System.in);
    static Customer currentCustomer = null;
    static Admin currentAdmin = null;
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();

    public void start() {
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
                System.out.println("Login successful!\n");
                currentCustomer = customer;
                CustomerMenu.customerMenu();
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
                System.out.println("Login successful!\n");
                currentAdmin = admin;
                AdminMenu.adminMenu();
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
}

