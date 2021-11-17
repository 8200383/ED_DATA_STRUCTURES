package Stack;

public class SmackStack<T> extends ArrayStack<T> implements SmackStackADT<T> {

    @Override
    public T smack() {
        ArrayStack<T> innerStack = new ArrayStack<>();

        while (!super.isEmpty()) {
            innerStack.push(super.pop());
        }

        T removed = innerStack.pop();

        while (!innerStack.isEmpty()) {
            super.push(innerStack.pop());
        }

        return removed;
    }
}
