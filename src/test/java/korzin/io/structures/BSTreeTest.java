package korzin.io.structures;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BSTreeTest {

  @Test
  public void testSearchBTree() {

    BSTree<Integer, String> bst = BSTree.initTree(100, "HEAD");
    assertEquals(1, bst.height());
    assertEquals(1, bst.size());

    bst.add(500, "init: right");
    bst.add(10000, "init: right right");
    bst.add(2000, "init: right left");
    bst.add(2500, "init: right left right");
    bst.add(1500, "init: right left left");
    bst.add(1000, "init: right left left left");

    assertEquals(6, bst.height());

    assertEquals((Integer) 10000, bst.getRight().getRight().getData().getKey());
    assertEquals(
        (Integer) 1000, bst.getRight().getRight().getLeft().getLeft().getLeft().getData().getKey());

    bst.remove(1000);
    assertNull(bst.getRight().getRight().getLeft().getLeft().getLeft());
    bst.add(1000, "recreated 1000");
    assertNotNull(bst.getRight().getRight().getLeft().getLeft().getLeft());
    bst.add(2750, "2750");
    bst.remove(2500);
    assertEquals((Integer) 2750, bst.getRight().getRight().getLeft().getRight().getData().getKey());
    bst.add(2500, "recreated 2500");
    bst.add(2400, "long left 2500 -> 2400");
    bst.add(2300, "long left for 2500 -> 2400 -> 2300");
    assertNotNull(bst.getRight().getRight().getLeft().getRight());
    assertNotNull(bst.getRight().getRight().getLeft().getRight().getLeft().getLeft().getLeft());

    bst.remove(2000);

    BSTree<Integer, String> node2750 = bst.getRight().getRight().getLeft();
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

  private BSTree<Integer, Object> generateComplexBSTree1() {
    BSTree<Integer, Object> bsTree = BSTree.initTree(250, null);

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
    return bsTree;
  }

  @Test
  public void test_TraversalStrategy_InOrder() {
    BSTree<Integer, Object> bsTree = generateComplexBSTree1();

    bsTree.setIteratorDepthFirstStrategy(new BSTree.DepthFirstTraversalStrategyInOrderByLoop<>());

    LinkedListQueue<Integer> expectedOrder =
        LinkedListQueue.fromList(
            Arrays.asList(
                150, 200, 225, 240, 250, 300, 350, 505, 550, 600, 650, 750, 800, 825, 850));

    int i = 0;
    for (BSTree<Integer, Object> entry : bsTree) {
      Integer key = entry.getData().getKey();
      assertEquals(expectedOrder.dequeue(), key);
      if (i++ > 100) {
        break;
      }
    }
    assertEquals(15, i);
  }

  @Test
  public void test_TraversalStrategy_PreOrder() {
    BSTree<Integer, Object> bsTree = generateComplexBSTree1();

    bsTree.setIteratorDepthFirstStrategy(new BSTree.DepthFirstTraversalStrategyPreOrder<>());

    LinkedListQueue<Integer> expectedOrder =
        LinkedListQueue.fromList(
            Arrays.asList(
                250, 240, 200, 150, 225, 300, 350, 750, 650, 600, 550, 505, 800, 850, 825));
    int i = 0;

    //    for (BSTree<Integer, Object> entry : bsTree) {
    //      System.out.println("<> " + entry.getData().getKey());
    //    }
    for (BSTree<Integer, Object> entry : bsTree) {
      Integer key = entry.getData().getKey();
      assertEquals(expectedOrder.dequeue(), key);
      if (i++ > 100) {
        break;
      }
    }
    assertEquals(15, i);
  }

  @Test
  public void test_TraversalStrategy_PostOrder() {
    BSTree<Integer, Object> bsTree = generateComplexBSTree1();

    bsTree.setIteratorDepthFirstStrategy(new BSTree.DepthFirstTraversalStrategyPostOrder<>());

    LinkedListQueue<Integer> expectedOrder =
        LinkedListQueue.fromList(
            Arrays.asList(
                150, 225, 200, 240, 505, 550, 600, 650, 825, 850, 800, 750, 350, 300, 250));
    int i = 0;

    //    for (BSTree<Integer, Object> entry : bsTree) {
    //      System.out.println("<> " + entry.getData().getKey());
    //    }
    for (BSTree<Integer, Object> entry : bsTree) {
      Integer key = entry.getData().getKey();
      assertEquals(expectedOrder.dequeue(), key);
      if (i++ > 100) {
        break;
      }
    }
    assertEquals(15, i);
  }

  @Test
  public void test_BreathFirstTraversal() {
    BSTree<Integer, Object> bsTree = generateComplexBSTree1();

    LinkedListQueue<Integer> expectedOrder =
        LinkedListQueue.fromList(
            Arrays.asList(
                250, 240, 300, 200, 350, 150, 225, 750, 650, 800, 600, 850, 550, 825, 505));
    int i = 0;

    Queue<BSTree<Integer, Object>> actualOrder = bsTree.asBreadthFirstQueue();
    while (!actualOrder.empty()) {
      assertEquals(expectedOrder.dequeue(), actualOrder.dequeue().getData().getKey());
      if (i++ > 100) {
        break;
      }
    }
    assertEquals(15, i);
  }
}
