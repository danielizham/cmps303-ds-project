package datastructure;

import model.Student;

import java.util.Stack;

public class Tree {
	private Node root;

	public Tree() {
		root = null;
	}

	public Node find(int key) {
		Node current = root;
		while (current.iData != key) {
			if (key < current.iData)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(int id, Student dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;
		if (root == null)
			root = newNode;
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (id < current.iData) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public void traverse(int traverseType) {
		switch (traverseType) {
			case 1:
				System.out.print("\nPreorder traversal: ");
				preOrder(root);
				break;
			case 2:
				System.out.print("\nInorder traversal:  ");
				inOrder(root);
				break;
			case 3:
				System.out.print("\nPostorder traversal: ");
				postOrder(root);
				break;
		}
		System.out.println();
	}

	// -------------------------------------------------------------
	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	// -------------------------------------------------------------
	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}

	// -------------------------------------------------------------
	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}

	// -------------------------------------------------------------
	public int max() {
		Node t = root;
		while (t.rightChild != null)
			t = t.rightChild;
		return t.iData;
	}

	// -----------------------------------------------------------
	public int count() {
		if (root == null)
			return 0;
		else
			return countNodes(root);
	}

	public int countNodes(Node n) {
		if (n == null)
			return 0;
		else
			return 1 + countNodes(n.rightChild) + countNodes(n.leftChild);
	}
	// --------------------------------------------------

	public int countLeaves() {
		return countLeaves(root);
	}

	private int countLeaves(Node n) {
		if (n == null)
			return 0;
		if (n.leftChild == null && n.rightChild == null)
			return 1;
		else
			return countLeaves(n.leftChild) + countLeaves(n.rightChild);
	}

	// ------------------------------------------
	public void displayTree() {
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................");
	} // end displayTree()
		// -------------------------------------------------------------

	public void printData() {
		printNode(root);
	}

	private void printNode(Node localRoot) {
		if (localRoot != null) {
			System.out.println(localRoot.dData);
			printNode(localRoot.leftChild);
			printNode(localRoot.rightChild);
		}
	}

	public void printByGpa(double gpa) {
		printNodeGpa(root, gpa);
	}

	private void printNodeGpa(Node localRoot, double gpa) {
		if (localRoot != null) {
			if (localRoot.dData.getGpa() < gpa)
				System.out.println(localRoot.dData);
			printNodeGpa(localRoot.leftChild, gpa);
			printNodeGpa(localRoot.rightChild, gpa);
		}
	}

} // end class Tree