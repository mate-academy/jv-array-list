package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int initSize = 10;
    private T[] strArray;
    private int pointer;

    public ArrayList() {
        strArray = (T[]) new Object[initSize];
        pointer = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        strArray[pointer++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        wrongIndexCheckForAdd(index);
        pointer++;
        System.arraycopy(strArray, index, strArray, index + 1, size() - 1 - index);
        strArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        wrongIndexCheck(index);
        return strArray[index];
    }

    @Override
    public void set(T value, int index) {
        wrongIndexCheck(index);
        strArray[index] = value;
    }

    @Override
    public T remove(int index) {
        wrongIndexCheck(index);
        int numMoved = size() - index - 1;
        T removed = strArray[index];
        System.arraycopy(strArray, index + 1, strArray, index, numMoved);
        pointer--;
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if (isEquals(t, i)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    private void ensureCapacity() {
        if (pointer == strArray.length) {
            T[] newArray = (T[]) new Object[strArray.length + strArray.length / 2];
            System.arraycopy(strArray, 0, newArray, 0, pointer);
            strArray = newArray;
        }
    }

    private void wrongIndexCheck(int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayIndexOutOfBoundsException("Index is out of array.");
        }
    }

    private void wrongIndexCheckForAdd(int index) {
        if (index < 0 || index > pointer) {
            throw new ArrayIndexOutOfBoundsException("Index is out of array.");
        }
    }

    private boolean isEquals(T value, int i) {
        if (value == null) {
            return value == strArray[i];
        }
        return value.equals(strArray[i]);
    }
}
