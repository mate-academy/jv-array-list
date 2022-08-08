package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= arrayList.length) {
            ensureCapacity();
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can not add the element. "
            + "Index out of bound exception " + index);
        }
        if (size>= arrayList.length) {
            ensureCapacity();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        int elementOfIndex = checkIndex(index);
        return (T) arrayList[elementOfIndex];
    }

    @Override
    public void set(T value, int index) {
        int newElementIndex = checkIndex(index);
        arrayList[newElementIndex] = value;
    }

    @Override
    public T remove(int index) {
        int indexOfElement = checkIndex(index);
        T elementOfIndex = (T) arrayList[indexOfElement];
        int newSize = size - indexOfElement - 1;
        if (newSize > 0) {
            System.arraycopy(arrayList, indexOfElement + 1, arrayList, indexOfElement, newSize);
        }
        arrayList[--size] = null;
        return elementOfIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i] || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There are no this element " + element + " in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public int checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bound index " + index);
        }
        return index;
    }

    private void ensureCapacity() {
        Object[] newArrayList = new Object[arrayList.length + arrayList.length / 2];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        arrayList = newArrayList;
    }
}
