package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int lastElementIndex;

    public ArrayList() {
        array = (T[])new Object[10];
        lastElementIndex = -1;
    }

    public ArrayList(T[] array) {
        this.array = array;
        lastElementIndex = array.length - 1;
    }

    @Override
    public void add(T value) {
        if (lastElementIndex < array.length - 1) {
            ++lastElementIndex;
            array[lastElementIndex] = value;
        } else {
            changeSize(1.5);
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, can't be added to"
                    + " non-existing position.");
        }
        if (index == 0 && lastElementIndex < 0) {
            array[0] = value;
            ++lastElementIndex;
            return;
        }
        if (index > lastElementIndex + 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, can't be added to"
                    + " non-existing position.");
        }
        if (lastElementIndex == array.length - 1) {
            changeSize(1.5);
        }
        Object[] newArray = new Object[array.length];
        for (int i = 0; i < index; ++i) {
            newArray[i] = array[i];
        }
        newArray[index] = value;
        for (int i = index + 1; i <= lastElementIndex + 1; ++i) {
            newArray[i] = array[i - 1];
        }
        ++lastElementIndex;
        array = (T[])newArray;
    }

    @Override
    public void addAll(List<T> list) {
        int expectedLength = (list.size() + lastElementIndex + 1);
        if (expectedLength <= array.length) {
            for (int i = 0; i < list.size(); ++i) {
                ++lastElementIndex;
                array[lastElementIndex] = list.get(i);
            }
        } else {
            changeSize(1.5);
            addAll(list);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to get element, index of which is lower than 0");
        }
        if (index > lastElementIndex) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to get element, index of which is larger than array length.");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to set element, index of which is lower than 0");
        }
        if (index > lastElementIndex) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to set element, index of which is larger than array length");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > lastElementIndex) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, can't be removed");
        }
        T temp = array[index];
        for (int i = index; i < lastElementIndex; ++i) {
            array[i] = array[i + 1];
        }
        lastElementIndex--;
        return temp;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i <= lastElementIndex; ++i) {
            if (element == array[i]) {
                index = i;
                break;
            } else if (element != null && element.equals(array[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("You're trying to remove element,"
                    + " that is not present in the list");
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return lastElementIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        if (lastElementIndex == -1) {
            return true;
        } else {
            return false;
        }
    }

    private T[] changeSize(double coefficient) {
        int newLength = (int)(array.length * coefficient);
        Object[] tempArray = new Object[(int) newLength];
        if (newLength > array.length) {
            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }
        } else if (newLength < array.length) {
            for (int i = 0; i < newLength; i++) {
                tempArray[i] = array[i];
            }
        }
        array = (T[]) tempArray;
        return array;
    }
}
