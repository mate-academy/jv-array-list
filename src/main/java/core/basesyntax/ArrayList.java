package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_ARRAY_SIZE = 10;
    public static final String OUT_OF_BOUNDS_MESSAGE = "Index out of bounds";
    public static final String NO_SUCH_ELEMENT_MESSAGE = "No such element";
    private int size;

    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        elementDataGrow(size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (isEmpty()) {
            elementData[0] = value;
            size++;
            return;
        }
        elementDataGrow(size);
        if (checkIndex(index)) {
            System.arraycopy(elementData, index,
                    elementData, index + 1,
                    size - index);
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i <= list.size(); i++) {
            array[i] = list.remove(0);
        }
        if (list.size() + size >= elementData.length) {
            elementData = grow();
        }
        System.arraycopy(array, 0, elementData, size, array.length);
        size += array.length;
    }

    @Override
    public T get(int index) {
        if (!checkIndex(index + 1)) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index + 1)) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    @Override
    public T remove(int index) {
        T removedElement;
        if ((size == elementData.length) && index + 1 == size) {
            size--;
            removedElement = elementData[index];
            return removedElement;
        }
        if (checkIndex(index + 1)) {
            removedElement = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
            size--;
        } else {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        }
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        Integer removeIndex = null;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                removedElement = elementData[i];
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == null) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
        }
        System.arraycopy(elementData, removeIndex + 1,
                elementData, removeIndex, size - removeIndex);
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size + 1 == elementData.length;
    }

    private T[] grow() {
        return Arrays.copyOf(elementData, newCapacity(size));
    }

    private boolean checkIndex(int index) {
        return index <= size && index >= 0;
    }

    private int newCapacity(int oldCapacity) {
        return oldCapacity + (oldCapacity >> 1);
    }

    private void elementDataGrow(int size) {
        if (size == elementData.length) {
            elementData = grow();
        }
    }
}

