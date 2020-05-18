package korzin.io.structures;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TwoDirLList<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;

  public TwoDirLList() {}

  public TwoDirLList(List<T> sourceList) {
    for (T curr : sourceList) {
      this.pushFront(curr);
    }
  }

  public int size() {
    if (empty()) return 0;
    int size = 0;
    TwoDirLList.Node curr = head;
    while (curr != null) {
      size++;
      curr = curr.next;
    }
    return size;
  }

  public Node<T> getByIndex(int index) {
    if (head == null) throw new NoSuchElementException();

    Node<T> curr = head;
    int i = 0;
    while (curr != null & i++ < index) {
      curr = curr.next;
    }
    return curr;
  }

  public void pushFront(T value) {
    Node<T> newNode = new Node<T>();
    newNode.value = value;

    if (head != null) {
      Node<T> oldHead = head;
      head = newNode;
      head.next = oldHead;
      oldHead.prev = head;
    } else {
      tail = newNode;
      head = newNode;
    }
  }

  public Node<T> topFront() {
    return head;
  }

  public void popFront() {
    if (head == null) throw new NoSuchElementException();
    else if (head == tail) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      head.prev = null;
    }
  }

  public void pushBack(T value) {
    Node<T> newNode = new Node<T>();
    newNode.value = value;

    if (tail != null) {
      Node<T> oldTail = tail;
      tail = newNode;
      tail.prev = oldTail;
      oldTail.next = tail;
    } else {
      head = newNode;
      tail = newNode;
    }
  }

  public Node<T> topBack() {
    return tail;
  }

  public void popBack() {
    if (tail == null) throw new NoSuchElementException();
    else if (head == tail) {
      head = null;
      tail = null;
    } else {
      tail = tail.prev;
      tail.next = null;
    }
  }

  public boolean find(T value) {
    if (head == null) return false;
    Node<T> curr = head;
    while (curr != null) {
      if (curr.value.equals(value)) return true;
      curr = curr.next;
    }
    return false;
  }

  public void erase(T value) {
    if (head == null) {
      throw new NoSuchElementException();
    } else if (head.value == value) {
      popFront();
    } else if (tail.value == value) {
      popBack();
    } else {
      Node<T> curr = head;
      while (curr.next != null && curr.next.value.equals(value)) {
        curr = curr.next;
      }
      if (curr.next == null) {
        throw new NoSuchElementException();
      } else {
        Node<T> newNext = curr.next.next;
        curr.next.value = null; // help GC
        curr.next = newNext;
        newNext.prev = curr;
      }
    }
  }

  public boolean empty() {
    return head == null;
  }

  public void add(int index, T value) {
    Node<T> nodeToInsertBefore = getByIndex(index);
    if (nodeToInsertBefore == null) {
      throw new IndexOutOfBoundsException(
          "List size is " + size() + ", but requested index is " + index);
    }
    addBefore(nodeToInsertBefore, value);
  }

  public void addBefore(Node<T> node, T value) {
    if (head == null) {
      throw new NoSuchElementException();
    } else if (head.value == node.value) {
      pushFront(value);
    } else {
      Node<T> newOne = new Node<>();
      newOne.value = value;

      newOne.prev = node.prev;
      newOne.next = node;
      if (node.prev != null) {
        node.prev.next = newOne;
      }
      node.prev = newOne;
    }
  }

  public void addAfter(Node<T> node, T value) {
    if (head == null) {
      throw new NoSuchElementException();
    } else if (tail.value == node.value) {
      pushBack(value);
    } else {
      Node<T> newOne = new Node<>();
      newOne.value = value;

      newOne.prev = node;
      newOne.next = node.next;

      if (node.next != null) {
        node.next.prev = newOne;
      }
      node.next = newOne;
    }
  }

  public String toString() {
    if (empty()) return "empty";

    StringBuilder sb = new StringBuilder();
    Node curr = head;
    while (curr != null) {
      sb.append(" ==> ").append(curr.value);
      curr = curr.next;
    }
    return sb.toString();
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {

      private Node<T> curr = TwoDirLList.this.head;
      private boolean isFirst = true;

      @Override
      public boolean hasNext() {
        return curr != null && (isFirst || curr.next != null);
      }

      @Override
      public T next() {
        if (curr != null) {
          Node<T> next;
          if (isFirst) {
            next = curr;
            isFirst = false;
          } else {
            next = curr = curr.next;
          }
          return next.value;
        } else {
          return null;
        }
      }
    };
  }

  public static class Node<N> {
    private N value;
    private TwoDirLList.Node<N> next;
    private TwoDirLList.Node<N> prev;

    public N getValue() {
      return value;
    }

    public Node<N> getNext() {
      return next;
    }

    public Node<N> getPrev() {
      return prev;
    }

    @Override
    public String toString() {
      return value.toString();
    }
  }
}
