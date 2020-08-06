package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int SIZE = 10;

    private int filledSize;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[SIZE];
        filledSize = 0;
    }

    public ArrayList(int incomeSize) {
        array = (T[]) new Object[incomeSize];
        filledSize = 0;
    }

    public void sizeCheck() {
        if (filledSize == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, filledSize);
            array = newArray;
        }
    }

    @Override
    public void add(T value) {
        sizeCheck();
        array[filledSize] = value;
        filledSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > filledSize) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            sizeCheck();
            System.arraycopy(array, index, array, index + 1, filledSize - index);
            array[index] = value;
            filledSize++;
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
        if (index >= filledSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index > filledSize - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index > filledSize - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            T temp = array[index];
            System.arraycopy(array, index + 1, array, index, filledSize - index - 1);
            filledSize--;
            return temp;
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < filledSize; i++) {
            if (t == array[i] || (t != null && t.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return filledSize;
    }

    @Override
    public boolean isEmpty() {
        return filledSize == 0;
    }
}
