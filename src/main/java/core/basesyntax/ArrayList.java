package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int arraySize;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        arraySize = 10;
        elementData = new Object[arraySize];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= arraySize) {
            Object[] obj = elementData;
            elementData = new Object[(int) (elementData.length + (elementData.length * 0.5))];
            for (int i = 0; i < obj.length; i++) {
                elementData[i] = obj[i];
            }
            arraySize = elementData.length;
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than the "
                    + "size of the array or uncorect");
        } else if (size == elementData.length) {
            Object[] obj = elementData;
            elementData = new Object[(int) (elementData.length + (elementData.length * 0.5))];
            for (int i = 0; i < obj.length; i++) {
                elementData[i] = obj[i];
            }
            arraySize = elementData.length;
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= arraySize) {
            Object[] obj = elementData;
            elementData = new Object[(int) (elementData.length + list.size())];
            for (int i = 0; i < obj.length; i++) {
                elementData[i] = obj[i];
            }
            arraySize = elementData.length;
        }
        for (int i = size, k = 0; k < list.size(); i++, k++) {
            elementData[i] = list.get(k);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater "
                    + "than the size of the array or uncorrect");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than the size "
                    + "of the array or uncorrect");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than "
                    + "the size of the array or uncorrect");
        }
        T element = (T) elementData[index];
        for (int i = index; i < size; i++) {
            if (index == size - 1) {
                elementData[i] = null;
            } else {
                elementData[i] = elementData[i + 1];
            }
        }
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        boolean foundElement = false;
        T removedElement = null;
        int position = 0;

        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && element == null) {
                foundElement = true;
                removedElement = null;
                position = i;
                break;
            } else if (elementData[i] != null && elementData[i].equals(element)) {
                foundElement = true;
                removedElement = (T) elementData[i];
                position = i;
                break;
            }
        }
        if (!foundElement) {
            throw new NoSuchElementException();
        }

        for (int i = position; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
