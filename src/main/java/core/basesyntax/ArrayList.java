package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int counter;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        counter = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        values[counter] = value;
        counter++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > counter) {
            throw new ArrayIndexOutOfBoundsException("This index is out of bounds.");
        }
        checkCapacity();
        System.arraycopy(values, index, values, index + 1, counter - index);
        counter++;
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedItem = values[index];
        System.arraycopy(values,index + 1, values, index, counter - index - 1);
        counter--;
        return removedItem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < counter; i++) {
            if (values[i] == t || values[i] != null && values[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in this array.");
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    private void checkCapacity() {
        if (counter >= values.length) {
            Object[] newValues = new Object[values.length * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, counter);
            values = (T[]) newValues;
        }
    }

    private void checkIndex(int index) {
        if (index >= counter || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index is out of bounds.");
        }
    }
}
