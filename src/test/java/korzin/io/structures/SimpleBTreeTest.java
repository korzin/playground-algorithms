package korzin.io.structures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBTreeTest {

    @Test
    public void testBTree() {
        SimpleBTree<String> bTree = new SimpleBTree<>("HEAD");
        bTree.addLeft("L HEAD").addLeft("L L HEAD");
        SimpleBTree<String> right = bTree.addRight("R HEAD");
        SimpleBTree<String> rightLeft = right.addLeft("R L HEAD");
        SimpleBTree<String> deepestLeaf = rightLeft.addRight("R L R HEAD").addRight("R L R R HEAD");

        assertEquals(7, bTree.size());
        assertEquals(5, bTree.height());
        assertEquals(4, deepestLeaf.depth());

        assertEquals("R L R R HEAD", bTree.getRight().getLeft().getRight().getRight().getData());
    }

}
