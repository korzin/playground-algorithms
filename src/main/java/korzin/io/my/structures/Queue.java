package korzin.io.my.structures;

public class Queue<T> {

    private TwoDirLList<T> linkedList = new TwoDirLList<>();

    public void push(T value) {
        linkedList.pushFront(value);
    }

    public T top() {
        TwoDirLList.Node<T> tail = linkedList.topBack();
        return tail == null ? null : tail.getValue();
    }

    public T pop() {
        T tail = top();
        linkedList.popBack();
        return tail;
    }
}
