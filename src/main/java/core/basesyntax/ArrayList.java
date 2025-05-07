package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_SIZE_INCREASE = 1.5;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[]{};
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
            return;
        }
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            elementData = grow();
        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        T removeValue = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element such element");
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
        if (size == 0) {
            initDefaultCapacityArray();
        }

        int oldSize = elementData.length;
        int newSize = (int) (oldSize * DEFAULT_SIZE_INCREASE);
        Object[] newArray = new Object[newSize];
        System.arraycopy(elementData, 0, newArray, 0, size);

        return newArray;
    }

    private void initDefaultCapacityArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index == size || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index" + index + "! "
                    + "Index value must be less than size and greater -1");
        }
    }
}
