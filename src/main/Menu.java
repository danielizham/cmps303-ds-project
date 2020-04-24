package main;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import datastructure.TreeTable;
import model.Student;

public class Menu {
	static TreeTable treeTable = new TreeTable();
	// Let This Be Here For Now Since We Should Move All Of the Data into The Data
	// Manager.
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
				try {
					deleteStudent();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

	protected static void deleteStudent() throws InterruptedException {
		System.out.println("\t\t-----------DELETE STUDENT-----------");
		System.out.println("Please Enter Student ID: ");
		int sID = validateIDExist(scanner.nextLine());
		System.out.print("Are you sure you want to delete ");
		System.out.println(treeTable.search(sID) + ("? (y/n): "));
		String answer = scanner.nextLine();
		while (!answer.matches("y|n|Y|N")) {
			System.out.print("ERROR: Invalid answer. Enter 'y' or 'n': ");
			answer = scanner.nextLine();
		}
		if (answer.matches("n|N")) {
			System.out.print("Cancelling deletion.");
			TimeUnit.SECONDS.sleep(1);
			System.out.print(".");
			TimeUnit.SECONDS.sleep(1);
			System.out.println(".");
			return;
		}
		treeTable.delete(sID);
		System.out.println();
		System.out.println("The student with ID " + sID + " has been successfully deleted!");
		TimeUnit.SECONDS.sleep(2);
	}

	protected static int choiceInput() {
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}

	protected static void addStudent() {
		System.out.println("\t\t-----------ADD STUDENT-----------");
		System.out.println("Please Enter Student ID: ");
		int sID = validateIDNotExist(scanner.nextLine());
		System.out.println("Please Enter Student Name: ");
		String sName = scanner.nextLine();
		System.out.println("Please Enter Student Address: ");
		String sAddress = scanner.nextLine();
		System.out.println("Please Enter Student GPA: ");
		double sGPA = validateGPA(scanner.nextLine());
		treeTable.insert(new Student(sID, sName, sAddress, sGPA));
		System.out.println("\nStudent Inserted Successfully !");
	}

	protected static void search() { // What is the difference between this and Display???
		System.out.println("\t\t-----------SEARCH STUDENT-----------");
		System.out.println("Please Enter Student ID: ");
		int sID = validateIDExist(scanner.nextLine());
		Student stud = treeTable.search(sID);
		System.out.println(stud.toString());
		System.out.println("\nThank you!");
	}

	protected static void update() {
		System.out.println("\t\t-----------UPDATE STUDENT-----------");
		System.out.println("Please Enter Student ID: ");

		int sID = validateIDExist(scanner.nextLine());
		System.out.println("Current Student Information");
		System.out.println(treeTable.search(sID));

		System.out.println("Enter new Name:");
		String name = scanner.nextLine();
		System.out.println("Enter new Address:");
		String address = scanner.nextLine();
		System.out.println("Enter new GPA:");
		double gpa = scanner.nextDouble();
		treeTable.update(sID, name, address, gpa);
		System.out.println("Student Updated Successfully !");
	}

	// Delete()

	protected static void display() {
		System.out.println("\t\t-----------DISPLAY STUDENT-----------");
		System.out.println("Please Enter Student ID: ");
		int sID = validateIDExist(scanner.nextLine());
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
			int year = validateYear(scanner.nextLine());
			treeTable.printTree(year);
			break;
		default:
			break;
		}
	}

	protected static void showTreeByYear() {
		System.out.println("\t\t-----------DISPLAY TREE BY YEAR-----------");
		System.out.println("Please Enter Year: ");
		int year = validateYear(scanner.nextLine());
		treeTable.showTree(year);
	}

	protected static void studentsUnderGPA() {
		System.out.println("\t\t-----------DISPLAY STUDENT UNDER GPA X-----------");
		System.out.println("Please Enter GPA: ");
		double sGPA = validateGPA(scanner.nextLine());
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

	// ========VALIDATION METHODS======== //

	private static int validateIDNotExist(String input) {
		while (true) {
			if (!input.matches("20[\\d]{3,}"))
				System.out.println("ERROR: The format of a " + "student ID must be 20YY[ID]\n");
			else if (studentExists(Integer.parseInt(input)))
				System.out.println("ERROR: The student with ID " + input + " already exists\n");
			else
				break;
			System.out.println("Please Enter Student ID: ");
			input = scanner.nextLine();
		}
		return Integer.parseInt(input);
	}

	private static int validateIDExist(String input) {
		while (true) {
			if (!input.matches("20[\\d]{3,}"))
				System.out.println("ERROR: The format of a " + "student ID must be 20YY[ID]\n");
			else if (!studentExists(Integer.parseInt(input)))
				System.out.println("ERROR: The student with ID " + input + " does not exist\n");
			else
				break;
			System.out.println("Please Enter Student ID: ");
			input = scanner.nextLine();
		}
		return Integer.parseInt(input);
	}

	private static double validateGPA(String input) {
		while (!input.matches("[0-3]{1}(.[\\d])?|4(.0)?")) {
			System.out.println("ERROR: The format of a " + "student GPA must be e.g. 3.6\n");
			System.out.println("Please Enter Student GPA: ");
			input = scanner.nextLine();
		}
		return Double.parseDouble(input);
	}

	private static int validateYear(String input) {
		while (!input.matches("20[\\d]{2}")) {
			System.out.println("ERROR: The format of a " + "year must be 20YY\n");
			System.out.println("Please Enter Year: ");
			input = scanner.nextLine();
		}
		return Integer.parseInt(input);
	}

	private static boolean studentExists(int id) {
		return treeTable.search(id) != null;
	}

}
