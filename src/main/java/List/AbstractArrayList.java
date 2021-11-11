package List;

import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100;

    protected T[] list;

    /**
     * Int that represents both the number of elements and the next
     * available position in the array
     */
    protected int rear;

    private class ArrayListIterator implements Iterator<T> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < rear;
        }

        @Override
        public T next() {
            current++;
            return list[current-1];
        }
    }

    public AbstractArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
        rear = 0;
    }

    private void expandArray() {
        T[] expanded = (T[]) new Object[DEFAULT_CAPACITY * 2];
        System.arraycopy(list, 0, expanded, 0, rear);
        list = expanded;
    }

    @Override
    public T removeFirst() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = list[0];
        list[0] = null;

        System.arraycopy(list, 1, list, 0, rear);
        rear--;

        return removed;
    }

    @Override
    public T removeLast() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = list[rear];
        list[rear] = null;
        rear--;
        return removed;
    }

    @Override
    public T remove(T element) throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return null;
    }

    @Override
    public T first() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return list[0];
    }

    @Override
    public T last() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return list[rear];
    }

    @Override
    public boolean contains(T target) throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        for (int i = 0; i < rear; i++) {
            if (list[i] == target) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return rear == 0;
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "Stack.ArrayStack{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", pos=" + rear +
                ", list=" + Arrays.toString(list) +
                '}';
    }
}
