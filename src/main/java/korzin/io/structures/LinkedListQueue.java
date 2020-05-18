package korzin.io.structures;

import java.util.List;

public class LinkedListQueue<T> implements Queue<T> {

  private final TwoDirLList<T> linkedList;

  public LinkedListQueue() {
    linkedList = new TwoDirLList<>();
  }

  public LinkedListQueue(TwoDirLList<T> linkedList) {
    this.linkedList = linkedList;
  }

  public LinkedListQueue(List<T> list) {
    this.linkedList = new TwoDirLList<>(list);
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
    TwoDirLList.Node<T> tail = linkedList.topBack();
    return tail == null ? null : tail.getValue();
  }
}
