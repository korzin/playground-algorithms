package korzin.io.structures;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BSTreeTest {

  @Test
  public void testSearchBTree() {

    BSTree<Integer, String> searchTree = BSTree.initTree(100, "HEAD");
    assertEquals(1, searchTree.height());
    assertEquals(1, searchTree.size());

    searchTree.add(500, "init: right");
    searchTree.add(10000, "init: right right");
    searchTree.add(2000, "init: right left");
    searchTree.add(2500, "init: right left right");
    searchTree.add(1500, "init: right left left");
    searchTree.add(1000, "init: right left left left");

    assertEquals(6, searchTree.height());

    assertEquals((Integer) 10000, searchTree.getRight().getRight().getData().getKey());
    assertEquals(
        (Integer) 1000,
        searchTree.getRight().getRight().getLeft().getLeft().getLeft().getData().getKey());

    searchTree.remove(1000);
    assertNull(searchTree.getRight().getRight().getLeft().getLeft().getLeft());
    searchTree.add(1000, "recreated 1000");
    assertNotNull(searchTree.getRight().getRight().getLeft().getLeft().getLeft());
    searchTree.add(2750, "2750");
    searchTree.remove(2500);
    assertEquals(
        (Integer) 2750, searchTree.getRight().getRight().getLeft().getRight().getData().getKey());
    searchTree.add(2500, "recreated 2500");
    searchTree.add(2400, "long left 2500 -> 2400");
    searchTree.add(2300, "long left for 2500 -> 2400 -> 2300");
    assertNotNull(searchTree.getRight().getRight().getLeft().getRight());
    assertNotNull(
        searchTree.getRight().getRight().getLeft().getRight().getLeft().getLeft().getLeft());

    searchTree.remove(2000);

    BSTree<Integer, String> node2750 = searchTree.getRight().getRight().getLeft();
    assertEquals((Integer) 2750, node2750.getData().getKey());
    assertNull(node2750.getRight());

    BSTree<Integer, String> node2500 = node2750.getLeft();
    assertEquals((Integer) 2500, node2500.getData().getKey());
    BSTree<Integer, String> node2400 = node2500.getLeft();
    assertEquals((Integer) 2400, node2400.getData().getKey());
    BSTree<Integer, String> node2300 = node2400.getLeft();
    assertEquals((Integer) 2300, node2300.getData().getKey());

    BSTree<Integer, String> node1500 = node2300.getLeft();
    assertEquals((Integer) 1500, node1500.getData().getKey());
    assertEquals((Integer) 1000, node1500.getLeft().getData().getKey());
  }

  @Test
  public void test_TraversalStrategy_IN_ORDER_BY_LOOP() {
    BSTree<Integer, Object> bsTree =
        BSTree.initTree(250, null, BSTree.TraversalStrategy.IN_ORDER_BY_LOOP);

    bsTree.add(240, null);
    bsTree.add(200, null);
    bsTree.add(150, null);

    bsTree.add(225, null);

    bsTree.add(300, null);
    bsTree.add(350, null);

    bsTree.add(750, null);
    bsTree.add(650, null);
    bsTree.add(600, null);
    bsTree.add(550, null);
    bsTree.add(505, null);

    bsTree.add(800, null);
    bsTree.add(850, null);
    bsTree.add(825, null);

    int i = 0;
    LinkedListQueue<Integer> expectedOrder =
        LinkedListQueue.fromList(
            Arrays.asList(
                150, 200, 225, 240, 250, 300, 350, 505, 550, 600, 650, 750, 800, 825, 850));

    for (BSTree<Integer, Object> entry : bsTree) {
      Integer key = entry.getData().getKey();
      System.out.println("<> " + key);
      assertEquals(expectedOrder.dequeue(), key);
      if (i++ > 100) {
        break;
      }
    }
  }
}
