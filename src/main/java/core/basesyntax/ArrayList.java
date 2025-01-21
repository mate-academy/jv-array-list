package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_SIZE = 10;
    private static final double RESIZER = 1.5;
    private Object[] arrayList = new Object[INITIAL_SIZE];
    private int size = 0;

    private void grow() {
        arrayList = Arrays.copyOf(arrayList, (int)(arrayList.length * RESIZER));
    }

    private void boundCheck(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    @Override
    public void add(T value) {
        if (arrayList.length == size()) {
            grow();
        }
        arrayList[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        if (size() == arrayList.length) {
            grow();
        }
        Object[] newList = new Object[arrayList.length];

        for (int i = 0; i < index; i++) {
            newList[i] = arrayList[i];
        }
        newList[index] = value;
        for (int i = index; i < size(); i++) {
            newList[i + 1] = arrayList[i];
        }
        arrayList = newList;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (arrayList.length < size() + list.size()) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        boundCheck(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        boundCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        boundCheck(index);
        final Object oldElement = arrayList[index];
        for (int i = index; i < size() - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[size() - 1] = null;
        size--;
        return (T) oldElement;
    }

    @Override
    public T remove(T element) {
        boolean removed = false;
        Object[] newArrayList = new Object[arrayList.length];
        int newIndex = 0;

        for (int i = 0; i < size(); i++) {
            if (!removed && (get(i) != null ? get(i).equals(element) : get(i) == element)) {
                removed = true;
                size--;
                continue;
            }
            newArrayList[newIndex++] = arrayList[i];
        }
        arrayList = newArrayList;

        if (!removed) {
            throw new NoSuchElementException("No such element " + element);
        }

        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size() > 0);
    }
}