package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int size;
    private final int baseSize = 10;
    private final double enlargingSize = 1.5;

    public ArrayList() {
        array = (T[]) new Object[baseSize];
        size = 0;
    }

    public ArrayList(T[] array) {
        this.array = array;
        size = array.length;
    }

    @Override
    public void add(T value) {
        if (size < array.length) {
            ++size;
            array[size - 1] = value;
        } else {
            changeSize(enlargingSize);
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, can't be added to"
                    + " non-existing position.");
        }
        if (index == 0 && size <= 0) {
            add(value);
            return;
        }
        ++size;
        if (size == array.length) {
            changeSize(enlargingSize);
        }
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, array.length - index - 1);
        array = (T[]) newArray; //without this array does not resize properly.
    }

    @Override
    public void addAll(List<T> list) {
        int expectedLength = (list.size() + this.size());
        if (expectedLength > array.length) {
            changeSize(enlargingSize);
        }
        for (int i = 0; i < list.size(); ++i) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T temp = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i <= size - 1; ++i) {
            if (element == array[i] |
                    (element != null && element.equals(array[i]))) {
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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private T[] changeSize(double coefficient) {
        int newLength = (int) (array.length * coefficient);
        Object[] tempArray = new Object[(int) newLength];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = (T[]) tempArray;
        return array;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to access element, index of which is lower than 0");
        }
        if (index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("You're trying"
                    + " to access element, index of which is larger than array length.");
        }
    }
}
