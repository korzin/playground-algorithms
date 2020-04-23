package korzin.io.structures;


public class ArrayQueue<T> implements Queue<T> {

    private final T[] arr;
    private Integer pullIndex = 0;
    private Integer pushIndex = 0;

    @SuppressWarnings({"unchecked"})
    public ArrayQueue(int size) {
        arr = (T[]) new Object[size];
    }

    @Override
    public void enqueue(T value) {
        if(arr[pushIndex] != null) throw new IllegalStateException();
        arr[pushIndex] = value;
        pushIndex = pushIndex == arr.length - 1 ? 0 : pushIndex + 1;
    }

    @Override
    public boolean empty() {
        return pushIndex.equals(pullIndex);
    }

    @Override
    public T dequeue() {
        T resp = arr[pullIndex];
        arr[pullIndex] = null;
        pullIndex = pullIndex == arr.length - 1 ? 0 : pullIndex + 1;
        return resp;
    }
}
