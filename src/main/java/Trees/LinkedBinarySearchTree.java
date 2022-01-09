package Trees;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new
     *                binary search tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its key value. Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search tree
     */
    @Override
    public void addElement(T element) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(element);
        Comparable<T> comparableNode = (Comparable<T>) element;

        if (isEmpty()) {
            super.root = node;
            return;
        }

        BinaryTreeNode<T> current = super.root;
        boolean added = false;

        while (!added) {
            // Se for mais pequeno adiciona a esquerda
            if (comparableNode.compareTo(current.element) < 0) {
                if (current.left == null) {
                    current.left = node;
                    added = true;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = node;
                    added = true;
                } else {
                    current = current.right;
                }
            }
        }

        super.count++;
    }

    /**
     * Returns a reference to a node that will replace the one
     * specified for removal. In the case where the removed node has
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result;

        if ((node.left == null) && (node.right == null))
            result = null;
        else if ((node.left != null) && (node.right == null))
            result = node.left;
        else if ((node.left == null) && (node.right != null))
            result = node.right;
        else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            while (current.left != null) {
                parent = current;
                current = current.left;
            }

            if (node.right == current)
                current.left = node.left;
            else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }

        return result;
    }

    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it. Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary search tree
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        if (!isEmpty()) {
            if (((Comparable) targetElement).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;

                if (((Comparable) targetElement).compareTo(root.element) < 0)
                    current = root.left;
                else
                    current = root.right;

                while (current != null && !found) {
                    if (targetElement.equals(current.element)) {
                        found = true;
                        count--;
                        result = current.element;
                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;
                        if (((Comparable) targetElement).compareTo(current.element) < 0)
                            current = current.left;
                        else
                            current = current.right;
                    }
                } //while
                if (!found)
                    throw new ElementNotFoundException();
            }
        } // end outer if
        return result;
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException {

        removeElement(targetElement);

        boolean occur = true;
        while (occur) {
            try {
                removeElement(targetElement);
            } catch (ElementNotFoundException e) {
                occur = false;
            }
        }
    }

    @Override
    public T removeMin() {

        BinaryTreeNode<T> current = super.root;
        BinaryTreeNode<T> parent = null;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }

        if (current == super.root) {
            super.root = super.root.right;
            return current.element;
        }

        // Se é uma folha
        if (current.right == null) {
            parent.left = null;
            return current.element;
        }

        // é um nó interno
        //if (current.right != null) {
        parent.left = current.right;
        return current.element;
        //}

        //return null;
    }

    @Override
    public T removeMax() {
        return null;
    }

    @Override
    public T findMin() {
        BinaryTreeNode<T> current = super.root;

        while (current.left != null) {
            current = current.left;
        }

        return current.element;
    }

    @Override
    public T findMax() {
        BinaryTreeNode<T> current = super.root;

        while (current.right != null) {
            current = current.right;
        }

        return current.element;
    }
}
