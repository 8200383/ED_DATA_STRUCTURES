package Trees.Heaps;

import Trees.BinaryTreeNode;

public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    public HeapNode(T element) {
        super(element);
        parent = null;
    }
}
