package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void increaseArrayCapacity() {
        T newCapacityArr = (T) new Object[size + size << 1];
        System.arraycopy(elementData, 0, newCapacityArr, 0, size);
        elementData = (T[]) newCapacityArr;
    }

    private boolean arrayContains(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && value == null
                    || value != null && value.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }

    private int indexOfValue(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && value == null
                    || value != null && value.equals(elementData[i])) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Such element does not exist!");
        }
        return index;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            increaseArrayCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > elementData.length || index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        } else {
            T[] newData = (T[]) new Object[elementData.length + 1];
            for (int i = 0; i < index; i++) {
                newData[i] = (T) elementData[i];
            }
            newData[index] = value;
            for (int j = index; j < size; j++) {
                newData[j + 1] = (T) elementData[j];
            }
            elementData = newData;
        }
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        T[] result = (T[]) Arrays.copyOf(elementData, size + listArray.length);
        System.arraycopy(listArray, 0, result, size, listArray.length);
        elementData = result;
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0 && index < elementData.length) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index > elementData.length || index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (size == elementData.length) {
            increaseArrayCapacity();
        }
        if (index > elementData.length || index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist!");
        } else {
            final T returnedElement = get(index);
            T[] newData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newData, 0, index);
            System.arraycopy(elementData, index + 1, newData, index, size - index);
            elementData = newData;
            size = size - 1;
            return returnedElement;
        }
    }

    @Override
    public T remove(T element) {
        if (!arrayContains(element)) {
            throw new NoSuchElementException("Such element does not exist!");
        } else {
            return remove(indexOfValue(element));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
