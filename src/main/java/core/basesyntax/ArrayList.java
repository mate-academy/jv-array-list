package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException(
                    "Index [" + index + "] is out of size [" + size + "]");
        }
        if (size == elementData.length) {
            resize();
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
        if (isValidIndex(index)) {
            return elementData[index];
        } else {
            throw new ArrayIndexOutOfBoundsException(
                    "Index [" + index + "] is out of size [" + size + "]");
        }
    }

    @Override
    public void set(T value, int index) {
        if (isValidIndex(index)) {
            elementData[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException(
                    "Index [" + index + "] is out of size [" + size + "]");
        }
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == elementData[i] || (elementData[i] != null && elementData[i].equals(t))) {
                remove(i);
                return t;
            }
        }
        throw new NoSuchElementException("Element with value [" + t + "] wasn't found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        T[] oldData = elementData;
        elementData = (T[]) new Object[oldData.length * 3 / 2 + 1];
        System.arraycopy(oldData, 0, elementData, 0, oldData.length);
    }

    private boolean isValidIndex(int index) {
        return index > -1 && index < size;
    }
}
