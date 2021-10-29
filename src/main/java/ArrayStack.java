import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackADT<T> {

    /**
     * Constant to represent the default capacity of the array
     */
    private final int DEFAULT_CAPACITY = 100;

    /**
     * Int that represents both the number of elements and the next
     * available position in the array
     */
    private int top;

    /**
     * Array of generic elements to represent the stack
     */
    private T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates an empty stack using the specified capacity.
     *
     * @param capacity represents the specified capacity
     */
    public ArrayStack(int capacity) {
        stack = (T[]) new Object[capacity];
    }

    /**
     * Expands the capacity of the stack by 2x
     */
    private void expandCapacity() {
        T[] newStack = (T[]) new Object[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    /**
     * Adds one element to the top of this stack.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }

        stack[top] = element;
        top++;
    }

    /**
     * Removes and returns the top element from this stack.
     *
     * @return T element removed from the top of the stack
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        top--;
        T removed = stack[top];
        stack[top] = null;

        return removed;
    }

    /**
     * Returns without removing the top element of this stack.
     *
     * @return T element on top of the stack
     */
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack[top-1];
    }

    /**
     * Returns true if this stack contains no elements.
     *
     * @return boolean whether or not this stack is empty
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return int number of elements in this stack
     */
    @Override
    public int size() {
        return top;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return String representation of this stack
     */
    @Override
    public String toString() {
        return "ArrayStack{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", top=" + top +
                ", stack=" + Arrays.toString(stack) +
                '}';
    }
}
