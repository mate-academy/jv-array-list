package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] array = (T[]) new Object[10];
    private int elementCount = 0;

    @Override
    public void add(T value) {
        if (elementCount == array.length) {
            int newCapacity = (int) increaseCapacity(array);
            array = Arrays.copyOf(array, newCapacity);
        }
        elementCount++;
        array[elementCount - 1] = value;
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void add(T value, int index) {
        if (index > elementCount || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid!");
        }
        if (elementCount == array.length) {
            int newCapacity = (int) increaseCapacity(array);
            array = Arrays.copyOf(array, newCapacity);
        }
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[index] = value;
        elementCount++;
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private double increaseCapacity(T[] array) {
        return array.length * 1.5;
    }

    @Override
    public T get(int index) {
        if (index > elementCount - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid!");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > elementCount - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid!");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > elementCount - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index passed is invalid!");
        }
        T removeElement = array[index];
        System.arraycopy(array, index + 1, array, index, elementCount - index - 1);
        elementCount--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        T removeElement = null;
        if (element == null) {
            for (int i = 0; i < elementCount; i++) {
                if (array[i] == null) {
                    return remove(i);
                }
            }
        }
        for (int i = 0; i < elementCount; i++) {
            if (element.equals(array[i])) {
                removeElement = remove(i);
                break;
            }
        }
        if (removeElement == null) {
            throw new NoSuchElementException("The passed element does not exist.");
        }
        return removeElement;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }
}