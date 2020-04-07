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

    public ArrayList(int capacity) {
        elementData = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        assertIndex(index);
        ensureCapacity();
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
        assertIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        assertIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        assertIndex(index);
        T element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return element;
    }

    @Override
    public T remove(T t) {
        T element = null;
        for (int i = 0; i < size; i++) {
            if (t == elementData[i] || t != null && t.equals(elementData[i])) {
                element = remove(i);
            }
        }
        if (element == null && t != null) {
            throw new NoSuchElementException("Такого элемента нет!");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size + 1 > elementData.length) {
            T[] newElementData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void assertIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Указанный индекс превышает длину массива!");
        }
    }
}
