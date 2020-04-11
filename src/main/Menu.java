package main;

import java.util.Scanner;

public class Menu {
    public static void showMenu() {

        int choice;
        do {
            System.out.println("\n--------Welcome--------");
            System.out.println("1. Add Student");
            System.out.println("2. Search For Student Using ID");
            System.out.println("3. Update Student Info");
            System.out.println("4. Delete Student");
            System.out.println("5. Display a Student's Data");
            System.out.println("6. Display All Students' Data in a Year");
            System.out.println("7. Display All Students");
            System.out.println("8. Show Tree of a Year");
            System.out.println("9. Display Students Under a Specific GPA");
            System.out.println("10. Save Data");
            System.out.println("11. Read Data");
            System.out.println("12. Exit");
            System.out.println("\nPlease Enter your Choice: ");

            choice = choice();

            switch (choice) {

                case 1:
                    // addStudent();
                    System.out.println("Not implemented Yet!");
                    break;
                case 2:
                    // searchStudent(id);
                    System.out.println("Not implemented Yet!");
                    break;
                case 3:
                    // updateStudent(id);
                    System.out.println("Not implemented Yet!");

                    break;
                case 4:
                    // deleteStudent(id);
                    System.out.println("Not implemented Yet!");

                    break;
                case 5:
                    // diplayStudent(id);
                    System.out.println("Not implemented Yet!");

                    break;
                case 6:
                    // displayStudents(year);
                    System.out.println("Not implemented Yet!");

                    break;
                case 7:
                    // displayStudents();
                    System.out.println("Not implemented Yet!");

                    break;
                case 8:
                    // showTree(year);
                    System.out.println("Not implemented Yet!");

                    break;
                case 9:
                    // studentsUnderGPA(gpa);
                    System.out.println("Not implemented Yet!");

                    break;
                case 10:
                    // saveFile();
                    System.out.println("Not implemented Yet!");

                    break;
                case 11:
                    // readFile();
                    System.out.println("Not implemented Yet!");

                    break;
                case 12:
                    System.out.println("\nThank you for using this application.");

                    break;
                default:
                    System.out.println("Invalid Input");
            }

        } while (choice != 12);
    }

    private static int choice() {

        Scanner input = new Scanner(System.in);
         // Cant Be Closed Since its in a loop, Creats an error once re intering the loop
        int choice = input.nextInt();
        input.nextLine();
        while (choice <= 0 && choice > 13) {
            System.out.println("Please Choose one of the valid Choices");
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }
}