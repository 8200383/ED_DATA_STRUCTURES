package List;

public class RecursionList<T extends Comparable<T>> extends DoubleLinkedUnorderedList<T> {

    public enum SIDE {
        LEFT,
        RIGHT
    }

    public RecursionList() {
        super();
    }

    public void printRecursively(SIDE side) {
        switch (side) {
            case LEFT:
                leftRecursivePrint(super.front);
                break;
            case RIGHT:
                rightRecursivePrint(super.rear);
                break;
        }
    }

    private void leftRecursivePrint(Node<T> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.element);
        leftRecursivePrint(node.next);
    }

    private void rightRecursivePrint(Node<T> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.element);
        rightRecursivePrint(node.previous);
    }
}
