package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double RESIZE_COUNT = 1.5;
    private static final int START_SIZE = 10;
    private Object[] elementData;
    private int resizeValue;
    private int count;

    public ArrayList() {
        resizeValue = START_SIZE;
        elementData = new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        resize(count);
        elementData[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index == count) {
            add(value);
        } else {
            rangeCheckForCount(index);
            resize(count);
            System.arraycopy(elementData, index, elementData, index + 1, count - index);
            elementData[index] = value;
            count++;
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
        rangeCheckForCount(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForCount(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForCount(index);
        final Object deletedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, count - index - 1);
        count--;
        return (T) deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < count; i++) {
            if (checkValue(element, i)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("ELement not found " + element);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private void rangeCheckForCount(int index) {
        if (index >= count || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bound for length " + count);
        }
    }

    private void resize(int count) {
        if (count >= resizeValue) {
            Object[] localeData = elementData;
            elementData = new Object[resizeValue *= RESIZE_COUNT];
            System.arraycopy(localeData, 0, elementData, 0, localeData.length);
        }
    }

    private boolean checkValue(T element, int i) {
        return element == null ? element == elementData[i] : element.equals(elementData[i]);
    }
}
