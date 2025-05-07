package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    public void resize() {
        if (size == arrayList.length) {
            T[] arrayNewList = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(arrayList, 0, arrayNewList, 0, size);
            arrayList = arrayNewList;
        }
    }

    @Override
    public void add(T value) {
        resize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            resize();
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
            arrayList[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("ArrayList "
                    + index + " OutOfBoundsException");
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
            return arrayList[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("ArrayList "
                    + index + " OutOfBoundsException");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            arrayList[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("ArrayList "
                    + index + " OutOfBoundsException");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removedElement = arrayList[index];
            System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("ArrayList "
                    + index + " OutOfBoundsException");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i] || (element != null && element.equals(arrayList[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn`t exist");
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
