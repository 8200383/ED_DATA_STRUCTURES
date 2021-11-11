package List;

public class OrderedArrayList<T> extends AbstractArrayList<T> implements OrderedListADT<T> {

    public OrderedArrayList() {
        super();
    }

    @Override
    public void add(T element) {
        Comparable<T> comparable = (Comparable<T>) element;

        int i = 0;
        while (i < size() && comparable.compareTo(super.list[i]) >= 0) {
            i++;
        }

        System.arraycopy(super.list, i, super.list, i + 1, super.rear);
        super.list[i] = element;
        super.rear++;
    }
}
