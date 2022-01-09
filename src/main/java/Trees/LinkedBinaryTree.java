package Trees;

import List.UnorderedArrayList;
import Queue.LinkedQueue;
import Queue.QueueADT;

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    public LinkedBinaryTree(T element) {
        this.count = 1;
        this.root = new BinaryTreeNode<>(element);
    }

    @Override
    public T getRoot() {
        return this.root.element;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) {
        BinaryTreeNode<T> found = this.findAgain(targetElement, this.root);

        return found != null;
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next          the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.element.equals(targetElement)) {
            return next;
        }

        /* Find on the left side */
        BinaryTreeNode<T> tmp = findAgain(targetElement, next.left);

        if (tmp == null) {
            /* Find on the right side */
            tmp = findAgain(targetElement, next.right);
        }

        return tmp;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {

        BinaryTreeNode<T> tmp = this.findAgain(targetElement, this.root);

        if (tmp == null) {
            throw new ElementNotFoundException();
        }

        return tmp.element;
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, UnorderedArrayList<T> tempList) {
        if (node == null) return;

        inorder(node.left, tempList);
        tempList.addToRear(node.element);
        inorder(node.right, tempList);
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void preorder(BinaryTreeNode<T> node, UnorderedArrayList<T> tempList) {
        if (node == null) return;

        tempList.addToRear(node.element);
        preorder(node.left, tempList);
        preorder(node.right, tempList);
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void postorder(BinaryTreeNode<T> node, UnorderedArrayList<T> tempList) {
        if (node == null) return;

        postorder(node.left, tempList);
        postorder(node.right, tempList);
        tempList.addToRear(node.element);
    }

    /**
     * Performs a recursive levelorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void levelorder(BinaryTreeNode<T> node, UnorderedArrayList<T> tempList) {
        if (node == null) return;

        QueueADT<BinaryTreeNode<T>> queue = new LinkedQueue<>();
        queue.enqueue(node);

        while (!queue.isEmpty()) {

            BinaryTreeNode<T> tmp = queue.dequeue();
            tempList.addToRear(tmp.element);

            if (tmp.left != null) {
                queue.enqueue(node.left);
            }

            if (tmp.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<>();
        postorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<>();
        levelorder(root, tempList);

        return tempList.iterator();
    }
}
