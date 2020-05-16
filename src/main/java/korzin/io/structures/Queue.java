package korzin.io.structures;

public interface Queue<T> {

  void enqueue(T value);

  boolean empty();

  T dequeue();

  T top();
}
