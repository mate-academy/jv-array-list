package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    private void checkArraySize() {
        if (array.length == size) {
            Object[] addObject = array;
            array = new Object[size * 3 / 2 + 1];
            System.arraycopy(addObject, 0, array, 0, addObject.length);
        }
    }

    private void checkIndex(int someIndex) {
        if (someIndex < 0 || someIndex >= size) {
            throw new ArrayListIndexOutOfBoundsException("Not appropriate index.");
        }
    }

    @Override
    public void add(T value) {
        checkArraySize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            checkArraySize();
            System.arraycopy(array, index, array,index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't add. Not appropriate index. ");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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
        final T value = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        array[size] = null;

        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                array[size] = null;

                return element;
            }
        }

        throw new NoSuchElementException("Can't remove. No such element in the array.");
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
