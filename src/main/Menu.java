package main;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import datastructure.TreeTable;
import model.Student;

public class Menu {
    static TreeTable treeTable = new TreeTable();
    // Let This Be Here For Now Since We Should Move All Of the Data into The Data Manager.
    // Also It Made It easier to access the table in the functions for now.
    // Will Be Changed soon
    public static Scanner scanner = new Scanner(System.in); // Generally Scoped Scanner To Avoid Repetition

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

            choice = choiceInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    update(); // Creates a New Student Using the Updated info
                    break;
                case 4:
                    // deleteStudent(id);
                    System.out.println("Not implemented Yet!");
                    break;
                case 5:
                    display();
                    break;
                case 6:
                    displayStudents(2);
                    break;
                case 7:
                    displayStudents(1);
                    break;
                case 8:
                    showTreeByYear();
                    break;
                case 9:
                    studentsUnderGPA();
                    break;
                case 10:
                    saveToFile();
                    break;
                case 11:
                    loadFromFile();
                    break;
                case 12:
                    System.out.println("\nThank you for using this application.");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid Input");
            }

        } while (choice != 12);
    }

    protected static int choiceInput() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    protected static void addStudent() {
        System.out.println("\t\t-----------ADD STUDENT-----------");
        System.out.println("Please Enter Student ID: ");
        int sID;
        String sID_string = scanner.nextLine();
        while (!sID_string.matches("20[\\d]{3,}")) {
        	System.out.println("ERROR: The format of a "
        			+ "student ID must be 20YYXXXXX");
        	System.out.println("Please Enter Student ID: ");
        	sID_string = scanner.nextLine();
        }
        sID = Integer.parseInt(sID_string);
        System.out.println("Please Enter Student Name: ");
        String sName = scanner.nextLine();
        System.out.println("Please Enter Student Address: ");
        String sAddress = scanner.nextLine();
        System.out.println("Please Enter Student GPA: ");
        Double sGPA;
        String sGPA_string = scanner.nextLine(); 
        while (!sGPA_string.matches("[0-3]{1}(.[\\d])?|4(.0)?")) {
        	System.out.println("ERROR: The format of a "
        			+ "student GPA must be e.g. 3.6");
        	System.out.println("Please Enter Student GPA: ");
        	sGPA_string = scanner.nextLine();
        }
        sGPA = Double.parseDouble(sGPA_string);
        treeTable.insert(new Student(sID, sName, sAddress, sGPA));
        System.out.println("\nStudent Inserted Successfully !");
    }

    protected static void search() { // What is the difference between this and Display???
        System.out.println("\t\t-----------SEARCH STUDENT-----------");
        System.out.println("Please Enter Student ID: ");
        int sID;
        String sID_string = scanner.nextLine();
        while (!sID_string.matches("20[\\d]{3,}")) {
        	System.out.println("ERROR: The format of a "
        			+ "student ID must be 20YYXXXXX");
        	System.out.println("Please Enter Student ID: ");
        	sID_string = scanner.nextLine();
        }
        sID = Integer.parseInt(sID_string);
//        sID = cin.nextInt();
        Student stud = treeTable.search(sID);
        System.out.println(stud.toString());
        System.out.println("\nThank you!");
    }

    protected static void update() {
        System.out.println("\t\t-----------UPDATE STUDENT-----------");
        System.out.println("Please Enter Student ID: ");

        // THE COMMENTED CODE WAS: A trial to verify if the ID is available as an input

        // Student sFlag;
        int sID;
        String sID_string = scanner.nextLine();
        while (!sID_string.matches("20[\\d]{3,}")) {
        	System.out.println("ERROR: The format of a "
        			+ "student ID must be 20YYXXXXX");
        	System.out.println("Please Enter Student ID: ");
        	sID_string = scanner.nextLine();
        }
        sID = Integer.parseInt(sID_string);
        // String flag;
        // do {
        // sID = scanner.nextInt();
        // sFlag = treeTable.search(sID);
        // System.out.println(" This ID Is Not Registered, Try Again? (Y/N) ");
        // cin.nextLine();
        // flag = cin.nextLine().toLowerCase();
        // } while (sFlag == null && flag == "y");
        // if (flag == "n" && sFlag != null) {
        treeTable.update(sID);
        // }
    }

    // Delete()

    protected static void display() {
        System.out.println("\t\t-----------DISPLAY STUDENT-----------");
        System.out.println("Please Enter Student ID: ");
        int sID;
        String sID_string = scanner.nextLine();
        while (!sID_string.matches("20[\\d]{3,}")) {
        	System.out.println("ERROR: The format of a "
        			+ "student ID must be 20YYXXXXX");
        	System.out.println("Please Enter Student ID: ");
        	sID_string = scanner.nextLine();
        }
        sID = Integer.parseInt(sID_string);
//        sID = scanner.nextInt();
        treeTable.printStudent(sID);
    }

    protected static void displayStudents(int mode) {
        switch (mode) {
            case 1:
                treeTable.printAll();
                break;
            case 2:
                System.out.println("\t\t-----------Display STUDENTS BY YEAR-----------");
                System.out.println("Please Enter Year: ");
                int year;
                String year_string = scanner.nextLine();
                while (!year_string.matches("20[\\d]{2}")) {
                	System.out.println("ERROR: The format of a "
                			+ "year must be 20YY");
                	System.out.println("Please Enter Year: ");
                	year_string = scanner.nextLine();
                }
                year = Integer.parseInt(year_string);
                treeTable.printTree(year);
                break;
            default:
                break;
        }
    }

    protected static void showTreeByYear() {
        System.out.println("\t\t-----------DISPLAY TREE BY YEAR-----------");
        System.out.println("Please Enter Year: ");
        int year;
        String year_string = scanner.nextLine();
        while (!year_string.matches("20[\\d]{2}")) {
        	System.out.println("ERROR: The format of a "
        			+ "year must be 20YY");
        	System.out.println("Please Enter Year: ");
        	year_string = scanner.nextLine();
        }
        year = Integer.parseInt(year_string);
        treeTable.showTree(year);
    }

    protected static void studentsUnderGPA() {
        System.out.println("\t\t-----------DISPLAY STUDENT UNDER GPA X-----------");
        System.out.println("Please Enter GPA: ");
        double sGPA;
        String sGPA_string = scanner.nextLine(); 
        while (!sGPA_string.matches("[0-3]{1}(.[\\d])?|4(.0)?")) {
        	System.out.println("ERROR: The format of a "
        			+ "student GPA must be e.g. 3.6");
        	System.out.println("Please Enter Student GPA: ");
        	sGPA_string = scanner.nextLine();
        }
        sGPA = Double.parseDouble(sGPA_string);
        treeTable.studentGPA(sGPA);
    }

    public static void saveToFile() {
        try {
            Writer writer = new FileWriter("data/Students.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(treeTable, writer);
            writer.close();
            System.out.println("==============DATA SAVED SUCCESSFULLY==============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=============!!OOPS SOMETHING HAPPENED!!==============");
        }
    }

    public static void loadFromFile() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("data/Students.json"));
            treeTable = new Gson().fromJson(reader, new TypeToken<TreeTable>() {
            }.getType());
            reader.close();
            System.out.println("==============DATA LOADED SUCCESSFULLY==============");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("=============!!OOPS SOMETHING HAPPENED!!==============");
        }
    }
}
