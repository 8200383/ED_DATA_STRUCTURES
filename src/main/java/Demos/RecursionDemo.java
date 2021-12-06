package Demos;

import List.RecursionList;

import static List.RecursionList.*;

public class RecursionDemo {

    public static void main(String[] args) {
        RecursionList<Integer> list = new RecursionList<>();

        for (int i = 1; i <= 5; i++) {
            list.addToRear(i);
        }

        System.out.println("From right to left");
        list.printRecursively(SIDE.LEFT);
        System.out.println("From left to right");
        list.printRecursively(SIDE.RIGHT);
    }
}
