import java.util.EmptyStackException;

public class MyStack<E> extends SinglyLinkedList<E> {

    private boolean empty;

    public MyStack() {
        empty = true;
        head = null;
        tail = null;
    }

    public boolean push(E obj) {
        if (obj == null) {
            return false;
        }
        empty = false;
        ListNode<E> newObj = new ListNode<E>(obj);
        newObj.setNext(head);
        head = newObj;
        return true;
    }

    public E pop() {
        if (empty) {
            throw new EmptyStackException();
        }
        ListNode<E> newObj = head;
        head = head.getNext();
        return newObj.getValue();
    }

    public E peek() {
        if (empty) {
            throw new EmptyStackException();
        }
        return head.getValue();
    }

    public boolean empty() {
        return empty;
    }
}
