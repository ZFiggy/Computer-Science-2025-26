
public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private final ListNode2<Nucleotide> SENTINEL = new ListNode2<Nucleotide>(null);
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		SENTINEL.setNext(SENTINEL);
		SENTINEL.setPrevious(SENTINEL);
	}
	
	public ListNode2<Nucleotide> getSentinel() {
		return SENTINEL;
	}
	
	public ListNode2<Nucleotide> getHead() {
		return SENTINEL.getNext();
	}
	
	public ListNode2<Nucleotide> getTail() {
		return SENTINEL.getPrevious();
	}


	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		if (nodeCount == 0) {
			return true;
		}
		return false;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(Nucleotide obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		for (ListNode2<Nucleotide> i = SENTINEL.getNext(); i != SENTINEL; i = i.getNext()) {
			if (i.getValue().equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		if (contains(obj)) {
			int count = 0;
			for (ListNode2<Nucleotide> i = SENTINEL.getNext(); i != SENTINEL; i = i.getNext()) {
			if (i.getValue().equals(obj)) {
				return count;
			}
			count++;
		}
		}
		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		ListNode2<Nucleotide> newNode = new ListNode2<Nucleotide>(obj, SENTINEL.getPrevious(), SENTINEL);
		SENTINEL.getPrevious().setNext(newNode);
		SENTINEL.setPrevious(newNode);
		nodeCount++;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		ListNode2<Nucleotide> node = SENTINEL;
		nodeCount--;
		if (index == 0) {
			SENTINEL.setNext(node.getNext().getNext());
			SENTINEL.getNext().setPrevious(SENTINEL);
			return true;
		} else if (index == 1) {
			node = SENTINEL.getNext();
			node.setNext(node.getNext().getNext());
			node.getNext().setPrevious(node);
			return true;
		}
		for (int i = 0; i < index - 1; i++) {
			node = node.getNext();
		}
		node.setNext(node.getNext().getNext());
		node.getNext().setPrevious(node);
		return true;
	}

	// Returns the i-th element.               
	public Nucleotide get(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> newNode = SENTINEL;
		for (int j = 0; j <= i; j++) {
			newNode = newNode.getNext();
		}
		return newNode.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		Nucleotide temp = this.get(i);
		ListNode2<Nucleotide> tempNode = SENTINEL;
		for (int j = 0; j <= i; j++) {
			tempNode = tempNode.getNext();
		}
		tempNode.setValue(obj);
		return temp;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> tempNode = new ListNode2<Nucleotide>(obj, )
		// Might need a getNode :(
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
	}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {


	}
	
	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		
	}
	
	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		
	}
	
	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		
	}
	
	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	public void replaceEveryAWithTAC() {
		
	}

}
