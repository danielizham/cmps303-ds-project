package main;

import model.Student;

public class App {
	public static void main(String[] args) {

		Menu.treeTable.insert(new Student(2001500, "Ali", "behind You", 2.8));
		Menu.treeTable.insert(new Student(2001432, "Hamad", "behind You", 2.7));
		Menu.treeTable.insert(new Student(2001657, "Jabbar", "behind You", 3.2));
		Menu.treeTable.insert(new Student(2001354, "Saud", "behind You", 1.5));
		Menu.treeTable.insert(new Student(2001234, "Saad", "behind You", 2.9));
		Menu.treeTable.insert(new Student(2001789, "Khalifa", "behind You", 3.7));
		Menu.treeTable.insert(new Student(2001675, "Amr", "behind You", 3.5));
		Menu.treeTable.insert(new Student(2001348, "Omar", "behind You", 1.4));
		Menu.treeTable.insert(new Student(2001567, "Abood", "behind You", 1.9));

		Menu.treeTable.insert(new Student(2002500, "Ali", "behind You", 2.8));
		Menu.treeTable.insert(new Student(2002432, "Hamad", "behind You", 2.7));
		Menu.treeTable.insert(new Student(2002657, "Jabbar", "behind You", 3.2));
		Menu.treeTable.insert(new Student(2002354, "Saud", "behind You", 1.5));
		Menu.treeTable.insert(new Student(2002234, "Saad", "behind You", 2.9));
		Menu.treeTable.insert(new Student(2002789, "Khalifa", "behind You", 3.7));
		Menu.treeTable.insert(new Student(2002675, "Amr", "behind You", 3.5));
		Menu.treeTable.insert(new Student(2002348, "Omar", "behind You", 1.4));
		Menu.treeTable.insert(new Student(2002567, "Abood", "behind You", 1.9));

		Menu.showMenu();
	}
}
