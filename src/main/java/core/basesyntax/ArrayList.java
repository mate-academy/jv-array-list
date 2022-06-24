package core.basesyntax;
//import jdk.internal.util.ArraysSupport;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAYDATA = {};
    private T[] arrayData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;
    private int cursor;

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private Object[] grow(int minimalCapacity) {
        int oldCapacity = arrayData.length;
        double multiplicator = 1;
        if (oldCapacity == size) {
            multiplicator = 1.5;
        }
        if (oldCapacity > 0 || arrayData != EMPTY_ARRAYDATA) {
            int newCapacity = (int) (oldCapacity * multiplicator + 1);
            return arrayData = Arrays.copyOf(arrayData, newCapacity);
        } else {
            return arrayData = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minimalCapacity)];
        }
    }

    public static Object[] listToArray(List<?> list) {
        if (!list.isEmpty()) {
            Object[] tempArray = new Object[list.size()];
            for (int index = 0; index < list.size(); index++) {
                tempArray[index] = list.get(index);
            }
            return tempArray;
        }
        return null;
    }

    @Override
    public void add(T value) {
        int index = cursor;
        ArrayList.this.add(value, index);
        cursor = index + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        } else {
            int s = size;
            if (s == arrayData.length) {
                arrayData = (T[]) grow(size + 1);
            }
            System.arraycopy(arrayData, index,
                    arrayData, index + 1,
                    s - index);
            arrayData[index] = value;
            size = s + 1;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            T[] tempArray = (T[]) listToArray(list);
            int newCapacity = size + tempArray.length;
            arrayData = Arrays.copyOf(arrayData, newCapacity);
            System.arraycopy(tempArray, 0, arrayData, size, tempArray.length);
            size = newCapacity;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        } else {
            return arrayData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        } else {
            arrayData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        } else {
            int newSize = size - 1;
            final T oldValue = get(index);
            if (newSize > index) {
                System.arraycopy(arrayData, index + 1, arrayData, index, newSize - index);
            }
            size = newSize;
            arrayData[newSize] = null;
            return oldValue;
        }
    }

    @Override
    public T remove(T element) {
        int index = 0;
        found:
        {
            if (element == null) {
                for (; index < size; index++) {
                    if (arrayData[index] == null) {
                        remove(index);
                        break found;
                    }
                }
            } else {
                for (; index < size; index++) {
                    if (element.equals(arrayData[index])) {
                        remove(index);
                        break found;
                    }
                }
                throw new NoSuchElementException();
            }
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        for (T element : arrayData) {
            if (element != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }
}
