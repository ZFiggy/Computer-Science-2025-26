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
		if (root == null) {
			return false;
		}
		return contains(value, root);
	}

	public boolean contains(E value, BinaryNode<E> currentNode) {
		if (value.compareTo(currentNode.getValue()) == 0) {
			return true;
		} else if (value.compareTo(currentNode.getValue()) > 0) {
			if (currentNode.hasRight()) {
				return contains(value, currentNode.getRight());
			} else {
				return false;
			}
		} else {
			if (currentNode.hasLeft()) {
				return contains(value, currentNode.getLeft());
			} else {
				return false;
			}
		}
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
		return remove(findNode(value, root));
	}

	public boolean remove(BinaryNode<E> nodeRemoved) {
		if (nodeRemoved.isLeaf()) {
			if (nodeRemoved.getParent() == null) {
				root = null;
				return true;
			}
			if (nodeRemoved.getParent().getValue().compareTo(nodeRemoved.getValue()) > 0) {
				nodeRemoved.getParent().setLeft(null);
			} else {
				nodeRemoved.getParent().setRight(null);
			}
			return true;
		}
		if (!nodeRemoved.hasRight()) {
			BinaryNode<E> replace = maxNodeFromSpot(nodeRemoved.getLeft());
			nodeRemoved.setValue(replace.getValue());
			if (replace.hasLeft()) {
				BinaryNode<E> updateHeight = replace.getLeft();
				if (replace.equals(nodeRemoved.getLeft())) {
					replace.getLeft().setParent(nodeRemoved);
					nodeRemoved.setLeft(replace.getLeft());
				} else {
					replace.getParent().setRight(replace.getLeft());
					replace.getLeft().setParent(replace.getParent());
				}
				subtractHeight(updateHeight);
			} else {
				return remove(replace);
			}
		} else {
			BinaryNode<E> replace = minNodeFromSpot(nodeRemoved.getRight());
			nodeRemoved.setValue(replace.getValue());
			if (replace.hasRight()) {
				BinaryNode<E> updateHeight = replace.getRight();
				if (replace.equals(nodeRemoved.getRight())) {
					replace.getRight().setParent(nodeRemoved);
					nodeRemoved.setRight(replace.getRight());
				} else {
					replace.getParent().setLeft(replace.getRight());
					replace.getRight().setParent(replace.getParent());
				}
				subtractHeight(updateHeight);
			} else {
				return remove(replace);
			}
		}
		return true;
	}

	public void subtractHeight(BinaryNode<E> start) {
		if (start == null) {
			return;
		}
		start.setHeight(start.getHeight() - 1);
		subtractHeight(start.getLeft());
		subtractHeight(start.getRight());
	}

	public BinaryNode<E> findNode(E value, BinaryNode<E> currentNode) {
		if (value.compareTo(currentNode.getValue()) > 0) {
			if (currentNode.hasRight()) {
				return findNode(value, currentNode.getRight());
			} else {
				System.out.println("Value not in list.");
				return null;
			}
		} else if (value.compareTo(currentNode.getValue()) < 0) {
			if (currentNode.hasLeft()) {
				return findNode(value, currentNode.getLeft());
			} else {
				System.out.println("Value not in list.");
				return null;
			}
		}
		return currentNode;
	}

	// Returns the minimum in the tree
	public E min() {
		if (root == null) {
			return null;
		}
		BinaryNode<E> currentNode = root;
		while (currentNode.hasLeft()) {
			currentNode = currentNode.getLeft();
		}
		return currentNode.getValue();
	}

	public BinaryNode<E> minNodeFromSpot(BinaryNode<E> currentNode) {
		if (root == null) {
			return null;
		}
		while (currentNode.hasLeft()) {
			currentNode = currentNode.getLeft();
		}
		return currentNode;
	}

	// Returns the maximum in the tree.
	public E max() {
		if (root == null) {
			return null;
		}
		BinaryNode<E> currentNode = root;
		while (currentNode.hasRight()) {
			currentNode = currentNode.getRight();
		}
		return currentNode.getValue();
	}

	public BinaryNode<E> maxNodeFromSpot(BinaryNode<E> currentNode) {
		if (root == null) {
			return null;
		}
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
		toString(root, str);
		str.append("]");
		return str.toString();
	}

	public void toString(BinaryNode<E> currentNode, StringBuilder currentString) {
		if (currentNode == null) {
			return;
		}
		toString(currentNode.getLeft(), currentString);
		if (currentString.length() == 1) {
			currentString.append(currentNode.getValue());
		} else {
			currentString.append(", " + currentNode.getValue());
		}
		toString(currentNode.getRight(), currentString);

		// if (currentNode.getValue().compareTo(min()) == 0) {
		// 	currentString.append(currentNode.getValue());
		// 	if (currentNode.hasRight()) {
		// 		toString(currentNode.getRight(), currentString);
		// 	}
		// } else {
		// 	if (currentNode.hasLeft()) {
		// 		toString(currentNode.getLeft(), currentString);
		// 	}
		// 	currentString.append(", " + currentNode.getValue());
		// 	if (currentNode.hasRight()) {
		// 		toString(currentNode.getRight(), currentString);
		// 	}
		// }
		// return currentString;
	}
}
