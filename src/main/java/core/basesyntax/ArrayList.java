package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int maxSize = 10;
    private int size;
    private T[] array;
    private T[] arrayGrow;

    public ArrayList() {
        array = (T[]) new Object[maxSize];
    }

    public void resize() {
        if (size == maxSize) {
            maxSize = (int) (maxSize * 1.5);
            arrayGrow = (T[]) new Object[maxSize];
            System.arraycopy(array, 0, arrayGrow, 0, size);
            array = arrayGrow;
        }
    }

    @Override
    public void add(T value) {
        resize();
        for (int i = 0; i < size; i++) {
            if ((array[i] != null && array[i] == value)
                    || (array[i] != null && array[i].equals(value))) {
                array[i] = value;
                return;
            }
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception");
        }
        resize();
        if (index < size) {
            T[] arrayGrow = (T[]) new Object[maxSize];
            System.arraycopy(array, 0, arrayGrow, 0, index);
            arrayGrow[index] = value;
            System.arraycopy(array, index, arrayGrow, index + 1, size - index);
            array = arrayGrow;
            size++;
            return;
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (array.length < size + list.size()) {
            maxSize = (int) (maxSize * 1.5);
            arrayGrow = (T[]) new Object[maxSize];
            System.arraycopy(array, 0, arrayGrow, 0, size);
            array = arrayGrow;
        }
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        T[] arrayNew = (T[]) new Object[maxSize];
        final T result;
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception");
        }
        System.arraycopy(array, 0, arrayNew, 0, index);
        System.arraycopy(array, index + 1, arrayNew, index, size - index - 1);
        result = array[index];
        array = arrayNew;
        size--;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        T[] arrayNew = (T[]) new Object[maxSize];
        boolean resultElement = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                System.arraycopy(array, 0, arrayNew, 0, i);
                System.arraycopy(array, i + 1, arrayNew, i, size - i - 1);
                resultElement = true;
                array = arrayNew;
                size--;
                return element;
            }
            if ((element == array[i]) || element != null && array[i].equals(element)) {
                System.arraycopy(array, 0, arrayNew, 0, i);
                System.arraycopy(array, i + 1, arrayNew, i, size - i - 1);
                resultElement = true;
                array = arrayNew;
                size--;
                return element;
            }
        }
        if (resultElement == false) {
            throw new NoSuchElementException("No such element exception");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
