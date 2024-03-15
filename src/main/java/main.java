import java.time.LocalDate;
import java.sql.Date;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Database studentDB = new Database("jdbc:postgresql://localhost:5432/Assignment3", "postgres", "admin");
        Scanner scanner = new Scanner(System.in);
        boolean finish = false;

        while(!finish){
            System.out.println("\nWhat would you like to do?");
            System.out.println("0: get all students from table.");
            System.out.println("1: add a student to the table.");
            System.out.println("2: update student email in the table.");
            System.out.println("3: delete student info from the table.");
            System.out.println("4: end the program.");

            String userInput = scanner.nextLine();

            switch (userInput){
                case "0":
                    studentDB.getAllStudents();
                    break;
                case "1":
                    LocalDate localDate = LocalDate.now(); // Create a LocalDate object
                    Date curDate = java.sql.Date.valueOf(localDate); //Convert LocalDate to Date object
                    System.out.print("Please enter student first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Please enter student last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Please enter student email: ");
                    String email = scanner.nextLine();

                    studentDB.addStudent(firstName, lastName, email, curDate);
                    break;
                case "2":
                    System.out.print("Please enter the ID of the student you want to update: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Please enter student's updated email: ");
                    String newEmail = scanner.nextLine();
                    studentDB.updateStudentEmail(studentID, newEmail);
                    break;
                case "3":
                    System.out.print("Please enter the ID of the student you want to delete: ");
                    studentID = scanner.nextInt();
                    scanner.nextLine();
                    studentDB.deleteStudent(studentID);
                    break;
                case "4":
                    System.out.print("Ending...");
                    finish = true;
                    break;

                default:
                    System.out.print("That is not a valid input.");

            }
        }

    }
}
