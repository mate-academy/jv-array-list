package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final int INITIAL_SIZE = 10;

    private T[] array = (T[]) new Object[INITIAL_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        checking();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        generateIndexException(index < 0);
        checking();
        if (index == size) {
            add(value);
        } else {
            T t = array[index];
            generateIndexException(t == null);
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        generateIndexException(index >= size || index < 0);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        generateIndexException(index >= size || index < 0);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        generateIndexException(index >= size || index < 0);
        if (index == size - 1) {
            T t = array[index];
            array[index] = null;
            size--;
            return t;
        }
        T t = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return t;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null && array[i] == null) {
                size--;
                return null;
            }
            if (array[i] != null && array[i].equals(element)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length - 1;
    }

    private T[] createNewArray() {
        T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private void checking() {
        if (isFull()) {
            array = createNewArray();
        }
    }

    private void generateIndexException(boolean index) {
        if (index) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        }
    }
}
