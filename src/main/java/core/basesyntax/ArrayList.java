package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DAFAULT_CAPACITY = 10;
    private static final double GROW_FACTORY = 1.5;
    private int size;
    private T[] internalArray;

    public ArrayList() {
        internalArray = (T[]) new Object[DAFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        enaughCapacity(size + 1);
        internalArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
        growIfArrayFull();
        System.arraycopy(internalArray, index, internalArray,
                index + 1, size - index);
        internalArray[index] = value;
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
        checkIndex(index);
        return internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T toRemoveElement = internalArray[index];
        for (int i = index; i < size - 1; i++) {
            internalArray[i] = internalArray[i + 1];
        }
        size--;
        return toRemoveElement;
    }

    @Override
    public T remove(T element) {
        boolean notExistToRemove = true;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(internalArray[i], element)) {
                System.arraycopy(internalArray, i + 1, internalArray,
                        i, size - i);
                size--;
                notExistToRemove = false;
                return element;
            }
        }
        if (notExistToRemove) {
            throw new NoSuchElementException("Element "
                    + element + " nor exist in List");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void enaughCapacity(int needCapacity) {
        if (needCapacity >= internalArray.length) {
            T[] newArray = (T[]) new Object[(int) (internalArray.length * GROW_FACTORY)];
            System.arraycopy(internalArray, 0, newArray, 0, internalArray.length);
            internalArray = newArray;
        }
    }

    private void growIfArrayFull() {
        if (internalArray.length == size) {
            T[] newArray = (T[]) new Object[(int) (internalArray.length * GROW_FACTORY)];
            System.arraycopy(internalArray, 0, newArray, 0, internalArray.length);
            internalArray = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
    }
}
