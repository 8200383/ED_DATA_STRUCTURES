package Demos;

import Stack.SmackStack;
import Stack.SmackStackADT;

public class SmackStackDemo {

    public static void main(String[] args) {
        SmackStackADT<Integer> stack = new SmackStack<>();
        int i = 1;
        while (i < 20) {
            stack.push(i++);
        }

        System.out.println(stack);

        System.out.println(stack.smack());

        System.out.println(stack);
    }
}
