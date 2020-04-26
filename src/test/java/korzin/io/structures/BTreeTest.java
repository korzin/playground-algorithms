package korzin.io.structures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BTreeTest {

    @Test
    public void testBTree() {
        BTree<String> bTree = new BTree<>("HEAD");
        bTree.addLeft("L HEAD").addLeft("L L HEAD");
        BTree<String> right = bTree.addRight("R HEAD");
        BTree<String> rightLeft = right.addLeft("R L HEAD");
        BTree<String> deepestLeaf = rightLeft.addRight("R L R HEAD").addRight("R L R R HEAD");

        assertEquals(7, bTree.size());
        assertEquals(5, bTree.height());
        assertEquals(4, deepestLeaf.depth());

        assertEquals("R L R R HEAD", bTree.getRight().getLeft().getRight().getRight().getData());
    }

}
