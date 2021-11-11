package List;

import java.util.NoSuchElementException;

public class UnorderedArrayList<T> extends AbstractArrayList<T> implements UnorderedListADT<T> {
    public UnorderedArrayList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        if (isEmpty()) {
            super.list[rear++] = element;
            return;
        }

        System.arraycopy(super.list, 0, super.list, 1, super.rear);
        super.list[0] = element;
        super.rear++;
    }

    @Override
    public void addToLast(T element) {
        super.list[super.rear++] = element;
    }

    @Override
    public void addAfter(T element, T target) throws NoSuchElementException {
        Comparable<T> comparable = (Comparable<T>) target;

        int i = 0;
        while (i < size()) {
            if (comparable.compareTo(super.list[i]) == 0) {
                System.arraycopy(super.list, i, super.list, i+1, rear);
                super.list[i] = element;
                super.rear++;
                return;
            }

            i++;
        }

        throw new NoSuchElementException();
    }
}
