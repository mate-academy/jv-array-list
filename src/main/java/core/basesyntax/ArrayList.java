package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }
    @Override
    public void add(T value) {
        elementData = growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] growIfArrayFull() {
        T[] grownArray = elementData;
        int oldCapacity = elementData.length;
        if (size == oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            grownArray = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, grownArray, 0, size);
        }
        return grownArray;
    }
}
