import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApp {
    static Scanner input = new Scanner(System.in);
    static Customer currentCustomer = null;
    static Admin currentAdmin = null;
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();

    public void start() {
        String hashedPassword = Authentication.hashPassword("admin");
        Admin admin = new Admin("admin", "admin", hashedPassword);
        admins.add(admin);
        String hashedPasswordCust = Authentication.hashPassword("customer");
        Customer cust = new Customer("customer", "customer", hashedPasswordCust, 123123);
        customers.add(cust);
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
        int choice = 0;
        try {
            choice = input.nextInt();

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please try again.\n");
                mainMenu();
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Invalid input. Please try again.\n");
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
        System.out.print("Enter your username: ");
        String username = input.next();
        System.out.print("Enter your password: ");
        String password = input.next();

        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && !Authentication.hashPassword(password).equals(customer.getPassword())) {
                if (customer.getSuspended()) {
                    System.out.println("Your account has suspended, please contact administrator!\n");
                    mainMenu();
                }

                if (customer.getTries() == 9) {
                    customer.setSuspended();
                }

                System.out.println("Invalid username or password. Please try again.\n");
                customer.addTries();
                login();
                return;
            }

            if (customer.getUsername().equals(username) && Authentication.hashPassword(password).equals(customer.getPassword())) {
                if (customer.getSuspended()) {
                    System.out.println("Your account has suspended, please contact administrator!\n");
                    mainMenu();
                }

                System.out.println("Login successful!\n");
                currentCustomer = customer;
                currentCustomer.resetTries();
                CustomerMenu.customerMenu();
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.\n");
        login();
    }

    public static void register() {
        System.out.print("Enter your name: ");
        String name = input.next();
        if (name.isEmpty()) {
            System.out.println("Invalid name. Name must be at least 1 character.\n");
            register();
            return;
        }

        System.out.print("Enter your username: ");
        String username = input.next();
        if (username.length() < 3 || username.length() > 12 || !username.matches("[A-Za-z0-9]+")) {
            System.out.println("Invalid username. Username must be 3-12 alphanumeric characters.\n");
            register();
            return;
        }

        System.out.print("Enter your password: ");
        String password = input.next();
        if (password.length() < 5) {
            System.out.println("Invalid password. Password must be at least 5 characters.\n");
            register();
            return;
        }

        String hashedPassword = Authentication.hashPassword(password);

        System.out.print("Enter your pin: ");
        String pin = input.next();
        if (pin.length() != 6 || !pin.matches("[0-9]+")) {
            System.out.println("Invalid pin. Pin must be 6 digits.\n");
            register();
            return;
        }

        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                System.out.println("Username is already taken. Please try again.\n");
                register();
                return;
            }
        }

        Customer newCustomer = new Customer(name, username, hashedPassword, Integer.parseInt(pin));
        customers.add(newCustomer);
        System.out.println("Registration successful!\n");
        mainMenu();
    }

    public static void adminArea() {
        System.out.print("Enter your username: ");
        String username = input.next();
        System.out.print("Enter your password: ");
        String password = input.next();

        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && Authentication.hashPassword(password).equals(admin.getPassword())) {
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