package korzin.io.structures;

import java.util.List;

public class Stack<T> {

  private BiDirectList<T> linkedList = new BiDirectList<>();

  public static <ST> Stack<ST> fromList(List<ST> list) {
    Stack<ST> stack = new Stack<>();
    for (ST el : list) {
      stack.push(el);
    }
    return stack;
  }

  public void push(T value) {
    linkedList.pushFront(value);
  }

  public T top() {
    BiDirectList.Node<T> head = linkedList.topFront();
    return head == null ? null : head.getValue();
  }

  public T pop() {
    T head = top();
    linkedList.popFront();
    return head;
  }
}
