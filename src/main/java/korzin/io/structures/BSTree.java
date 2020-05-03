package korzin.io.structures;

import java.util.Objects;

public class BSTree<K extends Comparable<K>, V> {

  private final Data<K, V> data;

  private BSTree<K, V> parent;

  private BSTree<K, V> left;
  private BSTree<K, V> right;

  private BSTree(K key, V value) {
    this.data = new Data<>(key, value);
  }

  public static <SK extends Comparable<SK>, SV> BSTree<SK, SV> initTree(SK key, SV value) {
    return new BSTree<>(key, value);
  }

  // for test
  Data<K, V> getData() {
    return data;
  }

  // for test
  BSTree<K, V> getLeft() {
    return left;
  }

  // for test
  BSTree<K, V> getRight() {
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

  public BSTree<K, V> add(K key, V value) {
    Objects.requireNonNull(key);
    int compareResult = key.compareTo(data.key);
    if (compareResult > 0) {
      if (right == null) {
        right = new BSTree<>(key, value);
        right.parent = this;
      } else {
        right.add(key, value);
      }
    } else if (compareResult < 0) {
      if (left == null) {
        left = new BSTree<>(key, value);
        left.parent = this;
        return left;
      } else {
        left.add(key, value);
      }
    } else {
      data.value = value;
    }
    return this;
  }

  @Override
  public String toString() {
    return "BSTree{"
        + "data="
        + data
        + ", parent="
        + (parent != null)
        + ", left="
        + (left != null)
        + ", right="
        + (right != null)
        + '}';
  }

  static class Data<NK, NV> {
    private NK key;
    private NV value;

    Data(NK key, NV value) {
      this.key = key;
      this.value = value;
    }

    public NK getKey() {
      return key;
    }

    public NV getValue() {
      return value;
    }

    @Override
    public String toString() {
      return "key:" + key;
    }
  }
}
