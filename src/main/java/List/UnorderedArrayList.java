package List;

import java.util.NoSuchElementException;

public class UnorderedArrayList<T> extends AbstractArrayList<T> implements UnorderedListADT<T> {
    public UnorderedArrayList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        if (super.rear == super.list.length) {
            super.expandArray();
        }

        if (isEmpty()) {
            super.list[rear++] = element;
            return;
        }

        System.arraycopy(super.list, 0, super.list, 1, super.rear);
        super.list[0] = element;
        super.rear++;
    }

    @Override
    public void addToRear(T element) {
        if (super.rear == super.list.length) {
            super.expandArray();
        }

        super.list[super.rear++] = element;
    }

    @Override
    public void addAfter(T element, T target) throws NoSuchElementException {
        if (super.rear == super.list.length) {
            super.expandArray();
        }

        if (!(target instanceof Comparable)) {
            throw new ClassCastException();
        }

        Comparable<T> comparable = (Comparable<T>) target;

        int i = 0;
        while (i < size()) {
            if (comparable.compareTo(super.list[i]) == 0) {
                System.arraycopy(super.list, i, super.list, i + 1, super.rear);
                super.list[i] = element;
                super.rear++;
                return;
            }

            i++;
        }

        throw new NoSuchElementException();
    }
}
