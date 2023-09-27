package core.basesyntax;

import com.google.common.base.Objects;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        expandCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        expandCapacity();
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object[] elements = elementData;
        T oldElement = (T) elements[index];
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        elements[size = newSize] = null;
        return oldElement;
    }

    @Override
    public T remove(T element) {

        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element  not found");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandCapacity() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * 1.5);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElements, 0, size);
            elementData = newElements;
        }
    }

    private int indexOf(T element) {
        final Object[] elements = elementData;
        for (int i = 0; i < size; i++) {
            if (Objects.equal(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Excepted index is incorrect");
        }
    }
}
