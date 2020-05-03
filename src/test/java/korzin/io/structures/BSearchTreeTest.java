package korzin.io.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class BSearchTreeTest {

  @Test
  public void testSearchBTree() {

    BSTree<Integer, String> searchTree = BSTree.initTree(100, "I'm the HEAD");
    assertEquals(1, searchTree.height());
    assertEquals(1, searchTree.size());

    searchTree.add(500, "right");
    searchTree.add(10000, "right right");
    searchTree.add(2000, "right left");
    searchTree.add(2500, "right left right");
    searchTree.add(1500, "right left left");
    searchTree.add(1000, "right left left left");

    assertEquals(6, searchTree.height());

    assertEquals("right right", searchTree.getRight().getRight().getData().getValue());
    assertEquals(
        "right left left left",
        searchTree.getRight().getRight().getLeft().getLeft().getLeft().getData().getValue());
  }
}
