package korzin.io.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BSTree<K extends Comparable<K>, V> implements Iterable<BSTree<K, V>> {

  private final Data<K, V> data;
  private DepthFirstTraversalStrategy<K, V> iteratorTraversalStrategy;
  private BSTree<K, V> parent;
  private BSTree<K, V> left;
  private BSTree<K, V> right;

  private BSTree(K key, V value) {
    this(key, value, new DepthFirstTraversalStrategyInOrderByLoop<>());
  }

  private BSTree(K key, V value, DepthFirstTraversalStrategy<K, V> iteratorTraversalStrategy) {
    this.data = new Data<>(key, value);
    this.iteratorTraversalStrategy = iteratorTraversalStrategy;
  }

  public static <K extends Comparable<K>, V> BSTree<K, V> initTree(K key, V value) {
    return new BSTree<>(key, value);
  }

  public static <K extends Comparable<K>, V> BSTree<K, V> initTree(
      K key, V value, DepthFirstTraversalStrategy<K, V> traversalStrategy) {
    return new BSTree<>(key, value, traversalStrategy);
  }

  public void setIteratorTraversalStrategy(
      DepthFirstTraversalStrategy<K, V> iteratorTraversalStrategy) {
    this.iteratorTraversalStrategy = iteratorTraversalStrategy;
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

      boolean isFirst = true;
      BSTree<K, V> curr = BSTree.this;

      @Override
      public boolean hasNext() {
        return iteratorTraversalStrategy.getSuccessor(curr, isFirst) != null;
      }

      @Override
      public BSTree<K, V> next() {
        BSTree<K, V> next = iteratorTraversalStrategy.getSuccessor(curr, isFirst);
        if (next == null) {
          throw new IllegalStateException(
              "No elements left in a tree. "
                  + "Use TraversalStrategy::hasNext method to check existence.");
        }
        if (isFirst) {
          isFirst = false;
        }
        curr = next;
        return next;
      }
    };
  }

  private BSTree<K, V> getSuccessor() {
    return iteratorTraversalStrategy.getSuccessor(this, false);
  }

  public interface DepthFirstTraversalStrategy<K extends Comparable<K>, V> {
    BSTree<K, V> getSuccessor(BSTree<K, V> node, boolean shouldFindStart);
  }

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

  public static class DepthFirstTraversalStrategyInOrderByLoop<K extends Comparable<K>, V>
      implements DepthFirstTraversalStrategy<K, V> {

    public BSTree<K, V> getSuccessor(BSTree<K, V> node, boolean shouldFindStart) {
      if (shouldFindStart) {
        BSTree<K, V> lowest = node.getLowest();
        return lowest != null ? lowest : node;
      }
      return doGetSuccessor(node);
    }

    private BSTree<K, V> doGetSuccessor(BSTree<K, V> node) {
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

  public static class DepthFirstTraversalStrategyPreOrder<K extends Comparable<K>, V>
      implements DepthFirstTraversalStrategy<K, V> {

    @Override
    public BSTree<K, V> getSuccessor(BSTree<K, V> node, boolean shouldFindStart) {
      if (shouldFindStart) {
        return node;
      } else {
        return doGetSuccessor(node, null);
      }
    }

    private BSTree<K, V> doGetSuccessor(BSTree<K, V> curr, BSTree<K, V> prev) {
      if (curr == null) {
        return null;
      } else if (prev == null || prev == curr.parent) {
        if (curr.left != null) {
          return curr.left;
        } else if (curr.right != null) {
          return curr.right;
        } else {
          return doGetSuccessor(curr.parent, curr);
        }
      } else if (prev == curr.left && curr.right != null) {
        return curr.right;
      } else {
        return doGetSuccessor(curr.parent, curr);
      }
    }
  }

  public static class DepthFirstTraversalStrategyPostOrder<K extends Comparable<K>, V>
      implements DepthFirstTraversalStrategy<K, V> {

    @Override
    public BSTree<K, V> getSuccessor(BSTree<K, V> node, boolean shouldFindStart) {
      if (shouldFindStart) {
        BSTree<K, V> lowest = node.getLowest();
        return lowest != null ? lowest : node;
      }
      return recGetSuccessor(node, null);
    }

    private BSTree<K, V> recGetSuccessor(BSTree<K, V> curr, BSTree<K, V> prev) {
      if (curr == null) {
        return null;
      } else if (prev == null) {
        return recGetSuccessor(curr.parent, curr);
      } else if (prev == curr.parent) {
        if (curr.left != null) {
          return recGetSuccessor(curr.left, curr);
        } else if (curr.right != null) {
          return recGetSuccessor(curr.right, curr);
        } else {
          return curr;
        }
      } else if (prev == curr.left) {
        if (curr.right == null) {
          return curr;
        } else {
          return recGetSuccessor(curr.right, curr);
        }
      } else if (prev == curr.right) {
        return curr;
      } else {
        throw new IllegalStateException("Cannot handle iterator position.");
      }
    }
  }
}
