package List;

public class DoubleLinkedOrderedList<T extends Comparable<T>> extends AbstractDoubleLinkedList<T> implements OrderedListADT<T> {

    public DoubleLinkedOrderedList() {
        super();
    }

    @Override
    public void add(T element) {
        Node<T> node = new Node<>(element);

        if (isEmpty()) {
            super.front = super.rear = node;
            super.currentSize++;
            super.modCount++;
            return;
        }

        if (element.compareTo(super.front.element) <= 0) {
            node.next = super.front;
            super.front.previous = node;
            super.front = node;
            super.currentSize++;
            super.modCount++;
            return;
        }

        Node<T> tmp = super.front;
        while (tmp.next != null && element.compareTo(tmp.element) > 0) {
            tmp = tmp.next;
        }

        node.previous = tmp;
        node.next = tmp.next;
        tmp.next = node;
        super.currentSize++;
        super.modCount++;
    }
}