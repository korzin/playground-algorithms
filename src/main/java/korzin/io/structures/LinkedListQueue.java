package korzin.io.structures;

public class LinkedListQueue<T> implements Queue<T> {

    private TwoDirLList<T> linkedList = new TwoDirLList<>();

    @Override
    public void enqueue(T value) {
        linkedList.pushFront(value);
    }

    @Override
    public boolean empty() {
        return top() == null;
    }

    @Override
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
