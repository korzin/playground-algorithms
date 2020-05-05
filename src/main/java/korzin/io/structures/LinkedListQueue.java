package korzin.io.structures;

import java.util.List;

public class LinkedListQueue<T> implements Queue<T> {

  private TwoDirLList<T> linkedList = new TwoDirLList<>();

  public static <TT> LinkedListQueue<TT> fromList(List<TT> list) {
    LinkedListQueue<TT> queue = new LinkedListQueue<>();
    for (TT el : list) {
      queue.enqueue(el);
    }
    return queue;
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

  private T top() {
    TwoDirLList.Node<T> tail = linkedList.topBack();
    return tail == null ? null : tail.getValue();
  }
}
