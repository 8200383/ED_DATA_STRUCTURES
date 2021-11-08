package Queue;

import java.util.Arrays;
import java.util.EmptyStackException;

public class CircularArrayQueue<T> implements QueueADT<T> {

    /**
     * Constant to represent the default capacity of the array
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Array of generic elements to represent the queue
     */
    private T[] queue;

    /**
     * Int that represents where to write in the array
     */
    private int rear;

    /**
     * Int that represents where to read in the array
     */
    private int front;

    /**
     * Represent size of the array
     */
    private int size;

    public CircularArrayQueue() {
        front = rear = 0;
        size = 0;
        queue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to
     *                the rear of this queue
     */
    @Override
    public void enqueue(T element) throws Exception {
        if (size == queue.length) {
            throw new Exception("Reached max capacity");
        }

        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        size++;
    }

    /**
     * Removes and returns the element at the front of
     * this queue.
     *
     * @return the element at the front of this queue
     */
    @Override
    public T dequeue() throws EmptyStackException {

        // It matters checking is the array is empty
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T removed = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;

        return removed;
    }

    /**
     * Returns without removing the element at the front of
     * this queue.
     *
     * @return the first element in this queue
     */
    @Override
    public T first() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return queue[front];
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size
     * of this queue
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "Queue.CircularArrayQueue{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", queue=" + Arrays.toString(queue) +
                ", rear=" + rear +
                ", front=" + front +
                ", currentSize=" + size() +
                ", first=" + first() +
                ", isEmpty=" + isEmpty() +
                '}';
    }
}
