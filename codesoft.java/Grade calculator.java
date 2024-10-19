import java.util.Scanner;

public class GradeCalculator {

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the number of subjects: ");
            int numSubjects = scanner.nextInt();

            if (numSubjects <= 0) {
                System.out.println("The number of subjects should be greater than zero.");
                return;
            }

            double[] marks = new double[numSubjects];
            double totalMarks = 0;

            // Loop to take marks input and validate
            for (int i = 0; i < numSubjects; i++) {
                while (true) {
                    try {
                        System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
                        double mark = scanner.nextDouble();

                        if (mark >= 0 && mark <= 100) {
                            marks[i] = mark;
                            totalMarks += mark;
                            break;
                        } else {
                            System.out.println("Please enter a mark between 0 and 100.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number between 0 and 100.");
                        scanner.next(); // Clear the invalid input
                    }
                }
            }

            double averagePercentage = totalMarks / numSubjects;
            String grade = calculateGrade(averagePercentage);

            System.out.println("\n--- Results ---");
            System.out.println("Total Marks: " + totalMarks);
            System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
            System.out.println("Grade: " + grade);

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number for the number of subjects.");
        } finally {
            scanner.close();
        }
    }
}
