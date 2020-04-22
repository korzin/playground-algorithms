package korzin.io.structures;

import java.util.NoSuchElementException;

public class OneDirLList<T> {

    private Node<T> head;
    private Node<T> tail;

    public int size() {
        if (empty()) return 0;
        int size = 0;
        Node curr = head;
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

    public void pushFront(T key) {
        Node<T> newNode = new Node<T>();
        newNode.value = key;
        if (head != null) {
            Node<T> oldHead = head;
            head = newNode;
            head.next = oldHead;
        } else {
            tail = newNode;
        }
        head = newNode;
    }

    public Node<T> topFront() {
        if (head == null) throw new NoSuchElementException();
        return head;
    }

    public void popFront() {
        if (head == null) throw new NoSuchElementException();
        else if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
    }

    public void pushBack(T key) {
        if (tail == null) throw new NoSuchElementException();

        Node<T> newTail = new Node<>();
        newTail.value = key;

        tail.next = newTail;
        tail = newTail;
    }

    public Node<T> topBack() {
        if (tail == null) throw new NoSuchElementException();
        return tail;
    }

    public void popBack() {
        if (tail == null) throw new NoSuchElementException();
        else if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> curr = head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            curr.next = null;
            tail = curr;
        }
    }

    public boolean find(T key) {
        if (head == null) return false;
        Node<T> curr = head;
        while (curr != null) {
            if (curr.value == key) return true;
            curr = curr.next;
        }
        return false;
    }

    public void erase(T key) {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.value == key) {
            popFront();
            return;
        } else if (tail.value == key) {
            popBack();
            return;
        }

        Node<T> curr = head;
        while (curr.next != null && curr.next.value.equals(key)) {
            curr = curr.next;
        }
        if (curr.next == null) {
            throw new NoSuchElementException();
        } else {
            Node<T> newNext = curr.next.next;
            curr.next.value = null; // help GC
            curr.next = newNext;
        }
    }

    public boolean empty() {
        return head == null;
    }

    public void addBefore(Node node, T key) {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.value == node.value) {
            pushFront(key);
        } else {
            Node<T> newOne = new Node<>();
            newOne.value = key;

            Node<T> curr = head;
            while (curr.next != null && !curr.next.value.equals(node.value)) {
                curr = curr.next;
            }
            if (curr.next == null) throw new NoSuchElementException();
            newOne.next = curr.next;
            curr.next = newOne;
        }
    }

    public void addAfter(Node<T> node, T key) {
        Node<T> newNode = new Node<>();
        newNode.value = key;

        Node<T> next = node.next;
        node.next = newNode;
        newNode.next = next;
        if (node == tail) {
            tail = newNode;
        }
    }

    public String toString() {
        if (empty()) return "empty";

        StringBuilder sb = new StringBuilder();
        sb.append(">>>");
        Node curr = head;
        while (curr != null) {
            sb.append(" ").append(curr.value);
            curr = curr.next;
        }
        return sb.toString();
    }

    public static class Node<N> {
        private N value;
        private Node<N> next;

        public N getValue() {
            return value;
        }

        public Node<N> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
