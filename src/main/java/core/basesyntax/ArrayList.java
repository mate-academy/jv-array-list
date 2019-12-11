package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private Object[] elementsData;
    private Object[] emptyArray = {};
    private int DEFAULT_SIZE = 10;
    private int size;

    public ArrayList() {
        elementsData = emptyArray;
        size = 0;
    }

    public ArrayList(int arraySize) {
        size = 0;
        if (arraySize > 0) {
            elementsData = new Object[arraySize];
        } else if (arraySize == 0) {
            elementsData = emptyArray;
        } else {
            throw new IllegalArgumentException("Incorrect size of array");
        }
    }

    @Override
    public void add(T value) {
        if (size == elementsData.length) {
            elementsData = grow();
        }
        elementsData[size] = value;
        size++;
    }

    private Object[] grow() {
        int oldCapacity = elementsData.length;
        if (oldCapacity > 0) {
            int newCapacity = (elementsData.length * 3) / 2 + 1;
            return elementsData = Arrays.copyOf(elementsData, newCapacity);
        }
        return elementsData = Arrays.copyOf(elementsData, Math.max(DEFAULT_SIZE, size + 1));
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == elementsData.length) {
            elementsData = grow();
        }
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
        size += 1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Check your index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        int numMoved = size - index - 1;
        T oldValue = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, numMoved);
        --size;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        int i = 0;
        if (t == null) {
            for (; i < size; i++) {
                if (elementsData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (; i < size; i++)
                if (t.equals(elementsData[i])) {
                    return remove(i);
                }
        }
        throw new NoSuchElementException();
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
