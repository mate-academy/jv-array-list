package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] currentArray;
    private int size;

    public ArrayList() {
        currentArray = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == currentArray.length) {
            increaseLength();
        }
        currentArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " don`t exist!");
        }
        if (size == currentArray.length) {
            increaseLength();
        }
        System.arraycopy(currentArray, index, currentArray, index + 1, size - index);
        currentArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("This list is null!");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexLengthCheck(index);
        return currentArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexLengthCheck(index);
        currentArray[index] = value;
    }

    @Override
    public T remove(int index) {
        indexLengthCheck(index);
        T temp = currentArray[index];
        System.arraycopy(currentArray, index + 1, currentArray, index, size - index - 1);
        --size;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentArray.length; i++) {
            if (compare(currentArray[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " don`t exist!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseLength() {
        int newLength = (int) (currentArray.length * MULTIPLIER);
        T[] tempArray = (T[]) new Object[newLength];
        System.arraycopy(currentArray, 0, tempArray, 0, size);
        currentArray = tempArray;
    }

    private void indexLengthCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " don`t exist!");
        }
    }

    private boolean compare(T firstElement, T secondElement) {
        return firstElement == secondElement
                || firstElement != null
                && firstElement.equals(secondElement);
    }
}
