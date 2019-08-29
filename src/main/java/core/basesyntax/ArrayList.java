package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void resizeArray() {
        Object[] newArrayList = Arrays.copyOf(elementData, elementData.length * 3 / 2);
        elementData = newArrayList;
    }

    private void resizeArray(int deltaSize) {
        Object[] newArrayList = Arrays.copyOf(elementData, elementData.length * 3 / 2 + deltaSize);
        elementData = newArrayList;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resizeArray();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("You cannot add an item to this position! ("
                    + index + ")");
        }
        if (size == elementData.length) {
            resizeArray();
        }
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size() == elementData.length) {
            resizeArray(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Item with this number does not exist!");
        }
        return  (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("You cannot set an item to this position!");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Item with this number does not exist!");
        }
        T result = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                size - index - 1);
        elementData[size--] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == t) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException("Item with this number does not exist!");
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

