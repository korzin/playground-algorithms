package korzin.io.structures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayQueueTest {

    @Test
    public void testQueue(){
        Queue<String> queue = new ArrayQueue<>(3);


        queue.enqueue("111");
        assertFalse(queue.empty());
        queue.dequeue();
        assertTrue(queue.empty());
        queue.enqueue("111");
        queue.enqueue("222");
        queue.enqueue("333");

        queue.dequeue();

        assertEquals("222", queue.dequeue());
        assertEquals("333", queue.dequeue());
    }

    @Test(expected = IllegalStateException.class)
    public void validateArraySizeExceeded() {
        Queue<Integer> queue = new ArrayQueue<>(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
    }
}
