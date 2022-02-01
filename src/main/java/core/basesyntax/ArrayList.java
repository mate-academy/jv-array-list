package core.basesyntax;

import static java.util.Arrays.copyOf;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_LENGTH = 10;
    private T[] arrayObjects;
    private int size;

    public ArrayList() {
        arrayObjects = (T[]) new Object[ARRAY_LENGTH];
    }

    private void increaseCapacity() {
        if (size == arrayObjects.length) {
            arrayObjects = copyOf(arrayObjects, (int) (size * 1.5));
        }
    }

    @Override
    public void add(T value) {
        if (size >= arrayObjects.length) {
            increaseCapacity();
        }
        arrayObjects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == arrayObjects.length) {
            increaseCapacity();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(arrayObjects, index, arrayObjects,
                    index + 1, size - index);
            arrayObjects[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception");
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
        if (index >= 0 && index < size) {
            return arrayObjects[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("index out of bound exception");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            arrayObjects[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T current = arrayObjects[index];
            System.arraycopy(arrayObjects, index + 1, arrayObjects, index, size - index - 1);
            size--;
            return current;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayObjects.length; i++) {
            if (Objects.equals(arrayObjects[i], element)) {
                System.arraycopy(arrayObjects, i + 1, arrayObjects, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Element doesn't exist on array");
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
