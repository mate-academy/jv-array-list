package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] elementData = (T[]) new Object[size];

    @Override
    public void add(T value) {
        if (elementData.length == 0) {
            elementData = (T[]) new Object[DEFAULT_CAPACITY];
            add(value);
        } else {
            if (size < elementData.length) {
                elementData[size++] = value;
            } else {
                int newLength = elementData.length + elementData.length / 2;
                elementData = Arrays.copyOf(elementData, newLength);
                add(value);
            }
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index (" + index
                    + ") must be not smaller than 0 "
                    + "and not greater than size of array (" + size + ")");
        }
        add(value);
        T[] after = Arrays.copyOfRange(elementData, index, size - 1);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index (" + index
                    + ") must be not smaller than 0 "
                    + "and smaller than size of array (" + size + ")");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index (" + index
                    + ") must be not smaller than 0 "
                    + "and smaller than size of array (" + size + ")");
        }
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index (" + index
                    + ") must be not smaller than 0 "
                    + "and smaller than size of array (" + size + ")");
        }
        T t = get(index);
        size -= 1;
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        return t;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index < 0) {
            throw new NoSuchElementException("ArrayList contains no suitable element");
        }
        return remove(index);
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            T t = elementData[i];
            if (Objects.equals(t, element)) {
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
