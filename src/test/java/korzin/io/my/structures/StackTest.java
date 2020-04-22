package korzin.io.my.structures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {


    @Test
    public void testStack(){
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        stack.push(5);
        stack.push(4);
        stack.pop();
        stack.push(4);
        stack.top();
        stack.push(3);

        assertEquals(new Integer(3), stack.pop());
        assertEquals(new Integer(4), stack.pop());
        assertEquals(new Integer(5), stack.pop());
    }
}
