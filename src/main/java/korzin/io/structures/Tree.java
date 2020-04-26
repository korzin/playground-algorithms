package korzin.io.structures;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    private T data;

    private List<Tree<T>> children = new ArrayList<>();

    Tree(T data) {
        this.data = data;
    }

    public void addChild(Tree<T> child) {
        children.add(child);
    }

    public void addChildren(List<Tree<T>> children) {
        this.children.addAll(children);
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer height() {
        if (children.isEmpty()) {
            return 1;
        } else {
            return 1 + children.stream().mapToInt(Tree::height).max().getAsInt();
        }
    }

    public Integer size() {
        if(children.isEmpty()){
            return 1;
        } else {
            return 1 + children.stream().mapToInt(Tree::size).sum();
        }
    }

    @Override
    public String toString() {
        return "data: " + data + ", childrenSize: " + children.size();
    }
}
