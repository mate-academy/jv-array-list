package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int INITIAL_CAPACITY = 10;
    private int currentNumberOfMembers = 0;
    private Object[] elementData;

    ArrayList() {
        elementData = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (currentNumberOfMembers >= elementData.length) {
            grow();
        }
        elementData[currentNumberOfMembers++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index exception");
        }
        if (currentNumberOfMembers >= elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData,
                index + 1, currentNumberOfMembers - index);
        elementData[index] = value;
        currentNumberOfMembers++;
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
        return (T) elementData[index];

    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData,
                index, currentNumberOfMembers - index - 1);
        currentNumberOfMembers--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentNumberOfMembers; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return remove(i);

            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return currentNumberOfMembers;
    }

    @Override
    public boolean isEmpty() {
        return currentNumberOfMembers == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index exception");
        }
    }

    private void grow() {
        double increaseValue = 1.5;
        Object[] temp = elementData;
        INITIAL_CAPACITY *= increaseValue;
        elementData = new Object[INITIAL_CAPACITY];
        System.arraycopy(temp, 0, elementData, 0, temp.length);
    }
}
