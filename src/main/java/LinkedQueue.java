import java.util.EmptyStackException;

public class LinkedQueue<T> implements QueueADT<T> {

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
            next = null;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int currentSize;

    public LinkedQueue() {
        front = rear = null;
        currentSize = 0;
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to
     *                the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        Node<T> node = new Node<>(element);

        if (isEmpty()) {
            front = rear = node;
            currentSize++;
            return;
        }

        node.next = rear;
        rear = node;
        currentSize++;
    }

    /**
     * Removes and returns the element at the front of
     * this queue.
     *
     * @return the element at the front of this queue
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<T> tmp = rear;

        while(tmp.next.next != null) {
            tmp = tmp.next;
        }

        T removed = front.element;
        tmp.next = null;
        front = tmp;
        currentSize--;

        return removed;
    }

    /**
     * Returns without removing the element at the front of
     * this queue.
     *
     * @return the first element in this queue
     */
    @Override
    public T first() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return front.element;
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size
     * of this queue
     */
    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LinkedQueue{currentSize=")
                .append(currentSize)
                .append(", nodes=[");

        Node<T> tmp = rear;
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
