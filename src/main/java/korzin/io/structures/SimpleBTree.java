package korzin.io.structures;

public class SimpleBTree<T extends Comparable<T>> {

  private T data;

  private SimpleBTree<T> parent;

  private SimpleBTree<T> left;
  private SimpleBTree<T> right;

  public SimpleBTree(T data) {
    this.data = data;
  }

  public SimpleBTree<T> getLeft() {
    return left;
  }

  public SimpleBTree<T> getRight() {
    return right;
  }

  public T getData() {
    return data;
  }

  public SimpleBTree<T> addLeft(T leftValue) {
    SimpleBTree<T> left = new SimpleBTree<>(leftValue);
    left.parent = this;
    this.left = left;
    return left;
  }

  public SimpleBTree<T> addRight(T rightValue) {
    SimpleBTree<T> right = new SimpleBTree<>(rightValue);
    right.parent = this;
    this.right = right;
    return right;
  }

  public int height() {
    int heightLeft = left == null ? 0 : left.height();
    int heightRight = right == null ? 0 : right.height();
    return 1 + Math.max(heightLeft, heightRight);
  }

  public int size() {
    int sizeLeft = left == null ? 0 : left.size();
    int sizeRight = right == null ? 0 : right.size();
    return 1 + sizeLeft + sizeRight;
  }

  public int depth() {
    SimpleBTree<T> curr = this;
    int depth = 0;
    while (curr.parent != null) {
      curr = curr.parent;
      depth++;
    }
    return depth;
  }
}
