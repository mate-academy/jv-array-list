package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double RESIZE_COUNT = 1.5;
    private Object[] elementData;
    private int defaultSize;
    private int count;

    public ArrayList() {
        defaultSize = 10;
        elementData = new Object[defaultSize];
        count = 0;
    }

    private boolean rangeCheckForCount(int index) {
        if (index >= count || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound of array.");
        }
        return true;
    }

    private void checkArrayLength(int count) {
        if (count >= defaultSize) {
            Object[] localeData = elementData;
            elementData = new Object[defaultSize *= RESIZE_COUNT];
            System.arraycopy(localeData, 0, elementData, 0, localeData.length);
        }
    }

    public boolean checkValue(T element, int i) {
        if (element != null && element.equals(elementData[i])) {
            return true;
        }
        return element == null && element == elementData[i];
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
            if (rangeCheckForCount(index)) {
                checkArrayLength(count);
                Object[] localeOne = Arrays.copyOfRange(elementData, 0, index);
                Object[] localeTwo = Arrays.copyOfRange(elementData, index, count);
                System.arraycopy(localeOne, 0, elementData, 0, localeOne.length);
                System.arraycopy(localeTwo, 0, elementData, localeOne.length + 1, count - index);
                elementData[index] = value;
                count++;
            }
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
        if (rangeCheckForCount(index)) {
            return (T) elementData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (rangeCheckForCount(index)) {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (rangeCheckForCount(index)) {
            final Object deletedElement = elementData[index];
            Object[] localeOne = Arrays.copyOfRange(elementData, 0, index);
            Object[] localeTwo = Arrays.copyOfRange(elementData, index + 1, count);
            System.arraycopy(localeOne, 0, elementData, 0, localeOne.length);
            System.arraycopy(localeTwo, 0, elementData, localeOne.length, localeTwo.length);
            count--;
            return (T) deletedElement;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        boolean deleteOrNot = false;
        for (int i = 0; i < count; i++) {
            if (checkValue(element, i)) {
                remove(i);
                deleteOrNot = true;
                break;
            }
        }
        if (!deleteOrNot) {
            throw new NoSuchElementException("ELement not find.");
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
}
