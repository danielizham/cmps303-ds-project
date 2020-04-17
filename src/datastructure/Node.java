package datastructure;

import model.Student;

public class Node {
	public int iData; // data item (key)
	public Student dData; // data item
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child

	public void displayNode() // display ourself
	{
		System.out.println("{ " + iData + ", " + dData + " }");
	}
} // end class Node