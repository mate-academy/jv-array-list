package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int lastElementIndex;
    private final int baseSize = 10;
    private final double enlargingSize = 1.5;

    public ArrayList() {
        array = (T[])new Object[baseSize];
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
            changeSize(enlargingSize);
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > lastElementIndex + 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, can't be added to"
                    + " non-existing position.");
        }
        if (index == 0 && lastElementIndex < 0) {
            add(value);
            return;
        }
        ++lastElementIndex;
        if (lastElementIndex == array.length - 1) {
            changeSize(enlargingSize);
        }
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, array.length - index - 1);
        array = (T[])newArray;
    }


    @Override
    public void addAll(List<T> list) {
        int expectedLength = (list.size() + this.size());
        if (expectedLength <= array.length) {
            for (int i = 0; i < list.size(); ++i) {
                add(list.get(i));
            }
        } else {
            changeSize(enlargingSize);
            addAll(list);
        }
    }

    @Override
    public T get(int index) {
        checkIfArgumentIsCorrect(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfArgumentIsCorrect(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfArgumentIsCorrect(index);
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
        return (lastElementIndex == -1);
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

    private void checkIfArgumentIsCorrect (int argument) {
        if (argument < 0) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to access element, index of which is lower than 0");
        }
        if (argument > lastElementIndex) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to access element, index of which is larger than array length.");
        }
    }
}
