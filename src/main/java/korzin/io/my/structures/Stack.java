package korzin.io.my.structures;

public class Stack<T> {

    private TwoDirLList<T> linkedList = new TwoDirLList<>();

    public void push(T value) {
        linkedList.pushFront(value);
    }

    public T top() {
        TwoDirLList.Node<T> head = linkedList.topFront();
        return head == null ? null : head.getValue();
    }

    public T pop() {
        T head = top();
        linkedList.popFront();
        return head;
    }

}
