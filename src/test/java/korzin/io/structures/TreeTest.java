package korzin.io.structures;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TreeTest {

  @Test
  public void testHeightAndSize() {
    Tree<Integer> head = new Tree<>(111);
    Tree<Integer> lvl2node1 = new Tree<>(211);
    Tree<Integer> lvl2node2 = new Tree<>(212);
    Tree<Integer> lvl2node3 = new Tree<>(213);
    head.addChildren(Arrays.asList(lvl2node1, lvl2node2, lvl2node3));
    Tree<Integer> lvl3node1 = new Tree<>(3111);
    Tree<Integer> lvl3node2 = new Tree<>(3112);
    Tree<Integer> lvl3node3 = new Tree<>(3113);
    lvl2node1.addChildren(Arrays.asList(lvl3node1, lvl3node2, lvl3node3));
    Tree<Integer> lvl3node4 = new Tree<>(3131);
    Tree<Integer> lvl3node5 = new Tree<>(3132);
    Tree<Integer> lvl3node6 = new Tree<>(3133);
    lvl2node3.addChildren(Arrays.asList(lvl3node4, lvl3node5, lvl3node6));
    Tree<Integer> lvl4node1 = new Tree<>(31111);
    Tree<Integer> lvl4node2 = new Tree<>(31112);
    lvl3node1.addChildren(Arrays.asList(lvl4node1, lvl4node2));
    Tree<Integer> lvl4node3 = new Tree<>(31311);
    Tree<Integer> lvl4node4 = new Tree<>(31312);
    lvl3node4.addChildren(Arrays.asList(lvl4node3, lvl4node4));
    Tree<Integer> lvl5node1 = new Tree<>(313111);
    lvl4node3.addChild(lvl5node1);
    // height
    assertEquals((Integer) 5, head.height());
    assertEquals((Integer) 1, lvl2node2.height());
    assertEquals((Integer) 2, lvl4node3.height());
    assertEquals((Integer) 2, lvl3node1.height());
    // size
    assertEquals((Integer) 15, head.size());
    assertEquals((Integer) 7, lvl2node3.size());
  }
}
