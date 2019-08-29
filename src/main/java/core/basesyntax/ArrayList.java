package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private final int sizeArr = 16;
    private Object[] elements = new Object[sizeArr];
    private int indexTopArray = 0;

    @Override
    public void add(T value) {
        if (value == null) {
            return;
        }
        if (indexTopArray >= elements.length) {
            resizeAndCopyArray();
        }
        elements[indexTopArray] = value;
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
        if (index == indexTopArray) {
            elements[index] = value;
            indexTopArray++;
        } else {
            System.arraycopy(elements, index, elements, index + 1, indexTopArray - index);
            elements[index] = value;
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
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (value == null) {
            return;
        }
        if (index < 0 || index >= indexTopArray) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= indexTopArray) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T t = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, indexTopArray - 1 - index);
        indexTopArray--;
        return t;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            return null;
        }
        for (int i = 0; i < indexTopArray; i++) {
            if (elements[i].equals(t)) {
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

    private void resizeAndCopyArray() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }
}
