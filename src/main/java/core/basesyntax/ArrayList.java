package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private int size;
    private Object[] array;

    ArrayList() {
        array = new Object[INITIAL_ARRAY_SIZE];
    }

    private Object[] copyOfRange(Object[] src, int start, int end) {
        Object[] result = new Object[end - start];
        for (int i = 0; i < result.length; i++) {
            result[i] = src[start + i];
        }
        return result;
    }
    private void grow(int value) {
        Object[] temp = new Object[size + value];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    private void grow() {
        grow(INITIAL_ARRAY_SIZE / 2);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The array index is out of bounds");
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The array index is out of bounds");
        }
    }

    @Override
    public void add(T value) {
        if (size == array.length) { grow(); }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (index == size) {
            add(value);
            return;
        }
        if (size == array.length) { grow(); }
        Object[] temp = copyOfRange(array, index, size);
        array[index] = value;
        for (int i = 0; i < temp.length; i++) {
            array[index + i + 1] = temp[i];
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (array.length - size < listSize) {
            grow(listSize);
        }
        for (int i = 0; i < listSize; i++) {
            array[size + i] = list.get(i);
        }
        size += listSize;
    }

    @Override
    public T get(int index)
    {
        checkIndex(index);
        return (T)array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        // temp logic
        size--;
        return (T)array[index];
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
}
