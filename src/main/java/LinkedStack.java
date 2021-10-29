import java.util.EmptyStackException;

public class LinkedStack<T> implements StackADT<T> {

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
            next = null;
        }
    }

    private Node<T> head;
    private int currentSize;

    public LinkedStack() {
        head = null;
        currentSize = 0;
    }

    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        Node<T> node = new Node<>(element);

        if (isEmpty()) {
            head = node;
            currentSize++;
            return;
        }

        node.next = head;
        head = node;
        currentSize++;
    }

    /**
     * Removes and returns the top element from this stack.
     *
     * @return T element removed from the top of the stack
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T removed = head.element;
        head = head.next;
        currentSize--;

        return removed;
    }

    /**
     * Returns without removing the top element of this stack.
     *
     * @return T element on top of the stack
     */
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return head.element;
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether or not this stack is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LinkedStack{currentSize=")
               .append(currentSize)
               .append(", nodes=[");

        Node<T> tmp = head;
        while (tmp != null) {
            builder.append(tmp.element);

            if (tmp.next != null) {
                builder.append(", ");
            }

            tmp = tmp.next;
        }

        builder.append("]}");

        return builder.toString();
    }
}
