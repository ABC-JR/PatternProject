import java.util.Scanner;
class UserDashboard {
    private MyTimer myTimer = new MyTimer();
    private Database db = Database.getInstance();

    public void showDashboard(User user) {
        myTimer.startTimer();
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("Welcome, " + user.getName() + "! \nChoose an option:");
            System.out.println("1. View Profile");
            System.out.println ("2. Consultation with an online doctor");
            System.out.println("3. Turn Off/ Turn On Reminder");
            System.out.println("4. Calculate BMI");
            System.out.println("5. Edit Profile");
            System.out.println("6. Change Password");
            System.out.println("7. Delete Account");
            System.out.println("8. Log Out");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> viewProfile(user);
                case 2 -> consulting(user);
                case 3 -> reminder();
                case 4 -> calculateBMI(user);
                case 5 -> editProfile(user);
                case 6 -> changePassword(user);
                case 7 -> {
                    db.removeUser(user.getName());
                    System.out.println("Account deleted successfully.");
                    return;
                }
                case 8 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void calculateBMI(User user) {
        double heightInMeters = user.getHeight() / 100.0;

        HealthAnalysis bodyFatCalculator = new BodyFatCalculation();

        // Creating an adapter that converts BodyFatCalculation to BMI Calculator
        BMICalculator adaptedBMI = new BodyFatToBMICalculatorAdapter(bodyFatCalculator);

        // I use Bridge to perform verification (calculation of Body Fat)
        HealthCheck bodyFatCheck = new BodyFatHealthCheck(adaptedBMI);
        bodyFatCheck.performCheck(heightInMeters, user.getWeight());

        // Now i  am creating a regular implementation for BMI
        BMICalculator bmiCalculator = new BMI(new WeightCalculator());


        HealthCheck bmiCheck = new BMIHealthCheck(bmiCalculator);
        bmiCheck.performCheck(heightInMeters, user.getWeight());

        WeightCalculator weightCalculator = new WeightCalculator();
        BMICalculator bmi = new BMI(weightCalculator);

        System.out.println("BMI (Body Mass Index) is used to estimate the ratio \nof body weight to height. It helps to determine if a person's weight \nis within the normal range or if they \nare underweight, overweight, or obese.");

        double bmiIndex = bmi.calculate(heightInMeters, user.getWeight());

        if (bmiIndex < 18.5) {
            System.out.println("Category: Underweight");
            System.out.println("Consequences: Increased risk of nutritional deficiencies, weak immune system, fatigue, and potential bone health issues like osteoporosis.");
            System.out.println("Recommendations: \n- Increase calorie intake with nutrient-dense foods.\n- Focus on protein-rich meals and healthy fats.\n- Consult a doctor or dietitian for a personalized nutrition plan.\n- Consider adding strength training to build muscle mass.");
        } else if (bmiIndex >= 18.5 && bmiIndex < 24.9) {
            System.out.println("Category: Normal weight");
            System.out.println("Consequences: Generally associated with good health and lower risk of weight-related diseases.");
            System.out.println("Recommendations: \n- Maintain a balanced diet.\n- Stay physically active.\n- Continue regular health check-ups to monitor well-being.");
        } else if (bmiIndex >= 24.9 && bmiIndex < 29.9) {
            System.out.println("Category: Overweight");
            System.out.println("Consequences: Increased risk of cardiovascular diseases, type 2 diabetes, and joint issues.");
            System.out.println("Recommendations: \n- Adopt a balanced diet with reduced calorie intake.\n- Increase physical activity, such as regular cardio or strength exercises.\n- Seek advice from a healthcare provider or dietitian.");
        } else if (bmiIndex >= 30) {
            System.out.println("Category: Obesity");
            System.out.println("Consequences: High risk of serious health conditions, including heart disease, diabetes, high blood pressure, and some cancers.");
            System.out.println("Recommendations: \n- Consult a doctor for a comprehensive health evaluation.\n- Work with a dietitian to create a sustainable weight-loss plan.\n- Incorporate regular physical activity and consider behavior therapy.\n- Explore medical treatments if necessary, such as weight-loss medication or surgery.");
        }
    }


    private void reminder() {
        Scanner sc = new Scanner(System.in);
        if(db.reminder == true){
            System.out.println("Reminder is active.");
            System.out.println("Do you want to turn off?");
            System.out.println("Yes");
            System.out.println("No");
            String answer = sc.next();
            if(answer.equalsIgnoreCase("yes")){
                db.reminder = false;
                myTimer.worked = false;
                System.out.println("Reminder is active.");
            } else if (answer.equalsIgnoreCase("no")) {
                db.reminder = true;
                System.out.println("Reminder is not active.");


            }
            else{
                System.out.println("Invalid option. Try again.");
            }


        }else{
            System.out.println("Reminder is not active.");
            System.out.println("Do you want to turn on?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            String answer = sc.next();
            if(answer.equalsIgnoreCase("yes")){
                db.reminder = true;
                myTimer.worked = true;
                System.out.println("Reminder is active.");
            } else if (answer.equalsIgnoreCase("no")) {
                db.reminder = false;
                System.out.println("Reminder is not active.");
            }
            else{
                System.out.println("Invalid option. Try again.");
            }
        }

    }

    private void consulting(User user) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Write exit if you want to distrub:");

            System.out.println("Describe your question:");
            String userMessage = sc.nextLine();

            if (userMessage.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            String asnwer = "Answer more politely, and I need medical advice. Here my data my name is" + user.getName() + " I am" + user.getAge() + "My height" + user.getHeight() +"My Wight" + user.getWeight()  + "\n my question is " + userMessage;
            String gptResponse = HuggingFaceClient.getResponseFromHuggingFace(asnwer);
            System.out.println("GPT: " + gptResponse);
        }

    }

    private void viewProfile(User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Height: " + user.getHeight());
        System.out.println("Weight: " + user.getWeight());
    }

    private void editProfile(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new age:");
        int age = sc.nextInt();
        System.out.println("Enter new height:");
        double height = sc.nextDouble();
        System.out.println("Enter new weight:");
        double weight = sc.nextDouble();

        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);

        System.out.println("Profile updated successfully!");
    }

