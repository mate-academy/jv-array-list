package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] list;
    private int size;
    private int outerSize;

    public ArrayList() {
        this.list = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
        this.outerSize = DEFAULT_SIZE;
    }

    public T[] grow() {
        outerSize *= 1.5;
        return list = Arrays.copyOf(list, outerSize);
    }

    @Override
    public void add(T value) {
        if (outerSize <= size) {
            list = grow();
            add(value);
        } else {
            list[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bounds for " + size);
        }
        if (outerSize <= size + 1) {
            list = grow();
            add(value, index);
        } else {
            for (int i = size(); i > index; i--) {
                list[i] = list[i - 1];
            }
            list[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> currentList) {
        if (currentList.size() >= outerSize) {
            outerSize = currentList.size();
            grow();
        }
        for (int i = 0; i < currentList.size(); i++) {
            add(currentList.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bounds for " + size);
        }
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " is out of bounds for size:" + size);
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " is out of bounds for size:" + size);
        }
        T element = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        if (index < size) {
            list[size() - 1] = null;
            size--;
        }
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (list[i] == element || list[i] != null && list[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element" + element);
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
