package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private int currentNumberOfMembers = 0;
    private Object [] elementData = new Object [capacity];

    @Override
    public void add(T value) {
        if (currentNumberOfMembers >= capacity) {
            Object[] temp = elementData;
            capacity *= 1.5;
            elementData = new Object[capacity];
            System.arraycopy(temp, 0, elementData, 0, temp.length);
        }
        elementData[currentNumberOfMembers++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == currentNumberOfMembers) {
            add(value);
        } else if (index < currentNumberOfMembers && index >= 0) {
            if (currentNumberOfMembers >= capacity) {
                Object[] temp = elementData;
                capacity *= 1.5;
                elementData = new Object[capacity];
                System.arraycopy(temp, 0, elementData, 0, temp.length);
            }
            Object [] temp1 = Arrays.copyOfRange(elementData, 0, index);
            Object [] temp2 = new Object[temp1.length + 1];
            System.arraycopy(temp1, 0, temp2, 0, temp1.length);
            temp2[index] = value;

            Object[] temp3 = Arrays.copyOfRange(elementData, index, currentNumberOfMembers);
            elementData = new Object[capacity];
            System.arraycopy(temp2, 0, elementData, 0, temp2.length);
            System.arraycopy(temp3, 0, elementData, temp2.length, temp3.length);
            currentNumberOfMembers++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index exception");
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
        if (index < currentNumberOfMembers && index >= 0) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index exception");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < currentNumberOfMembers && index >= 0) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index exception");
        }
    }

    @Override
    public T remove(int index) {
        if (index < currentNumberOfMembers && index >= 0) {
            final T removedElement = (T) elementData[index];
            Object[] temp1 = Arrays.copyOfRange(elementData, 0, index);
            Object[] temp2 = Arrays.copyOfRange(elementData, index + 1, currentNumberOfMembers);
            elementData = new Object[capacity];
            System.arraycopy(temp1, 0, elementData, 0, temp1.length);
            System.arraycopy(temp2, 0, elementData, temp1.length, temp2.length);
            currentNumberOfMembers--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index exception");
        }
    }

    @Override
    public T remove(T element) {
        int index;
        boolean isFound = false;
        for (int i = 0; i < currentNumberOfMembers; i++) {
            if (Objects.equals(elementData[i], element)) {
                index = i;
                isFound = true;
                remove(index);
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return currentNumberOfMembers;
    }

    @Override
    public boolean isEmpty() {
        return currentNumberOfMembers == 0;
    }
}
