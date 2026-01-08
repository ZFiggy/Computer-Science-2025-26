import java.lang.StringBuilder;

// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	BinaryNode<E> root; // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public int getHeight() {
		return root.getHeight();
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		return contains(value, root);
	}

	public boolean contains(E value, BinaryNode<E> currentNode) {
		if (value.compareTo(currentNode.getValue()) == 0) {
			return true;
		} else if (value.compareTo(currentNode.getValue()) > 0) {
			if (currentNode.hasRight()) {
				contains(value, currentNode.getRight());
			} else {
				return false;
			}
		} else {
			if (currentNode.hasLeft()) {
				contains(value, currentNode.getLeft());
			} else {
				return false;
			}
		}
		System.out.println("Something went wrong.");
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		return add(value, root);
	}

	public boolean add(E value, BinaryNode<E> currentNode) {
		if (root == null) {
			root = new BinaryNode<E>(value);
			return true;
		}
		if (value.compareTo(currentNode.getValue()) == 0) {
			return false;
		} else if (value.compareTo(currentNode.getValue()) > 0) {
			if (currentNode.hasRight()) {
				return add(value, currentNode.getRight());
			} else {
				BinaryNode<E> newNode = new BinaryNode<E>(value);
				currentNode.setRight(newNode);
				newNode.setParent(currentNode);
				return true;
			}
		} else {
			if (currentNode.hasLeft()) {
				return add(value, currentNode.getLeft());
			} else {
				BinaryNode<E> newNode = new BinaryNode<E>(value);
				currentNode.setLeft(newNode);
				newNode.setParent(currentNode);
				return true;
			}
		}
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// largest node in the right subtree
	public boolean remove(E value) {
		if (!this.contains(value)) {
			return false;
		}
		return remove(value, findNode(value, root));
	}

	public boolean remove(E value, BinaryNode<E> nodeRemoved) {
		if (nodeRemoved.isLeaf()) {
			if (nodeRemoved.getParent().getValue().compareTo(nodeRemoved.getValue()) > 0) {
				nodeRemoved.getParent().setLeft(null);
			} else {
				nodeRemoved.getParent().setRight(null);
			}
		} else if (nodeRemoved.hasRight()) {
			BinaryNode<E> replace = minNodeFromSpot(nodeRemoved);
			nodeRemoved.setValue(replace.getValue());
		} else {
			BinaryNode<E> replace = maxNodeFromSpot(nodeRemoved);
			nodeRemoved.setValue(replace.getValue());
			

		}
	}

	public BinaryNode<E> findNode(E value, BinaryNode<E> currentNode) {
		if (value.compareTo(currentNode.getValue()) == 0) {
			return currentNode;
		} else if (value.compareTo(currentNode.getValue()) > 0) {
			if (currentNode.hasRight()) {
				contains(value, currentNode.getRight());
			} else {
				System.out.println("Value not in list.");
				return null;
			}
		} else {
			if (currentNode.hasLeft()) {
				contains(value, currentNode.getLeft());
			} else {
				System.out.println("Value not in list.");
				return null;
			}
		}
		System.out.println("Something went wrong.");
		return null;
	}

	// Returns the minimum in the tree
	public E min() {
		BinaryNode<E> currentNode = root;
		while (currentNode.hasLeft()) {
			currentNode = currentNode.getLeft();
		}
		return currentNode.getValue();
	}

	public BinaryNode<E> minNodeFromSpot(BinaryNode<E> currentNode) {
		while (currentNode.hasLeft()) {
			currentNode = currentNode.getLeft();
		}
		return currentNode;
	}

	// Returns the maximum in the tree.
	public E max() {
		BinaryNode<E> currentNode = root;
		while (currentNode.hasRight()) {
			currentNode = currentNode.getRight();
		}
		return currentNode.getValue();
	}

	public BinaryNode<E> maxNodeFromSpot(BinaryNode<E> currentNode) {
		while (currentNode.hasRight()) {
			currentNode = currentNode.getRight();
		}
		return currentNode;
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the
	// nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		str = toString(root, str);
		str.append("]");
		return str.toString();
	}

	public StringBuilder toString(BinaryNode<E> currentNode, StringBuilder currentString) {
		if (currentNode.getValue().compareTo(min()) == 0) {
			currentString.append(currentNode.getValue() + "()" + currentNode.getHeight());
			if (currentNode.hasRight()) {
				toString(currentNode.getRight(), currentString);
			}
		} else {
			if (currentNode.hasLeft()) {
				toString(currentNode.getLeft(), currentString);
			}
			currentString.append(", " + currentNode.getValue() + "()" + currentNode.getHeight());
			if (currentNode.hasRight()) {
				toString(currentNode.getRight(), currentString);
			}
		}
		return currentString;
	}
}
