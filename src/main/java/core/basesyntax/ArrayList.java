package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final String EXCEPTION_MESSAGE = " index doesn't exist";
    private T[] arrayList = (T[])new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resize();
        }

        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + EXCEPTION_MESSAGE);
        }

        if (size == arrayList.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }

        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > arrayList.length) {
            resize();
        }

        for (int i = 0; i < list.size(); i++) {
            arrayList[i + size] = list.get(i);
        }

        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index == size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + EXCEPTION_MESSAGE);
        }

        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index == size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + EXCEPTION_MESSAGE);
        }

        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + EXCEPTION_MESSAGE);
        }

        T removedElement = arrayList[index];

        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);

        arrayList[--size] = null;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, arrayList[i])) {
                T removedElement = arrayList[i];

                System.arraycopy(arrayList, i + 1, arrayList, i, size - i - 1);

                arrayList[--size] = null;

                return removedElement;
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

    public void resize() {
        int newCapacity = arrayList.length + (arrayList.length / 2);
        T[] newArrayList = (T[]) new Object[newCapacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
        arrayList = newArrayList;
    }
}
