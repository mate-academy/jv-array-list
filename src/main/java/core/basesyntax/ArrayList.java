package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_EMPTYARRAY_CAPACITY = 10;
    private T[] arrayOfData;
    private int size;

    public ArrayList() {
        arrayOfData = (T[]) new Object[DEFAULT_EMPTYARRAY_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (isFool()) {
            arrayOfData = scale();
        }
        arrayOfData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else if ((index < 0) || (index > size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        } else {
            if (isFool()) {
                arrayOfData = scale();
            }
            T[] newArray = (T[]) new Object[arrayOfData.length];
            for (int i = 0; i < size + 1; i++) {
                if (i < index) {
                    newArray[i] = arrayOfData[i];
                } else if (i == index) {
                    newArray[i] = value;
                } else {
                    newArray[i] = arrayOfData[i - 1];
                }
            }
            arrayOfData = newArray;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        } else {
            return arrayOfData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        } else {
            arrayOfData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        } else {
            T removed = arrayOfData[index];
            arrayOfData = fastRemove(index);
            size--;
            return removed;
        }
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException("There isn't such element in list");
        }
        for (int i = 0; i < size; i++) {
            if ((arrayOfData[i] == null) && (element == null)) {
                return remove(i);
            } else if ((arrayOfData[i] == null)) {
                continue;
            } else if (arrayOfData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There isn't such element in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] scale() {
        T[] newArray = (T[]) new Object[(int) (size * 1.5)];
        for (int i = 0; i < arrayOfData.length; i++) {
            newArray[i] = arrayOfData[i];
        }
        return newArray;
    }

    public boolean isFool() {
        return size == arrayOfData.length;
    }

    public T[] fastRemove(int index) {
        T[] newArray = (T[]) new Object[arrayOfData.length];
        for (int i = 0; i < size; i++) {
            if (i < index) {
                newArray[i] = arrayOfData[i];
            } else if (i == index) {
                continue;
            } else {
                newArray[i - 1] = arrayOfData[i];
            }
        }
        return newArray;
    }
}
