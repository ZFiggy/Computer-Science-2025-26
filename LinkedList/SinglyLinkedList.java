// Implements a singly-linked list.

import java.util.List;

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

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
			tail = new ListNode<E>((E) values[values.length - 1]);
			for (ListNode<E> i = head; nodeCount != values.length - 1; i = i.getNext()) {
				nodeCount++;
				i.setNext(new ListNode<E>((E) values[nodeCount]));
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
		for (ListNode<E> i = head; i != tail; i = i.getNext()) {
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
		for (ListNode<E> i = head; i != tail; i = i.getNext()) {
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
			return true;
		} else {
			tail.setNext(new ListNode<E> ((E) obj));
			tail = tail.getNext();
			return true;
		}
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	@SuppressWarnings("unchecked")
	public boolean remove(E obj) {
		if (this.contains(obj)) {
			if (head.getValue().equals(obj)) {
				head = head.getNext();
				nodeCount--;
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
	public E set(int i, Object obj) {
		E temp = this.get(i);
		
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Object obj) {
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {

	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {

	}
}
