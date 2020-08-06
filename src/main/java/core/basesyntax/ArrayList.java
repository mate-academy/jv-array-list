package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private final int INIT_SIZE = 10;
    private T[] strArray;
    private int pointer = 0;

    public ArrayList() {
        strArray = (T[]) new Object[INIT_SIZE];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        strArray[pointer++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        pointer++;
        wrongIndexCheck(index);
        System.arraycopy(strArray, index, strArray, index + 1, strArray.length - 1 - index);
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
        ensureCapacity();
        strArray[index] = value;
    }

    @Override
    public T remove(int index) {
        wrongIndexCheck(index);
        ensureCapacity();
        int numMoved = strArray.length - index - 1;
        T removed = strArray[index];
        System.arraycopy(strArray, index + 1, strArray, index, numMoved);
        pointer--;
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < strArray.length; i++) {
            if (isEquals(t, i)) {
                ensureCapacity();
                T removed = strArray[i];
                int numMoved = strArray.length - i - 1;
                System.arraycopy(strArray, i + 1, strArray, i, numMoved);
                pointer--;
                return removed;
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
        if (pointer + 1 == strArray.length) {
            T[] newArray = (T[]) new Object[strArray.length + strArray.length / 2];
            System.arraycopy(strArray, 0, newArray, 0, pointer);
            strArray = newArray;
        }
    }

    private void wrongIndexCheck(int index) {
        if (index < 0 || index >= pointer) throw new ArrayIndexOutOfBoundsException("Index is out of array.");
    }

    private boolean isEquals(T value, int i) {
        if (value == null) {
            return value == strArray[i];
        }
        return value.equals(strArray[i]);
    }
}
