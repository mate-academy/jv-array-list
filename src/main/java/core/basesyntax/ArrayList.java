package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_FIRST_INDEX = 0;
    private static final double GROWTH_INDEX = 1.5;
    private int size;
    private int capacity;
    private T[] values;

    public ArrayList() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            values = grow();
        }
        values[size] = value;
    }

    @Override
    public void add(T value, int index) {
        values[index] = value;
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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        capacity = (int) (capacity * GROWTH_INDEX);
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(values, ARRAY_FIRST_INDEX, tempArray, ARRAY_FIRST_INDEX, size);

        return tempArray;
    }
}
