package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int elementsAmount;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        elementsAmount = 0;
    }

    @Override
    public void add(T value) {
        if (array.length == size()) {
            increaseArrayLength();
        }
        array[size()] = value;
        increaseElementsAmount();
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is incorrect");
        }

        if (index != size()) {
            T[] newArray = (T[]) new Object[size() + 1];

            for (int i = 0; i < newArray.length; i++) {
                if (i == index) {
                    newArray[i] = value;
                }
                if (i < index) {
                    newArray[i] = array[i];
                }
                if (i > index) {
                    newArray[i] = array[i - 1];
                }
            }

            if (array.length == size()) {
                increaseArrayLength();
            }

            array = copyArray(newArray);
            increaseElementsAmount();
        } else {
            add(value);
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
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("The index is incorrect");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("The index is incorrect");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("The index is incorrect");
        }
        final T element = array[index];
        T[] newArray = (T[]) new Object[size() - 1];

        for (int i = 0; i < size(); i++) {
            if (i < index) {
                newArray[i] = array[i];
            }
            if (i > index) {
                newArray[i - 1] = array[i];
            }
        }
        array = copyArray(newArray);
        decreaseElementsAmount();

        return element;
    }

    @Override
    public T remove(T element) {
        T[] newArray = (T[]) new Object[size() - 1];
        boolean isFound = false;

        for (int i = 0; i < size(); i++) {
            if (!isFound
                    && (((element == null)
                    && (array[i] != null))
                    || ((element != null)
                    && (array[i] == null)))) {
                if (newArray.length == i) {
                    throw new NoSuchElementException("No such element");
                }
                newArray[i] = array[i];
                continue;
            } else if (!isFound
                    && (((array[i] == null)
                    && (element == null))
                    || array[i].equals(element))) {
                isFound = true;
                continue;
            }

            if (!isFound) {
                if (newArray.length == i) {
                    throw new NoSuchElementException("No such element");
                }
                newArray[i] = array[i];
            } else {
                newArray[i - 1] = array[i];
            }
        }
        array = copyArray(newArray);
        decreaseElementsAmount();

        return element;
    }

    @Override
    public int size() {
        return elementsAmount;
    }

    @Override
    public boolean isEmpty() {
        return elementsAmount == 0;
    }

    private void increaseArrayLength() {
        array = Arrays.copyOf(array, (array.length + (array.length >> 1)));
    }

    private void increaseElementsAmount() {
        elementsAmount++;
    }

    private void decreaseElementsAmount() {
        elementsAmount--;
    }

    private T[] copyArray(T[] newArray) {
        return Arrays.copyOf(newArray, array.length);
    }
}
