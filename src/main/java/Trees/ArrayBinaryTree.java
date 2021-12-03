package Trees;

import List.UnorderedArrayList;
import Queue.LinkedQueue;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    public ArrayBinaryTree() {
        this.count = 0;
        this.tree = (T[]) new Object[CAPACITY];
    }

    public ArrayBinaryTree(T element) {
        this.count = 1;
        this.tree = (T[]) new Object[CAPACITY];
        this.tree[0] = element;
    }

    @Override
    public T getRoot() {
        return this.tree[0];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            return this.find(targetElement) != null;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        boolean found = false;
        T tmp = null;

        for (int i = 0; i < this.size(); i++) {
            if (targetElement.equals(this.tree[i])) {
                found = true;
                tmp = this.tree[i];
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }

        return tmp;
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    protected void inorder(int node, UnorderedArrayList<T> tempList) {
        if (node < this.size() && this.tree[node] != null) {
            inorder(node * 2 + 1, tempList);
            tempList.addToRear(this.tree[node]);
            inorder((node + 1) * 2, tempList);
        }
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    protected void preorder(int node, UnorderedArrayList<T> tempList) {
        if (node < this.size() && this.tree[node] != null) {
            tempList.addToRear(this.tree[node]);
            preorder(node * 2 + 1, tempList);
            preorder((node + 1) * 2, tempList);
        }
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    protected void postorder(int node, UnorderedArrayList<T> tempList) {
        if (node < this.size() && this.tree[node] != null) {
            postorder(node * 2 + 1, tempList);
            postorder((node + 1) * 2, tempList);
            tempList.addToRear(this.tree[node]);
        }
    }

    /**
     * Performs a recursive levelorder traversal.
     *
     * @param node     the node used in the traversal
     * @param tempList the temporary list used in the traversal
     */
    protected void levelorder(int node, UnorderedArrayList<T> tempList) {
        if (node < this.size() && this.tree[node] != null) {
            LinkedQueue<T> queue = new LinkedQueue<>();
            queue.enqueue(this.tree[node]);

            while (!queue.isEmpty()) {
                T tmp = queue.dequeue();
                tempList.addToRear(tmp);

                levelorder(node * 2 + 1, tempList);
                levelorder((node + 1) * 2, tempList);
            }
        }
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedArrayList<T> list = new UnorderedArrayList<>();
        inorder(0, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedArrayList<T> list = new UnorderedArrayList<>();
        preorder(0, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedArrayList<T> list = new UnorderedArrayList<>();
        postorder(0, list);

        return list.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedArrayList<T> list = new UnorderedArrayList<>();
        levelorder(0, list);

        return list.iterator();
    }
}
