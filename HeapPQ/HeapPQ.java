import java.util.EmptyStackException;

public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;

	public HeapPQ() {
		this.heap = (E[]) new Comparable[3];
		this.objectCount = 0;
	}

	// Returns the number of elements in the priority queue
	public int size() {
		return objectCount;
	}

	// DO NOT CHANGE MY JANKY TOSTRING!!!!!
	public String toString() {
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++) {
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for (int rowLength = 1, j = 0; j < objectCount; rowLength *= 2) {
			for (int i = 0; i < rowLength && j < objectCount; i++, j++) {
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount) {
				for (int i = 0; i < Math.min(objectCount - j, rowLength * 2); i++) {
					if (i % 2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}

	// Doubles the size of the heap array
	private void increaseCapacity() {
		E[] newHeap = (E[]) new Comparable[heap.length * 2];
		for (int i = 0; i < heap.length; i++) {
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}

	// Returns the index of the "parent" of index i
	private int parent(int i) {
		if (i >= objectCount) {
			throw new IllegalArgumentException();
		}
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		return (i - 1) / 2;
	}

	// Returns the index of the *smaller child* of index i
	private int smallerChild(int i) {
		if (i >= objectCount) {
			throw new IllegalArgumentException();
		}
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		E childOne = heap[2 * i + 1];
		E childTwo = heap[2 * i + 2];
		if (childOne != null && childTwo != null) {
			if (childOne.compareTo(childTwo) > 0) {
				return 2 * i + 2;
			} else {
				return 2 * i + 1;
			}
		} else if (childOne == null) {
			throw new IllegalArgumentException();
		} else if (childTwo == null) {
			return 2 * i + 1;
		}
		return 2 * i + 1;
	}

	// Swaps the contents of indices i and j
	private void swap(int i, int j) {
		if (i >= objectCount || j >= objectCount || i < 0 || j < 0) {
			throw new IllegalArgumentException();
		}
		E val = heap[i];
		heap[i] = heap[j];
		heap[j] = val;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i) {
		if (i < 0 || i >= objectCount) {
			throw new IllegalArgumentException();
		}
		if (i == 0) {
			return;
		}
		int parent = parent(i);
		if (heap[i].compareTo(heap[parent]) >= 0) {
			return;
		} else {
			swap(i, parent);
			bubbleUp(parent);
		}
	}

	// Bubbles the element at index i downwards until the heap properties hold
	// again.
	private void bubbleDown(int i) {
		if (i < 0 || i >= objectCount) {
			throw new IllegalArgumentException();
		}
		try {
			int kid = smallerChild(i);
			if (heap[i].compareTo(heap[kid]) <= 0) {
				return;
			} else {
				swap(i, kid);
				bubbleDown(kid);
			}
		} catch (Exception e) {
			return;
		}
	}

	// Adds obj to the Priority Queue
	public void add(E obj) {
		if (objectCount == heap.length) {
			increaseCapacity();
		}
		heap[objectCount] = obj;
		bubbleUp(objectCount);
		objectCount++;
	}

	// Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			E remove = heap[0];
			heap[0] = heap[objectCount - 1];
			bubbleDown(0);
			objectCount--;
			return remove;
		}
	}

	// Returns the MINIMUM element from the Priority Queue without removing it
	public E peek() {
		if (!isEmpty()) {
			return heap[0];
		} else {
			throw new EmptyStackException();
		}
	}

	// Returns true if the priority queue is empty
	public boolean isEmpty() {
		if (objectCount == 0) {
			return true;
		} else {
			return false;
		}
	}

}
