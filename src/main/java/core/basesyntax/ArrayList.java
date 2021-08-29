package core.basesyntax;

public class ArrayList<T> implements List<T> {
    public static final int START_CAPACITY = 10;
    public static final String BOUNDS_EXCEPTION = "Out of bounds exception";
    public static final String NO_ELEMENT_EXCEPTION = "Element is not exist";
    public T[] array;
    int size;

    public ArrayList() {
        array = (T[]) new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length - 1) {
            resize();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > array.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION);
        }
        if (size == array.length) {
            resize();
        }
        System.arraycopy(array,index,array,index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > array.length) {
            resize();
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = array[index];

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

    private void resize() {
        int newLength = array.length + (array.length / 2);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(array,0,newArray,0,array.length);
        array = newArray;
    }

    public void checkIndex(int checkIndex) {
        if (checkIndex >= size || checkIndex < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION);
        }
    }
}
