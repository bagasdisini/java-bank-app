public class Admin extends User {
    public Admin(String name, String username, String password) {
        super(name, username, password, "Admin");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: " + role);
    }
}
