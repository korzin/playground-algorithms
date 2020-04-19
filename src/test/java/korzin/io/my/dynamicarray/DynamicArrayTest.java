package korzin.io.my.dynamicarray;

import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicArrayTest {

    @Test
    public void testArr(){
        DynamicArray array = new DynamicArray();
        assertTrue(array.isEmpty());
        array.add(1);
        assertFalse(array.isEmpty());
        array.add(2);
        assertEquals(2, array.toArray().length);
        assertEquals(array.size(), 2);

        array.add(3);
        Object old2 = array.set(1, 22);
        assertEquals(2, old2);
        assertEquals(array.size(), 3);
        assertEquals(array.get(1), 22);

        Object old1 = array.remove(0);
        assertEquals(1, old1);
        assertEquals(22, array.get(0));
        assertEquals(2, array.size());
    }
}
