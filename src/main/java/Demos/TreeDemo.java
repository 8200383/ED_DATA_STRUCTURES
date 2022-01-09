package Demos;

import Trees.BinarySearchTreeADT;
import Trees.LinkedBinarySearchTree;

import java.util.Iterator;

public class TreeDemo {

    public static void main(String[] args) {


        BinarySearchTreeADT<Integer> bst = new LinkedBinarySearchTree<>();

        bst.addElement(2);
        bst.addElement(1);
        bst.addElement(3);

        Iterator<Integer> levelOrder = bst.iteratorLevelOrder();
        while (levelOrder.hasNext()) {
            System.out.println(levelOrder.next());
        }

        System.out.println();

        Iterator<Integer> postOrder = bst.iteratorPostOrder();
        while (postOrder.hasNext()) {
            System.out.println(postOrder.next());
        }

        System.out.println();

        Iterator<Integer> preOrder = bst.iteratorPreOrder();
        while (preOrder.hasNext()) {
            System.out.println(preOrder.next());
        }

        System.out.println();

        Iterator<Integer> inOrder = bst.iteratorInOrder();
        while (inOrder.hasNext()) {
            System.out.println(inOrder.next());
        }
    }
}
