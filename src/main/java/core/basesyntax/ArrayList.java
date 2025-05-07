package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int count;
    public static final int MAX_CAPACITY = 10;
    private Object[] elements = new Object[MAX_CAPACITY];
    @Override
    public void add(T value) {
        if (count >= elements.length) {
            elements = grow();
        }
        elements[count] = value;
        count++;

    }

    @Override
    public void add(T value, int index) {
        checkIndexforAdd(index);
        count++;
        Object[] newArray = new Object[elements.length + 1];
        if (count > MAX_CAPACITY) {
             newArray = grow();

        }
        for (int i = 0; i < newArray.length; i++) {
            if (i == index) {
                break;
            }
            newArray[i] = elements[i];
        }
        newArray[index] = value;
        for (int i = index + 1; i < newArray.length; i++) {
            newArray[i] = elements[i - 1];
        }
        elements = newArray;

    }

    @Override
    public void addAll(List<T> list) {
        if (count > elements.length) {
            elements = grow();
        }
        for (int i = 0; i < elements.length; i++) {
            elements[i] = list.get(i);
        }

    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexforAdd(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = (T) elements[index];
        Object[] newArray1 = new Object[elements.length - 1];
        for (int i = 0; i < newArray1.length; i++) {
            if (i == index) {
                break;
            }
            newArray1[i] = elements[i];
        }
        for (int i = index; i < newArray1.length; i++) {
            newArray1[i] = elements[i + 1];
        }
        count--;
        elements = newArray1;
        return element;
    }

    @Override
    public T remove(T element) {
        T result = null;
        Object[] newArray1 = new Object[elements.length - 1];
        int a = 0;
        int thisExist = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == element || (element != null
            && element.equals(elements[i]))) {
                a = i;
                result = element;
                thisExist++;
                break;
            }
            newArray1[i] = elements[i];
        }
        if (thisExist == 0) {
            throw new NoSuchElementException();
        }

        for (int i = a; i < newArray1.length; i++) {
            newArray1[i] = elements[i + 1];
        }
        count--;
        elements = newArray1;
        return result;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public Object[] grow() {
        return Arrays.copyOf(elements, MAX_CAPACITY + (MAX_CAPACITY >> 1));
    }

    private void checkIndexforAdd(int index) {
        if (index < 0 || index > count) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }

    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }
}
