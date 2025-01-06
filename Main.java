import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Createaccount createAccount = new Createaccount();
        SignIn signIn = new SignIn();

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.println(" ".repeat(i) + "✨");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("🌟 Wish for something! 🌟");
        Thread.sleep(2000);
        System.out.println("I wish you good health! 😁");
        Thread.sleep(3000);

        System.out.println("I am your assistant off  health");
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount.createUser();

                    break;
                case 2:
                    signIn.login();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }



}