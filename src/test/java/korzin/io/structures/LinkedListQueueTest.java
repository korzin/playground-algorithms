package korzin.io.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListQueueTest {

  @Test
  public void testQueue() {

    Queue<String> queue = new LinkedListQueue<>();

    queue.enqueue("111");
    assertFalse(queue.empty());
    queue.dequeue();
    assertTrue(queue.empty());
    queue.enqueue("111");
    queue.enqueue("222");
    queue.enqueue("333");
    assertEquals("111", queue.top());
    queue.enqueue("444");

    queue.dequeue();

    assertEquals("222", queue.top());
    assertEquals("222", queue.dequeue());
    assertEquals("333", queue.dequeue());
    assertEquals("444", queue.dequeue());
  }
}
