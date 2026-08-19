package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        checkCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            System.out.println("List doesn`t exist");
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            T o = list.get(i);
            checkCapacity();
            elementData[size] = o;
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removed = (T)elementData[index];
        Object[] firstPart = Arrays.copyOf(elementData, index);
        Object[] secondPart = Arrays.copyOfRange(elementData, index + 1, size);
        Object[] result = Arrays.copyOf(firstPart, firstPart.length + secondPart.length);
        System.arraycopy(secondPart, 0, result, firstPart.length, secondPart.length);
        elementData = result;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < size; ++i) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                index = i;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Such an element doesn`t exist");
        }
        final T removed = (T)elementData[index];
        Object[] firstPart = Arrays.copyOf(elementData, index);
        Object[] secondPart = Arrays.copyOfRange(elementData, index + 1, size);
        Object[] result = Arrays.copyOf(firstPart, firstPart.length + secondPart.length);
        System.arraycopy(secondPart, 0, result, firstPart.length, secondPart.length);
        elementData = result;
        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return index;
    }

    public void checkCapacity() {
        if (elementData.length == size) {
            int newCapacity = elementData.length + (elementData.length / 2);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
}
