package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int default_capacity = 10;
    private Object[] elementData = new Object[default_capacity];
    private int size;

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size >= elementData.length) {
                elementData = grow();
            }
            for (int i = size; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You can't add element in this index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        int totalSize = size + list.size();
        if (totalSize > elementData.length) {
            elementData = grow(totalSize);
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size = totalSize;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("You can't get element in this index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be > 0");
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The non existent position");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be > 0");
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The non existent position");
        } else {
            Object oldValue = elementData[index];
            int numMoved = size - index - 1;
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            elementData[--size] = null;
            return (T) oldValue;
        }
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        int indexElement = -1;
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                indexElement = i;
                break;
            }
        }
        if (indexElement == -1) {
            throw new NoSuchElementException("Element not found");
        }
        return remove(indexElement);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        return elementData = Arrays.copyOf(elementData, elementData.length * 2);
    }

    private Object[] grow(int totalSize) {
        return elementData = Arrays.copyOf(elementData, totalSize);
    }
}
