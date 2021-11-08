package List;

import java.util.NoSuchElementException;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Adds the specified element to the beginning of the list
     *
     * @param element The element ot be added to the list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to the end of the list
     *
     * @param element The element to be added to the list
     */
    public void addToLast(T element);

    /**
     * Adds the specified element to this list after the target location
     *
     * @param element The element to be added to this list
     * @param target  The target that is being sought in the list
     * @throws NoSuchElementException If the target isn't found
     */
    public void addAfter(T element, T target) throws NoSuchElementException;
}
