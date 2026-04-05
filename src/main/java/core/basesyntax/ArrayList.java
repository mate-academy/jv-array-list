package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double GROW = 1.5;

    private Object[] innerArray = new Object[CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == innerArray.length) {
            Object[] innerArray2 = new Object[(int) (innerArray.length * GROW)];
            System.arraycopy(innerArray, 0, innerArray2, 0, size);
            this.innerArray = innerArray2;
        }
        innerArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == innerArray.length) {
            Object[] innerArray2 = new Object[(int) (innerArray.length * GROW)];
            System.arraycopy(innerArray, 0, innerArray2, 0, size - index);
            this.innerArray = innerArray2;
            add(value, index);
        } else if (index <= size && index >= 0) {
            System.arraycopy(innerArray, index, innerArray, index + 1,
                    innerArray.length - index - 1);
            innerArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("");
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
        if (index < size && index >= 0) {
            return (T) innerArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            innerArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T remV = (T) innerArray[index];
            System.arraycopy(innerArray, index + 1, innerArray, index,
                    size - index - 1);
            size--;
            return remV;
        } else {
            throw new ArrayListIndexOutOfBoundsException("");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (innerArray[i] == null) {
                    return remove(i);
                }
            } else if (element.equals(innerArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
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
