package core.basesyntax;

import java.util.Arrays;
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
        if (size >= elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < elementData.length) {
            resize(elementData, index, size, true);
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
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
        T removedElement = elementData[index];
        resize(elementData, index, size, false);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        if (!contains(t)) {
            throw new NoSuchElementException();
        }
        return remove(indexOf(t));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || (element != null && element.equals(elementData[i]))) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void resize(T[] array, int index, int size, boolean isAddingValue) {
        if (isAddingValue) {
            System.arraycopy(array, index, array, index + 1, size - index);
        } else {
            System.arraycopy(array, index + 1, array, index, size - 1);
        }
    }
}
