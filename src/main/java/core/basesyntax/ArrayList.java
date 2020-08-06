package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = resize();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index!");
        }
        if (size == elementData.length) {
            elementData = resize();
        }
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
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
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return editArrayAndReturnObj(index);

    }

    @Override
    public T remove(T t) {
        int index = findRemovingElement(t);
        if (index != -1) {
            return editArrayAndReturnObj(index);
        }
        throw new NoSuchElementException("Element not found in collection!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] resize() {
        T[] currentArray = (T[]) new Object[(elementData.length * 3) / 2 + 1];
        System.arraycopy(elementData, 0, currentArray, 0, size);
        return currentArray;
    }

    private int findRemovingElement(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private T editArrayAndReturnObj(int index) {
        T element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        size--;
        return element;
    }

    private boolean checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong parameter!");
        }
        return true;
    }
}
