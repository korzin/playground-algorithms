package korzin.io.my.structures;

import java.util.NoSuchElementException;

public class TwoDirLList<T> {

    private Node<T> head;
    private Node<T> tail;

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

    public void pushFront(T key) {
        Node<T> newNode = new Node<T>();
        newNode.value = key;

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
            head.prev = null;
        }
    }

    public void pushBack(T key) {
        Node<T> newNode = new Node<T>();
        newNode.value = key;

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
        if (tail == null) throw new NoSuchElementException();
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

    public boolean find(T key) {
        if (head == null) return false;
        Node<T> curr = head;
        while (curr != null) {
            if (curr.value.equals(key)) return true;
            curr = curr.next;
        }
        return false;
    }

    public void erase(T key) {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.value == key) {
            popFront();
        } else if (tail.value == key) {
            popBack();
        } else {
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
                newNext.prev = curr;
            }
        }
    }

    public boolean empty() {
        return head == null;
    }

    public void addBefore(Node<T> node, T key) {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (head.value == node.value) {
            pushFront(key);
        } else {
            Node<T> newOne = new Node<>();
            newOne.value = key;

            newOne.prev = node.prev;
            newOne.next = node;
            if(node.prev != null) {
                node.prev.next = newOne;
            }
            node.prev = newOne;
        }
    }

    public void addAfter(Node<T> node, T key) {
        if (head == null) {
            throw new NoSuchElementException();
        } else if (tail.value == node.value) {
            pushBack(key);
        } else {
            Node<T> newOne = new Node<>();
            newOne.value = key;

            newOne.prev = node;
            newOne.next = node.next;

            if(node.next != null) {
                node.next.prev = newOne;
            }
            node.next = newOne;

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
        private TwoDirLList.Node<N> next;
        private TwoDirLList.Node<N> prev;

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
