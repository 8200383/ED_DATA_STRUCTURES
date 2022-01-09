package Trees;

public class BinaryTreeNode<T> {

    public T element;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }
}
