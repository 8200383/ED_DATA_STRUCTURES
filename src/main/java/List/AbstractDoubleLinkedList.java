package List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractDoubleLinkedList<T> implements ListADT<T> {

    protected Node<T> front;
    protected Node<T> rear;
    protected int currentSize;

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
        Node<T> tmp = front;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = tmp.element;
            tmp = tmp.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public AbstractDoubleLinkedList() {
        front = rear = null;
        currentSize = 0;
    }


    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        T removed = front.element;
        front = front.next;
        front.previous = null;
        currentSize--;
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

        Node<T> tmp = front;
        while (tmp.next != null) {
            if (tmp.element == target) {
                return true;
            }

            tmp = tmp.next;
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
        return new ListIterator();
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
