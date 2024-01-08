package core.basesyntax;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int STARTING_CAPACITY = 10;
    private static final double INCREASE_FACTOR = 1.5;

    private T[] frame;
    private int size;

    public ArrayList() {
        frame = (T[]) new Object[STARTING_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == frame.length) {
            int newCapacity = (int) (frame.length * INCREASE_FACTOR);
            T[] newArray = (T[]) new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newArray[i] = frame[i];
            }
            frame = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return frame[currentIndex++];
            }
        };
    }

    private int findindexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, frame[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        frame[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacity();

        for (int i = size - 1; i >= index; i--) {
            frame[i + 1] = frame[i];
        }

        frame[index] = value;
        size++;
    }

    public void addAll(List<T> list) {
        if (list != null) {
            java.util.Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()) {
                T value = iterator.next();
                add(value);
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return frame[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        frame[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = frame[index];
        System.arraycopy(frame, index + 1, frame, index, size - index - 1);
        frame[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findindexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found");
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
}
