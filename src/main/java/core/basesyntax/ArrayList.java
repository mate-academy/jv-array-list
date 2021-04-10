package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String NO_SUCH_ELEMENT = "No such element in array!";
    private static final String INVALID_INDEX = "Invalid index!";
    private static final String INDEX_NOT_EXIST = "Index doesn't exist!";
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        changeArrayCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        changeArrayCapacity();
        indexExisting(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedIndex = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return deletedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX);
        }
    }

    private void changeArrayCapacity() {
        if (array.length == size) {
            T[] resizedArray = (T[]) new Object[size + (size / 2)];
            System.arraycopy(array, 0, resizedArray, 0, size);
            array = resizedArray;
        }
    }

    private void indexExisting(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_NOT_EXIST);
        }
    }
}
