package korzin.io.structures;

import java.util.List;

public class LinkedListQueue<T> implements Queue<T> {

  private final BiDirectList<T> linkedList;

  public LinkedListQueue() {
    linkedList = new BiDirectList<>();
  }

  public LinkedListQueue(BiDirectList<T> linkedList) {
    this.linkedList = linkedList;
  }

  public LinkedListQueue(List<T> list) {
    this.linkedList = new BiDirectList<>(list);
  }

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

  @Override
  public T top() {
    BiDirectList.Node<T> tail = linkedList.topBack();
    return tail == null ? null : tail.getValue();
  }
}
