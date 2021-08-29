package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int WHOLE_COPY_START_INDEX = 0;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE
            = "Index %d out of bounds for length %d";
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = resizeArr(array);
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == array.length) {
            array = resizeArr(array);
        }
        if (index < size && index >= 0) {
            array = addingElementInside(value, index, array);
        } else if (index == size) {
            add(value);
            return;
        } else {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        throw new ArrayListIndexOutOfBoundsException(String
                .format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
        }
        final T returnValue = array[index];
        T[] newArray = (T[]) new Object[array.length];
        System.arraycopy(array, WHOLE_COPY_START_INDEX,
                newArray, WHOLE_COPY_START_INDEX, index);
        System.arraycopy(array, index + 1, newArray,
                index, array.length - index - 1);
        array = newArray;
        size--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = indexOf(element);
        if (indexOfElement == -1) {
            throw new NoSuchElementException();
        }
        return remove(indexOfElement);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == null && value == null)
                    || (array[i] != null && array[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    private T[] addingElementInside(T value, int index, T[] oldArray) {
        T[] newArray = (T[]) new Object[array.length];
        System.arraycopy(oldArray, WHOLE_COPY_START_INDEX,
                newArray, WHOLE_COPY_START_INDEX, index);
        newArray[index] = value;
        System.arraycopy(oldArray, index, newArray,
                index + 1, oldArray.length - index - 1);
        return newArray;
    }

    private T[] resizeArr(T[] oldArray) {
        T[] newArray = (T[]) new Object[(int) (oldArray.length * 1.5)];
        System.arraycopy(oldArray, WHOLE_COPY_START_INDEX,
                newArray, WHOLE_COPY_START_INDEX, oldArray.length);
        return newArray;
    }
}
