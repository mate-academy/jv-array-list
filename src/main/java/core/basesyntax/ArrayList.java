package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == values.length) {
            grow();
        }
        System.arraycopy(values, index,
                values, index + 1,
                size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] a = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        int varLength = a.length;
        if (size == values.length) {
            grow();
        }
        System.arraycopy(a,0,values,size, varLength);
        size += varLength;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForAdd(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        System.arraycopy(values, index + 1,
                values, index,
                values.length - index - 1);
        values[values.length - 1] = null;
        size--;
        return (T) values;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(element)) {
                return remove(i);
            }
        }
        return (T) values;
    }

    public void grow() {
        T[] temp = (T[]) new Object[values.length];
        System.arraycopy(values, 0, temp,0, size);
        values = (T[]) new Object[values.length << 1];
        System.arraycopy(temp, 0, values,0, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't find element by index");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
    }
}
