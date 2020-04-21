package datastructure;

import main.Menu;
import model.Student;

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
		Node student = hashArray[h(id)].find(id);
		if (student == null)
			return null;
		return student.dData;
	}

	public void update(int id, String name, String address, double gpa) {
		Student student = search(id);
		student.setAddress(address);
		student.setGpa(gpa);
		student.setName(name);
	}

	public Student delete(int id) { // dose not work if the id is not in the hashArray
		int hashVal = h(id); // hash the key
		if (hashArray[h(id)].find(id) != null) {
			Student s = hashArray[h(id)].find(id).dData; // find student to return
			hashArray[h(id)].delete(id); // delete the node
			return s; // return student
		} else
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
