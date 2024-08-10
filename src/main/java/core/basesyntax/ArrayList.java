package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    private static final double CAPACITY_MULTIPLIER = 1.5;

    private int size;
    private T[] innerArray;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.size = 0;
        this.innerArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size + 1 >= innerArray.length) {
            addCapacity();
        }

        innerArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexValidity(index, true);

        if (size + 1 >= innerArray.length) {
            addCapacity();
        }

        moveElementsForward(index, innerArray);
        innerArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexValidity(index);

        return innerArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidity(index);

        innerArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidity(index);

        T elementToRemove = innerArray[index];
        moveElementsBackwards(index, innerArray);

        size--;

        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int elementIndex = findElementIndex(element, innerArray);

        if (elementIndex == -1) {
            throw new NoSuchElementException("Invalid element");
        }

        return remove(elementIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void addCapacity() {
        int newCapacity = (int) (innerArray.length * CAPACITY_MULTIPLIER);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(innerArray, 0, newArray, 0, innerArray.length);
        innerArray = newArray;
    }

    private void moveElementsBackwards(int index, T[] innerArray) {
        for (int i = index + 1; i < innerArray.length; i++) {
            innerArray[i - 1] = innerArray[i];
        }
    }

    private void moveElementsForward(int index, T[] innerArray) {
        for (int i = innerArray.length - 1; i > index; i--) {
            innerArray[i] = innerArray[i - 1];
        }
    }

    private int findElementIndex(T element, T[] innerArray) {
        for (int i = 0; i < innerArray.length; i++) {
            if (Objects.equals(innerArray[i], element)) {
                return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private void checkIndexValidity(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndexValidity(int index, boolean isAddOperation) {
        if (isAddOperation && (index > size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
