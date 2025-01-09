package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 2;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public void rangeCheck() {
        if (size == elementData.length) {
            grow();
        }
    }

    public void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private Object[] grow() {
        int newCapacity = elementData.length * 3 / 2 + 1;
        elementData = Arrays.copyOf(elementData, newCapacity);
        return elementData;
    }

    public void checkIfElementExists(T element) {
        boolean elementFaunded = false;
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                elementFaunded = true;
                break;
            }
        }
        if (!elementFaunded) {
            throw new NoSuchElementException("Element not found in the array");
        }
    }

    @Override
    public void add(T value) {
        rangeCheck();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound ... )");
        }
        rangeCheck();
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        indexCheck(index);
        checkIfElementExistsByIndex(index);
        return (T) elementData[index];
    }

    private void checkIfElementExistsByIndex(int index) {
        if (elementData[index] == null) {
            throw new RuntimeException("There is no element by this index. Please call to admin to support");
        }
    }


    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        checkIfElementExists(element);
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null) || (element != null && element.equals(elementData[i]))) {
                T removedElement = remove(i);
                return removedElement;
            }
        }
        return null;
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
