package datastructure;

import com.google.gson.Gson;
import model.Student;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class TreeTable {


	private Tree[] hashArray;

	public TreeTable() {
		hashArray = new Tree[20];
		for (int i = 0; i < hashArray.length; i++)
			hashArray[i] = new Tree();
	}

	public void insert(Student student) {
		hashArray[h(student.getId())].insert(student.getId(), student);
	}

	public Student search(int id) {
		Student student = hashArray[h(id)].find(id).dData;
		if (student != null)
			return student;
		else
			return null;
	}

	public void update(int id) {
		Student student = search(id);
		if (student != null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Current Student Information");
			System.out.println(student);

			System.out.println("Enter new Name:");
			String name = scanner.nextLine();
			System.out.println("Enter new Address:");
			String address = scanner.nextLine();
			System.out.println("Enter new GPA:");
			double gpa = scanner.nextDouble();
			// Puts a New Student, Doesnt Update
			hashArray[h(id)].find(id).dData = new Student(id, name, address, gpa);
			System.out.println("Student Updated Succefully !");
			scanner.close();
		} else {
			System.out.println("Student with such id does not exist!");
		}
	}

	public Student delete(int id) {
		return null;
	}

	public void printStudent(int id) {
		System.out.println(search(id).toString());
	}

	public void printTree(int year) {
		hashArray[year % 20].printData();
	}

	public void printAll() {
		for (Tree tree : hashArray)
			tree.printData();
	}

	public void showTree(int year) {
		hashArray[year % 20].displayTree();
	}

	public void studentGPA(double gpa) {
		for (Tree tree : hashArray)
			tree.printByGpa(gpa);
	}

	public int h(int id) {
		String number = String.valueOf(id);
		int year = Integer.parseInt(number.substring(0, 4));
		return year % 20;
	}

}
