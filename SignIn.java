import java.util.Scanner;



interface SignIn_Interface {
    void login();
}


class SuccessFull_Interface implements SignIn_Interface {

    private Database db = Database.getInstance();
    @Override
    public void login() {

        System.out.println("Connected successfully to root account.\n");
        while (true){
            System.out.println("1  See all the Users");
            System.out.println("2 Send  message to all user");
            System.out.println("3 Exit");
            System.out.println("Choose an option:");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("All users name");
                    db.showAlluser();
                    break;
                case 2:
                    System.out.println("Your message:");
                    String message = sc.next();
                    db.register(message);
                    break;
                case 3:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again or create an account.");
            }


        }

    }
}
class SignIn implements SignIn_Interface {
    private SuccessFull_Interface successFull_Interface = new SuccessFull_Interface();
    private Database db = Database.getInstance();

    public void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();

        System.out.println("Enter your password:");
        String password = sc.nextLine();


        if (db.authenticate(name, password)) {
            System.out.println("Login successful! Welcome, " + name + ".");
           User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setAge(db.getage(name));
            user.setHeight(db.gethegt(name));
            user.setWeight(db.getw(name));

            UserDashboard userDashboard = new UserDashboard();
            userDashboard.showDashboard(user);
        }else if(name.equals("admin") && password.equals("Chess")) {
            successFull_Interface.login();
        }
        else {
            System.out.println("Invalid credentials. Please try again or create an account.");
        }
    }
}
