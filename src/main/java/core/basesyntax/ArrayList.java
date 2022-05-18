package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void isElementExist(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                return;
            }
        }
        throw new NoSuchElementException("Element " + element + " not find");
    }

    private int isIndexExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elementData.length);
        }
        return index;
    }

    private Object[] increaseCapacity() {
        Object[] temp = new Object[(elementData.length * 2)];
        System.arraycopy(elementData, 0, temp, 0, elementData.length);
        return temp;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = increaseCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size == elementData.length) {
                elementData = increaseCapacity();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elementData.length);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexExist(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
        Object[] temp = elementData;
        elementData = new Object[temp.length - 1];
        final Object value = temp[index];
        System.arraycopy(temp, 0, elementData, 0, index);
        System.arraycopy(temp, index + 1, elementData, index, temp.length - index - 1);
        size--;
        return (T) value;
    }

    @Override
    public T remove(T element) {
        isElementExist(element);
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
