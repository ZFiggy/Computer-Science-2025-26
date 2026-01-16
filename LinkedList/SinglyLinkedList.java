// Implements a singly-linked list.

import java.util.List;

public class SinglyLinkedList<E> {
	public ListNode<E> head;
	public ListNode<E> tail;
	public int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		head = null;
		tail = null;
		nodeCount = 0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	@SuppressWarnings("unchecked")
	public SinglyLinkedList(Object[] values) {
		if (values.length == 0) {
			head = null;
			tail = null;
			nodeCount = 0;
		} else if (values.length == 1) {
			head = new ListNode<E>((E) values[0]);
			tail = head;
			nodeCount = 1;
		} else {
			head = new ListNode<E>((E) values[0]);
			tail = head;
			nodeCount = 1;
			for (int i = 1; i < values.length; i++) {
				add((E) values[i]);
			}
		}
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
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
	public boolean contains(E obj) {
		if (nodeCount == 0) {
			return false;
		} else if (nodeCount == 1) {
			if (head.getValue().equals(obj)) {
				return true;
			}
			return false;
		}
		for (ListNode<E> i = head; i != null; i = i.getNext()) {
			if (i.getValue().equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		int count = 0;
		for (ListNode<E> i = head; i != null; i = i.getNext()) {
			if (i.getValue().equals(obj)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		if (nodeCount == 0) {
			head = new ListNode<E>((E) obj);
			tail = head;
			nodeCount++;
			return true;
		} else {
			tail.setNext(new ListNode<E>((E) obj));
			tail = tail.getNext();
			nodeCount++;
			return true;
		}
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		if (this.contains(obj)) {
			if (head.getValue().equals(obj)) {
				head = head.getNext();
				nodeCount--;
				if (head == null) {
					tail = null;
				}
				return true;
			}
			for (ListNode<E> i = head; i != tail; i = i.getNext()) {
				ListNode<E> nextNext = i.getNext();
				if (nextNext.getValue().equals(obj)) {
					if (i.getNext().equals(tail)) {
						tail = i;
						i.setNext(null);
						nodeCount--;
						return true;
					} else {
						i.setNext(nextNext.getNext());
						nodeCount--;
						return true;
					}
				}
			}
			return false;
		} else {
			return false;
		}
	}

	// Returns the i-th element.
	public E get(int i) {
		if (i >= nodeCount || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> newNode = head;
		for (int j = 1; j <= i; j++) {
			newNode = newNode.getNext();
		}
		return newNode.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	@SuppressWarnings("unchecked")
	public E set(int i, Object obj) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		E temp = this.get(i);
		ListNode<E> tempNode = head;
		for (int j = 0; j < i; j++) {
			tempNode = tempNode.getNext();
		}
		tempNode.setValue((E) obj);
		return temp;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	@SuppressWarnings("unchecked")
	public void add(int i, Object obj) {
		if (i < 0 || i > nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			ListNode<E> newNode = new ListNode<>((E) obj, head);
			head = newNode;
			if (nodeCount == 0)
				tail = head;
			nodeCount++;
			return;
		}
		if (i == nodeCount) {
			add((E) obj);
			return;
		}
		ListNode<E> tempNode = head;
		for (int j = 0; j < i - 1; j++) {
			tempNode = tempNode.getNext();
		}
		tempNode.setNext(new ListNode<>((E) obj, tempNode.getNext()));
		nodeCount++;
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			E temp = head.getValue();
			head = head.getNext();
			if (head == null)
				tail = null;
			nodeCount--;
			return temp;
		}
		ListNode<E> tempNode = head;
		for (int j = 0; j < i - 1; j++) {
			tempNode = tempNode.getNext();
		}
		E temp = tempNode.getNext().getValue();
		tempNode.setNext(tempNode.getNext().getNext());

		if (i == nodeCount - 1)
			tail = tempNode;
		nodeCount--;
		return temp;
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuilder newString = new StringBuilder("[");
		for (ListNode<E> i = head; i != tail; i = i.getNext()) {
			newString.append(i.getValue() + ", ");
		}
		newString.append(tail.getValue() + "]");
		return newString.toString();
	}
}
