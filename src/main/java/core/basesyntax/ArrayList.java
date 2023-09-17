package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int count = 0;
    private Object[] arrayObj;

    public ArrayList() {
        arrayObj = new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arrayObj[count++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > count) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        ensureCapacity();
        for (int i = count; i > index; i--) {
            arrayObj[i] = arrayObj[i - 1];
        }
        arrayObj[index] = value;
        count++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return (T) arrayObj[index];
    }

    @Override

    public void set(T value, int index) {
        if (index < 0 || index >= count) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        arrayObj[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayListIndexOutOfBoundsException("Invalid element");
        }
        final T removedValue = (T) get(index);
        for (int i = index; i < count - 1; i++) {
            arrayObj[i] = arrayObj[i + 1];
        }
        arrayObj[count - 1] = null;
        count--;
        return removedValue;
    }

    public T remove(T element) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(arrayObj[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + element);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private void ensureCapacity() {
        if (count == arrayObj.length) {
            int newCapacity = arrayObj.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(arrayObj, 0, newArray, 0, count);
            arrayObj = newArray;
        }
    }
}

