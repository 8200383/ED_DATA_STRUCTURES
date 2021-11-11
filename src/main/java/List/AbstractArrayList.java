package List;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractArrayList<T> implements ListADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int modCount;

    protected T[] list;

    /**
     * Int that represents both the number of elements and the next
     * available position in the array
     */
    protected int rear;

    private class ArrayListIterator implements Iterator<T> {
        private int current;
        private int expectedModCount;
        private boolean okToRemove;

        public ArrayListIterator(int modCount) {
            current = 0;
            expectedModCount = modCount;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current < rear;
        }

        @Override
        public T next() {
            if (expectedModCount != AbstractArrayList.this.modCount) {
                throw new ConcurrentModificationException();
            }

            okToRemove = true;
            current++;
            return list[current - 1];
        }

        @Override
        public void remove() {
            if (expectedModCount != AbstractArrayList.this.modCount) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            AbstractArrayList.this.remove(list[current - 1]);
            current--;
            expectedModCount++;
            okToRemove = false;
        }
    }

    public AbstractArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
        rear = 0;
        modCount = 0;
    }

    public void expandArray() {
        T[] expanded = (T[]) new Object[DEFAULT_CAPACITY * 2];
        System.arraycopy(list, 0, expanded, 0, rear);
        list = expanded;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = list[0];
        list[0] = null;

        System.arraycopy(list, 1, list, 0, rear);
        rear--;
        modCount++;

        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = list[rear];
        list[rear] = null;
        rear--;
        modCount++;
        return removed;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        int i = 0;
        while (i < rear) {
            if (list[i] == element) {
                T removed = list[i];
                System.arraycopy(list, i + 1, list, i, rear);
                rear--;
                modCount++;
                return removed;
            }
            i++;
        }

        throw new NoSuchElementException();
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return list[0];
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return list[rear];
    }

    @Override
    public boolean contains(T target) {
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
        return new ArrayListIterator(modCount);
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
