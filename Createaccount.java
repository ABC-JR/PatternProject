import java.util.Scanner;
import java.util.InputMismatchException;

class Createaccount {
    private Database db = Database.getInstance();

    public void createUser() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();

        if (db.userExists(name)) {
            System.out.println("User already exists. Please sign in.");
            return;
        }

        System.out.println("Enter password:");
        String password = sc.nextLine();

        System.out.println("Confirm password:");
        String confirmPassword = sc.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Try again.");
            return;
        }

        int age = 0;
        while (true) {
            try {
                System.out.println("Enter your age:");
                age = sc.nextInt();
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for age.");
                sc.nextLine(); // Consume the invalid input
            }
        }

        double height = 0.0;
        while (true) {
            try {
                System.out.println("Enter your height:");
                height = sc.nextDouble();
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for height.");
                sc.nextLine(); // Consume the invalid input
            }
        }

        double weight = 0.0;
        while (true) {
            try {
                System.out.println("Enter your weight:");
                weight = sc.nextDouble();
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for weight.");
                sc.nextLine(); // Consume the invalid input
            }
        }

        User user = new User.Builder()
                .setName(name)
                .setPassword(password)
                .setAge(age)
                .setHeight(height)
                .setWeight(weight)
                .build();

        db.addUser(user);
        System.out.println("Account created successfully!");
    }
}
