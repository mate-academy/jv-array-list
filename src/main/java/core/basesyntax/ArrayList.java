package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double RESIZE_COUNT = 1.5;
    private Object[] elementData;
    private int startSize;
    private int count;

    public ArrayList() {
        startSize = 10;
        elementData = new Object[startSize];
    }

    @Override
    public void add(T value) {
        checkArrayLength(count);
        elementData[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index == count) {
            add(value);
        } else {
            rangeCheckForCount(index);
            checkArrayLength(count);
            Object[] localeOne = Arrays.copyOfRange(elementData, 0, index);
            Object[] localeTwo = Arrays.copyOfRange(elementData, index, count);
            System.arraycopy(localeOne, 0, elementData, 0, localeOne.length);
            System.arraycopy(localeTwo, 0, elementData, localeOne.length + 1, count - index);
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
        Object[] localeOne = Arrays.copyOfRange(elementData, 0, index);
        Object[] localeTwo = Arrays.copyOfRange(elementData, index + 1, count);
        System.arraycopy(localeOne, 0, elementData, 0, localeOne.length);
        System.arraycopy(localeTwo, 0, elementData, localeOne.length, localeTwo.length);
        count--;
        return (T) deletedElement;
    }

    @Override
    public T remove(T element) {
        boolean isDelete = false;
        for (int i = 0; i < count; i++) {
            if (checkValue(element, i)) {
                remove(i);
                isDelete = true;
                break;
            }
        }
        if (!isDelete) {
            throw new NoSuchElementException("ELement not found " + element);
        }
        return element;
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

    private void checkArrayLength(int count) {
        if (count >= startSize) {
            Object[] localeData = elementData;
            elementData = new Object[startSize *= RESIZE_COUNT];
            System.arraycopy(localeData, 0, elementData, 0, localeData.length);
        }
    }

    private boolean checkValue(T element, int i) {
        return element == null ? element == elementData[i] : element.equals(elementData[i]);
    }
}
