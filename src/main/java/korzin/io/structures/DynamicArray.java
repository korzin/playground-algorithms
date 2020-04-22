package korzin.io.structures;

import java.util.Arrays;

public class DynamicArray {

    private static final int INITIAL_CAPACITY = 2;

    private int size;
    private Object[] arr;

    public DynamicArray() {
        this(INITIAL_CAPACITY);
    }

    public DynamicArray(int initialCapacity){
        arr = new Object[initialCapacity];
        size = 0;
    }
    public DynamicArray(Object[] arr) {
        this.arr = arr;
        size = arr.length;
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    private void provideCapacity(int newSize){
        if(newSize > arr.length){
            int newLength = arr.length;
            do {
                newLength = newLength * 2;
            } while (newLength < newSize);
            arr = Arrays.copyOf(arr, newLength);
        }
    }

    public Object remove(int index) {
        Object toRemove = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        arr[--size] = null;
        return toRemove;
    }

    public boolean add(Object o) {
        provideCapacity(size + 1);
        arr[size++] = o;
        return true;
    }

    public Object get(int index) {
        return arr[index];
    }

    public Object set(int index, Object element) {
        Object prevValue = arr[index];
        arr[index] = element;
        return prevValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
