package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double NEW_ARRAY_CAPACITY_FACTOR = 1.5;
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
        if (index == size) {
            add(value);
            return;
        }
        rangeCheckForAdd(index);
        checkCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkCapacity();
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T oldElementData;
        try {
            oldElementData = (T) elementData[index];
            rangeCheckForAdd(index);
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
        }
        --size;
        return oldElementData;
    }

    @Override
    public T remove(T element) {
        int amountOfElement = 0;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                amountOfElement++;
                int amountOfCopiedNumbers = size - i - 1;
                System.arraycopy(elementData, i + 1, elementData, i, amountOfCopiedNumbers);
            }
        }
        if (amountOfElement == 0) {
            throw new NoSuchElementException("No such element " + element + " present.");
        }
        --size;
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

    private void rangeCheckForAdd(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
        }
    }

    private void grow() {
        int newArrayCapacity = (int) (elementData.length * NEW_ARRAY_CAPACITY_FACTOR);
        Object[] newArrayList = new Object[newArrayCapacity];
        System.arraycopy(elementData, 0, newArrayList, 0, size);
        elementData = newArrayList;
    }

    private void checkCapacity() {
        if (size == elementData.length) {
            grow();
        }
    }
}
