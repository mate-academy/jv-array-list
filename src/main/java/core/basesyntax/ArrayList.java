package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= data.length) {
            data = grow();
        }
        data[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " is out of bounds for the size "
                    + (size - 1));
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be positive number"
            );
        }
        return (T) data[index];
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
        return false;
    }

    private Object[] grow() {
        int oldSize = data.length;
        int newSize = (int) (oldSize * 1.5);
        Object[] newData = new Object[newSize];
        for (int i = 0; i < oldSize; i++) {
            newData[i] = data[i];
        }
        return newData;
    }
}
