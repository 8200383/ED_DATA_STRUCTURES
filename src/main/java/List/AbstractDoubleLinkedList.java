package List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractDoubleLinkedList<T> implements ListADT<T> {

    protected Node<T> front;
    protected Node<T> rear;
    protected int currentSize;
    protected int modCount;

    protected static class Node<T> {
        protected T element;
        protected Node<T> previous;
        protected Node<T> next;

        public Node(T element) {
            this.element = element;
            previous = next = null;
        }
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current;
        private Node<T> previous;
        private boolean okToRemove;
        private int expectedModCount;

        public ListIterator(int modCount) {
            previous = null;
            current = front;
            okToRemove = false;
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            okToRemove = true;
            T element = current.element;
            previous = current;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            if (expectedModCount != AbstractDoubleLinkedList.this.modCount) {
                throw new ConcurrentModificationException();
            }

            AbstractDoubleLinkedList.this.remove(previous.element);
            okToRemove = false;
            expectedModCount++;
        }
    }

    public AbstractDoubleLinkedList() {
        front = rear = null;
        currentSize = 0;
        modCount = 0;
    }


    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = front.element;
        front.previous = null;
        front = front.next;
        currentSize--;
        modCount++;
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = rear.element;
        rear = rear.previous;
        rear.next = null;
        currentSize--;
        modCount++;
        return removed;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        if (front.element == element) {
            return removeFirst();
        }

        if (rear.element == element) {
            return removeLast();
        }

        Node<T> tmp = front;
        while (tmp.next != null) {
            if (tmp.element == element) {
                tmp.previous.next = tmp.next;
                tmp.next.previous = tmp.previous;
                currentSize--;
                modCount++;
                return tmp.element;
            }

            tmp = tmp.next;
        }

        return null;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return front.element;
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        return rear.element;
    }

    @Override
    public boolean contains(T target) {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        Node<T> current = front;
        while (current != null) {
            if (current.element == target) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(modCount);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractDoubleLinkedList{currentSize=")
                .append(currentSize)
                .append(", nodes=[");

        Node<T> tmp = front;
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
