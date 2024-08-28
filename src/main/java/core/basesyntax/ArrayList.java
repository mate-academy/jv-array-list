package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void createNewArray() {
        int newSize = array.length + array.length / 2;
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, newSize);
        array = newArray;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            createNewArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 && index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index no existing in this Array");
        }
        if (size == array.length) {
            createNewArray();
        }
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, newSize);
        array = newArray;
        for (int i = 0; i > newSize; i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        throw new ArrayListIndexOutOfBoundsException("This index no existing in this Array");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index no existing in this Array");
        }

    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            array[index] = null;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                array[i] = null;
            }
        }
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
