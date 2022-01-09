package Trees.Heaps;

import Trees.ElementNotFoundException;
import Trees.LinkedBinaryTree;

public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    public HeapNode<T> lastNode;

    public LinkedHeap() {
        super();
    }

    public LinkedHeap(T element) {
        super(element);
    }

    /**
     * Returns the node that will be the parent of the new node
     *
     * @return the node that will be a parent of the new node
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;

        while (result != root && result.parent.left != result) {
            result = result.parent;
        }

        if (result != root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = (HeapNode<T>) result.parent.right;
                while (result.left != null) {
                    result = (HeapNode<T>) result.left;
                }
            }
        } else {
            while (result.left != null) {
                result = (HeapNode<T>) result.left;
            }
        }

        return result;
    }

    /**
     * Reorders this heap after adding a node.
     */
    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;
        temp = next.element;

        while ((next != root) && (((Comparable) temp).compareTo(next.parent.element) < 0)) {
            next.element = next.parent.element;
            next = next.parent;
        }

        next.element = temp;
    }

    /**
     * Adds the specified element to this heap in the
     * appropriate position according to its key value
     * Note that equal elements are added to the right.
     *
     * @param obj the element to be added to this head
     */
    @Override
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<>(obj);

        if (root == null) {
            root = node;
        } else {
            HeapNode<T> nextParent = getNextParentAdd();

            if (nextParent.left == null) {
                nextParent.left = node;
            } else if (nextParent.right == null) {
                nextParent.right = node;
            }

            node.parent = nextParent;
        }

        lastNode = node;
        count++;

        if (count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Returns the node that will be the new last node after
     * a remove.
     *
     * @return the node that willbe the new last node after
     * a remove
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.parent.left == result))
            result = result.parent;
        if (result != root)
            result = (HeapNode<T>) result.parent.left;

        while (result.right != null)
            result = (HeapNode<T>) result.right;

        return result;
    }

    /**
     * Reorders this heap after removing the root element.
     */
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.left;
        HeapNode<T> right = (HeapNode<T>) node.right;
        HeapNode<T> next;
        if ((left == null) && (right == null))
            next = null;
        else if (left == null)
            next = right;
        else if (right == null)
            next = left;
        else if (((Comparable) left.element).compareTo(right.element) < 0)
            next = left;
        else
            next = right;

        temp = node.element;
        while ((next != null) && (((Comparable) next.element).compareTo(temp) < 0)) {
            node.element = next.element;
            node = next;
            left = (HeapNode<T>) node.left;
            right = (HeapNode<T>) node.right;
            if ((left == null) && (right == null))
                next = null;
            else if (left == null)
                next = right;
            else if (right == null)
                next = left;
            else if (((Comparable) left.element).compareTo(right.element) < 0)
                next = left;
            else
                next = right;
        }
        node.element = temp;
    }

    @Override
    public T removeMin() throws ElementNotFoundException {

        if (isEmpty()) {
            throw new ElementNotFoundException();
        }

        T min = root.element;

        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> nextLast = getNewLastNode();

            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }

            root.element = lastNode.element;
            lastNode = nextLast;
            heapifyRemove();
        }

        count--;

        return min;
    }

    @Override
    public T findMin() {
        return root.element;
    }
}
