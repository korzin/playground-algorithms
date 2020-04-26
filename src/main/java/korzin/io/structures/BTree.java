package korzin.io.structures;

public class BTree<T extends Comparable<T>> {

    private T data;

    private BTree<T> parent;

    private BTree<T> left;
    private BTree<T> right;

    public BTree(T data) {
        this.data = data;
    }

    public BTree<T> getLeft() {
        return left;
    }

    public BTree<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public BTree<T> addLeft(T leftValue) {
        BTree<T> left = new BTree<>(leftValue);
        left.parent = this;
        this.left = left;
        return left;
    }

    public BTree<T> addRight(T rightValue) {
        BTree<T> right = new BTree<>(rightValue);
        right.parent = this;
        this.right = right;
        return right;
    }

    public int height() {
        int heightLeft = left == null ? 0 : left.height();
        int heightRight = right == null ? 0 : right.height();
        return 1 + Math.max(heightLeft,heightRight);
    }

    public int size() {
        int sizeLeft = left == null ? 0 : left.size();
        int sizeRight = right == null ? 0 : right.size();
        return 1 + sizeLeft + sizeRight;
    }

    public int depth() {
        BTree<T> curr = this;
        int depth = 0;
        while (curr.parent != null) {
            curr = curr.parent;
            depth++;
        }
        return depth;
    }
}
