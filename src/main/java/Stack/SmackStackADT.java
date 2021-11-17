package Stack;

public interface SmackStackADT<T> extends StackADT<T> {

    /**
     * Remove the last element in the stack
     *
     * @return The last element in the stack
     */
    public T smack();
}
