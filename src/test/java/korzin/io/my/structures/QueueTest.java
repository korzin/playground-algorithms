package korzin.io.my.structures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueTest {

    @Test
    public void testQueue(){

        Queue<String> queue = new Queue<>();

        queue.push("111");
        queue.pop();
        queue.push("111");
        queue.push("222");
        queue.push("333");
        queue.push("444");
        queue.top();
        queue.top();

        queue.pop();

        assertEquals("222", queue.top());
        assertEquals("222", queue.pop());
        assertEquals("333", queue.pop());
        assertEquals("444", queue.pop());


    }

}
