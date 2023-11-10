package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INCREMENT = 1;
    private static int arraySize = 10;
    private Object[] arrayList = new Object[arraySize];
    private int currentMaxIndex = 0;
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            arrayList = extend();
        }
        this.arrayList[currentMaxIndex] = value;
        currentMaxIndex++;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
        if (size == arrayList.length) {
            arrayList = extend();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        currentMaxIndex++;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int additionalLength = list.size();
        if (size + additionalLength > arrayList.length) {
            arrayList = extend(size + additionalLength);
        }
        if (additionalLength != 0) {
            System.arraycopy(list.toArray(), 0, arrayList, size, additionalLength);
            size += additionalLength;
            currentMaxIndex += list.size();
        }
    }

    @Override
    public T get(int index) {
        checkRange(index + INCREMENT);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index + INCREMENT);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index + INCREMENT);
        final T previousValue = (T) arrayList[index];
        removeByIndex(arrayList, index);
        size--;
        currentMaxIndex--;
        return previousValue;
    }

    @Override
    public T remove(T element) {
        int index = findElementIndex(element);
        final T previousValue = (T) arrayList[index];
        removeByIndex(arrayList, index);
        size--;
        currentMaxIndex--;
        return previousValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] extend() {
        arraySize += arraySize / 2;
        Object[] copy = new Object[arraySize];
        System.arraycopy(arrayList, 0, copy, 0, Math.min(arrayList.length, arraySize));
        return copy;
    }

    private Object[] extend(int minCapacity) {
        while (arraySize < minCapacity) {
            arraySize += arraySize / 2;
        }
        Object[] copy = new Object[arraySize];
        System.arraycopy(arrayList, 0, copy, 0, Math.min(arrayList.length, arraySize));
        return copy;
    }

    private void checkRange(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index : " + index + " out of bound");
        }
    }

    private void removeByIndex(Object[] data, int index) {
        if (size - 1 > index) {
            System.arraycopy(data, index + 1, data, index, (size - 1) - index);
        }
        data[size] = null;
    }

    private int findElementIndex(T element) {
        boolean isFind = false;
        int i = 0;
        if (element == null) {
            for (; i < size; i++) {
                if (arrayList[i] == null) {
                    isFind = true;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(arrayList[i])) {
                    isFind = true;
                    break;
                }
            }
        }
        if (!isFind) {
            throw new NoSuchElementException("Element: " + element + " is not exist");
        }
        return i;
    }

    public Object[] toArray() {
        return this.arrayList;
    }
}
