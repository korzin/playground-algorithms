package korzin.io.structures;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BiDirectListTest {

  @Test
  public void testLL() {

    BiDirectList<Integer> ll = new BiDirectList<>();

    assertTrue(ll.empty());
    assertEquals(0, ll.size());

    ll.pushFront(22);
    ll.pushFront(11);
    ll.pushBack(33);
    assertEquals(3, ll.size());

    assertEquals(new Integer(22), ll.getByIndex(1).getValue());
    assertFalse(ll.empty());

    assertEquals(new Integer(33), ll.topBack().getValue());
    assertEquals(3, ll.size());

    ll.popBack();
    assertEquals(new Integer(22), ll.topBack().getValue());
    ll.popFront();
    assertEquals(1, ll.size());

    ll.pushBack(33);
    ll.pushBack(44);
    ll.pushFront(11);

    assertEquals(4, ll.size());

    BiDirectList.Node<Integer> _22 = ll.getByIndex(2);

    ll.addAfter(_22, 333);

    ll.popBack();

    assertEquals(new Integer(333), ll.topBack().getValue());

    ll.addBefore(ll.getByIndex(3), 222);

    assertEquals(new Integer(222), ll.getByIndex(3).getValue());

    ll.erase(11);

    ll.erase(222);

    ll.erase(333);

    assertEquals(2, ll.size());

    ll.addBefore(ll.getByIndex(0), 11);

    assertEquals(new Integer(11), ll.topFront().getValue());

    assertTrue(ll.find(22));
    assertFalse(ll.find(222));

    ll.addBefore(ll.getByIndex(0), 0);
    ll.addAfter(ll.getByIndex(2), 23);
    ll.addAfter(ll.getByIndex(3), 33);
    ll.erase(11);

    ll.addBefore(ll.getByIndex(4), 43);
    ll.addBefore(ll.getByIndex(4), 42);
    ll.addBefore(ll.getByIndex(4), 41);

    assertEquals((Integer) 43, ll.getByIndex(6).getValue());
    assertEquals(ll.size(), 8);

    ll.add(4, 40);
    ll.add(4, 39);
    assertEquals((Integer) 39, ll.getByIndex(4).getValue());
    assertEquals((Integer) 43, ll.getByIndex(8).getValue());
    assertTrue(ll.find(333));
  }

  @Test
  public void testLLIterator() {
    BiDirectList<Integer> ll = new BiDirectList<>();

    ll.pushFront(111);
    ll.pushBack(222);
    ll.pushFront(333);
    ll.pushBack(444);

    for (Integer curr : ll) {
      System.out.println("<> " + curr);
    }

    Iterator<Integer> iter = ll.iterator();

    assertTrue(iter.hasNext());
    assertEquals((Integer) 333, iter.next());
    assertTrue(iter.hasNext());
    assertEquals((Integer) 111, iter.next());
    assertTrue(iter.hasNext());
    assertEquals((Integer) 222, iter.next());
    assertTrue(iter.hasNext());
    assertEquals((Integer) 444, iter.next());
    assertFalse(iter.hasNext());
  }
}
