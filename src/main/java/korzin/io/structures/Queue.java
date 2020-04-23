package korzin.io.structures;

public class Queue<T> {

    private TwoDirLList<T> linkedList = new TwoDirLList<>();

    public void enqueue(T value) {
        linkedList.pushFront(value);
    }

    public boolean empty() {
        return top() == null;
    }

    public T dequeue() {
        T tail = top();
        linkedList.popBack();
        return tail;
    }

    private T top() {
        TwoDirLList.Node<T> tail = linkedList.topBack();
        return tail == null ? null : tail.getValue();
    }
}
