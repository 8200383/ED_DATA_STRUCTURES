package Demos;

import Stack.ArrayStack;
import Stack.StackADT;

public class HTMLValidatorDemo {

    public static void main(String[] args) {
        StackADT<String> stack = new ArrayStack<>();

        String innerHtml = "<body> <h1> </h1> <a> </a> </body>";

        String[] splitHtml = innerHtml.split(" ");

        int i = 0;
        while (i < splitHtml.length) {

            if (splitHtml[i].charAt(1) != '/') {
                stack.push(extractTag(splitHtml[i], false));
            } else {
                String closingTag = extractTag(splitHtml[i], true);
                if (!stack.peek().equals(closingTag)) {
                    System.out.println(stack.peek() + "!=" + closingTag);
                    return;
                }

                stack.pop();
            }
            i++;
        }

        System.out.println("OK");
    }

    public static String extractTag(String tag, boolean isClosing) {
       if (isClosing) {
           String replaced_0 = tag.replace("</", "");
           return replaced_0.replace(">", "");
       }

        String replaced_0 = tag.replace("<", "");
        return replaced_0.replace(">", "");
    }
}
