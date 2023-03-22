package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayOfElements;
    private int sizeOfElements = 0;

    public ArrayList() {
        this.arrayOfElements = new Object[DEFAULT_CAPACITY];
    }

    private Object[] growIfArrayFull() {
        Object[] grownArray = arrayOfElements;
        if (sizeOfElements == arrayOfElements.length) {
            grownArray = new Object[(int) (arrayOfElements.length * 1.5)];
            System.arraycopy(arrayOfElements, 0, grownArray, 0, sizeOfElements);
        }
        return grownArray;
    }

    private void checkForValidIndexValue(int index) {
        if (index < 0 || index > sizeOfElements - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid value of index.");
        }
    }

    @Override
    public void add(T value) {
        arrayOfElements = growIfArrayFull();
        for (int i = 0; i < arrayOfElements.length; i++) {
            if (arrayOfElements[i] == null && i >= sizeOfElements) {
                arrayOfElements[i] = value;
                sizeOfElements++;
                break;
            }
        }
    }

    @Override
    public void add(T value, int index) {
        arrayOfElements = growIfArrayFull();
        for (int i = 0; i < arrayOfElements.length; i++) {
            if (i == index && arrayOfElements[i] != null) {
                Object[] resultedArray = new Object[arrayOfElements.length + 1];
                System.arraycopy(arrayOfElements, 0, resultedArray, 0, i);
                resultedArray[index] = value;
                sizeOfElements++;
                System.arraycopy(arrayOfElements, index,
                        resultedArray, index + 1, sizeOfElements - index);
                arrayOfElements = resultedArray;
                break;
            }
            if (arrayOfElements[i] == null && index >= sizeOfElements) {
                arrayOfElements[index] = value;
                sizeOfElements++;
                break;
            }
        }
        if (index > sizeOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid value of index.");
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArray = new Object[sizeOfElements + list.size()];
        System.arraycopy(arrayOfElements, 0, newArray, 0, sizeOfElements);
        int j = 0;
        for (int i = sizeOfElements; i < newArray.length; i++) {
            while (j < list.size()) {
                newArray[i] = list.get(j);
                sizeOfElements++;
                j++;
                break;
            }
        }
        arrayOfElements = newArray;
    }

    @Override
    public T get(int index) {
        checkForValidIndexValue(index);
        return (T) arrayOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkForValidIndexValue(index);
        arrayOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForValidIndexValue(index);
        Object[] resultedArray = new Object[sizeOfElements - 1];
        System.arraycopy(arrayOfElements, 0, resultedArray, 0, index);
        System.arraycopy(arrayOfElements, index + 1,
                resultedArray, index, sizeOfElements - index - 1);
        T removedValue = get(index);
        arrayOfElements = resultedArray;
        sizeOfElements--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        boolean valueExist = false;
        for (int i = 0; i < arrayOfElements.length; i++) {
            if ((arrayOfElements[i] != null && arrayOfElements[i].equals(element))
                    || arrayOfElements[i] == element) {
                index = i;
                valueExist = true;
                break;
            }
        }
        if (!valueExist) {
            throw new NoSuchElementException("There is no such element present in the list.");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return sizeOfElements;
    }

    @Override
    public boolean isEmpty() {
        return sizeOfElements == 0;
    }
}
