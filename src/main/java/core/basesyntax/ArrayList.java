package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private int size = 0;
    private T[] elementData = (T[]) new Object[0];

    @Override
    public void add(T value) {
        if (elementData.length == 0) {
            elementData = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            if (size >= elementData.length) {
                int newLength = (int) (elementData.length * GROWTH_FACTOR);
                T[] array = (T[]) new Object[newLength];
                System.arraycopy(elementData, 0, array, 0, elementData.length);
                elementData = array;
            }
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index (" + index
                    + ") must be not smaller than 0 "
                    + "and not greater than size of array (" + size + ")");
        }
        add(value);
        T[] after = (T[]) new Object[size - index];
        System.arraycopy(elementData, index, after, 0, size - index);
        swap(index, size - 1);
        for (int i = index + 1; i < size; i++) {
            set(after[i - (index + 1)], i);
        }
    }

    private void swap(int firstElementIndex, int secondElementIndex) {
        T t1 = get(firstElementIndex);
        set(get(secondElementIndex), firstElementIndex);
        set(t1, secondElementIndex);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return elementData[index];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index (" + index
                    + ") must be not smaller than 0 "
                    + "and smaller than size of array (" + size + ")");
        }
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T t = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size -= 1;
        return t;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index < 0) {
            throw new NoSuchElementException("ArrayList contains no suitable element: " + element);
        }
        return remove(index);
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            T t = elementData[i];
            if ((t == element) || (t != null && t.equals(element))) {
                return i;
            }
        }
        return -1;
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
