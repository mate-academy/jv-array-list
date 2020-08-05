package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int CAPACITY = 10;
    private T[] elementData;
    private int size = 0;
    private int currentCapacity;

    public ArrayList() {
        elementData = (T[]) new Object[CAPACITY];
        currentCapacity = CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == currentCapacity) {
            elementData = ensureCapacity();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == currentCapacity) {
            elementData = ensureCapacity();
        }
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("Wrong index!");
        }
        if (index < size) {
            System.arraycopy(elementData, index, elementData, index + 1, currentCapacity - size);
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= currentCapacity) {
            elementData = ensureCapacity();
        }
        addElementsFromListToArray(list);
    }

    @Override
    public T get(int index) {
        if (index < size) {
            return elementData[index];
        }
        throw new ArrayIndexOutOfBoundsException("Wrong parameter!");
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < size) {
            return editArrayAndReturnObj(index);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        int index = findRemovingElement(t);
        if (index != -1) {
            return editArrayAndReturnObj(index);
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

    private T[] ensureCapacity() {
        currentCapacity = (currentCapacity * 3) / 2 + 1;
        T[] currentArray = (T[]) new Object[currentCapacity];
        System.arraycopy(elementData, 0, currentArray, 0, size);
        return currentArray;
    }

    private void addElementsFromListToArray(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    private int findRemovingElement(T element) {
        for (int i = 0; i < elementData.length; i++) {
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
}
