package korzin.io.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BSTree<K extends Comparable<K>, V> implements Iterable<BSTree<K, V>> {

  private final Data<K, V> data;
  private TraversalStrategy<K, V> traversalStrategy;
  private BSTree<K, V> parent;
  private BSTree<K, V> left;
  private BSTree<K, V> right;

  private BSTree(K key, V value) {
    this(key, value, new TraversalStrategyInOrderByLoop<>());
  }

  private BSTree(K key, V value, TraversalStrategy<K, V> traversalStrategy) {
    this.data = new Data<>(key, value);
    this.traversalStrategy = traversalStrategy;
  }

  public static <K extends Comparable<K>, V> BSTree<K, V> initTree(K key, V value) {
    return new BSTree<>(key, value);
  }

  public static <K extends Comparable<K>, V> BSTree<K, V> initTree(
      K key, V value, TraversalStrategy<K, V> traversalStrategy) {
    return new BSTree<>(key, value, traversalStrategy);
  }

  public void setTraversalStrategy(TraversalStrategy<K, V> traversalStrategy) {
    this.traversalStrategy = traversalStrategy;
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

  private BSTree<K, V> getLowest() {
    BSTree<K, V> curr = this;
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }

  public V remove(K key) {
    Objects.requireNonNull(key);

    int compareResult = key.compareTo(data.getKey());
    if (compareResult > 0) {
      if (right == null) {
        throw new NoSuchElementException();
      } else {
        return right.remove(key);
      }
    } else if (compareResult < 0) {
      if (left == null) {
        throw new NoSuchElementException();
      } else {
        return left.remove(key);
      }
    } else {
      V returnValue = data.getValue();
      BSTree<K, V> replacement;
      if (right != null) {
        replacement = right;
        if (left != null) {
          BSTree<K, V> lowest = replacement.getLowest();
          if (lowest == null) {
            replacement.left = left;
          } else {
            lowest.left = left;
          }
        }
      } else if (left != null) {
        replacement = left;
      } else {
        replacement = null;
      }

      if (parent.left == this) {
        parent.left = replacement;
      } else if (parent.right == this) {
        parent.right = replacement;
      } else {
        throw new IllegalStateException("Parent doesn't refer to current node");
      }
      return returnValue;
    }
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

  @Override
  public Iterator<BSTree<K, V>> iterator() {
    return new Iterator<BSTree<K, V>>() {
      BSTree<K, V> lowest = BSTree.this.getLowest();
      final BSTree<K, V> startNode = lowest != null ? lowest : BSTree.this;

      boolean isFirst = true;
      BSTree<K, V> curr = startNode;

      @Override
      public boolean hasNext() {
        BSTree successor = traversalStrategy.getSuccessor(curr);
        return isFirst || traversalStrategy.getSuccessor(curr) != null;
      }

      @Override
      public BSTree<K, V> next() {
        if (isFirst) {
          isFirst = false;
          return curr;
        }
        BSTree<K, V> next = traversalStrategy.getSuccessor(curr);
        if (next == null) {
          throw new IllegalStateException(
              "No elements left in a tree. "
                  + "Use TraversalStrategy::hasNext method to check existence.");
        }
        curr = next;
        return next;
      }
    };
  }

  private <K extends Comparable<K>, V> BSTree<K, V> getSuccessor(BSTree<K, V> node) {
    throw new RuntimeException("Not implemented");
  }

  public interface TraversalStrategy<K extends Comparable<K>, V> {
    BSTree<K, V> getSuccessor(BSTree<K, V> node);
  }

  //    IN_ORDER_BY_RECURSION,
  //    POST_ORDER,
  //    LEVEL_ORDER;

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

  public static class TraversalStrategyInOrderByLoop<K extends Comparable<K>, V>
      implements TraversalStrategy<K, V> {

    public BSTree<K, V> getSuccessor(BSTree<K, V> node) {
      if (node.right != null) {
        BSTree<K, V> curr = node.right;
        while (curr.left != null) {
          curr = curr.left;
        }
        return curr;
      } else {
        BSTree<K, V> parent = node.parent;
        BSTree<K, V> child = node;
        while (parent != null && parent.right == child) {
          child = parent;
          parent = parent.parent;
        }
        return parent;
      }
    }
  }
}
