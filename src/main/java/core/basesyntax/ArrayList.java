package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private Object[] array = new Object[16];
    private int indexTopArray = 0;

    @Override
    public void add(T value) {
        if (value == null) {
            return;
        }
        if (indexTopArray >= array.length) {
            array = resizeAndCopyArray(array);
        }
        array[indexTopArray] = value;
        indexTopArray++;
    }

    @Override
    public void add(T value, int index) {
        if (value == null) {
            return;
        }
        if (index < 0 || index > indexTopArray) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (indexTopArray >= array.length) {
            array = resizeAndCopyArray(array);
        }
        if (index == indexTopArray) {
            array[index] = value;
            indexTopArray++;
        } else {
            System.arraycopy(array, index, array, index + 1, indexTopArray - index);
            array[index] = value;
            indexTopArray++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= indexTopArray) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (value == null) {
            return;
        }
        if (index < 0 || index >= indexTopArray) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= indexTopArray) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T t = (T) array[index];
        for (int i = index + 1; i < indexTopArray; i++) {
            array[i - 1] = array[i];
        }
        indexTopArray--;
        return t;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            return null;
        }
        for (int i = 0; i < indexTopArray; i++) {
            if (array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return indexTopArray;
    }

    @Override
    public boolean isEmpty() {
        return indexTopArray < 1;
    }

    private Object[] resizeAndCopyArray(Object[] input) {
        return Arrays.copyOf(input, input.length * 2);
    }
}