    private void changePassword(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter current password:");
        String currentPassword = sc.nextLine();

        if (!user.getPassword().equals(currentPassword)) {
            System.out.println("Incorrect password. Try again.");
            return;
        }

        System.out.println("Enter new password:");
        String newPassword = sc.nextLine();
        System.out.println("Confirm new password:");
        String confirmPassword = sc.nextLine();

        if (newPassword.equals(confirmPassword)) {
            user.setPassword(newPassword);
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Passwords do not match. Try again.");
        }
    }
}// Interface for calculating BMI
interface BMICalculator {
    double calculate(double height, double weight); // Method for calculating BMI
}

// Implementation for calculating BMI
class BMI implements BMICalculator {
    private final WeightCalculator weightCalculator;

    public BMI(WeightCalculator weightCalculator) {
        this.weightCalculator = weightCalculator;
    }

    @Override
    public double calculate(double height, double weight) {
        return weight / (height * height); // BMI calculation Formula
    }
}


class WeightCalculator {
    // A method for additional weight processing (for example, some transformations are applied to the weight)
    public double calculate(int weight) {

        return weight;
    }
}

// Abstraction for health check
abstract class HealthCheck {
    protected BMICalculator bmiCalculator;// Dependence on the BMICalculator interface

    public HealthCheck(BMICalculator bmiCalculator) {
        this.bmiCalculator = bmiCalculator; // Initializing a dependent implementation
    }

    abstract void performCheck(double height, double weight);
}


class BMIHealthCheck extends HealthCheck {
    public BMIHealthCheck(BMICalculator bmiCalculator) {
        super(bmiCalculator);
    }

    @Override
    void performCheck(double height, double weight) {
        double bmi = bmiCalculator.calculate(height, weight);
        System.out.println("Calculated BMI: " + bmi);
    }
}


class BodyFatHealthCheck extends HealthCheck {
    public BodyFatHealthCheck(BMICalculator bmiCalculator) {
        super(bmiCalculator);
    }

    @Override
    void performCheck(double height, double weight) {
        double bodyFat = bmiCalculator.calculate(height, weight);
        System.out.println("Calculated Body Fat Percentage: " + bodyFat);
    }
}


interface HealthAnalysis {
    double calculate(double height, double weight, double waistCircumference, double age);
}


class BodyFatCalculation implements HealthAnalysis {
    @Override
    public double calculate(double height, double weight, double waistCircumference, double age) {

        return (weight * 0.082 + 44.74) - (waistCircumference * 0.157) + (age * 0.2);
    }
}


class BodyFatToBMICalculatorAdapter implements BMICalculator {
    private final HealthAnalysis bodyFatCalculator;

    public BodyFatToBMICalculatorAdapter(HealthAnalysis bodyFatCalculator) {
        this.bodyFatCalculator = bodyFatCalculator;
    }

    @Override
    public double calculate(double height, double weight) {

        double waistCircumference = 80;
        double age = 30;
        return bodyFatCalculator.calculate(height, weight, waistCircumference, age);
    }
}



/*
Explanation: i used in the HealthCheck class and its subclasses BMIHealthCheck and BodyFatHealthCheck. These classes depend on the BMICalculator interface to calculate health metrics, which is passed to them via the constructor. The concrete implementation of the BMICalculator is provided by the BMI or BodyFatCalculation classes.
Location: The classes BMIHealthCheck, BodyFatHealthCheck, and HealthCheck are part of the Bridge pattern, decoupling the abstract check from the concrete calculation implementations.
 */