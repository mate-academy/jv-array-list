package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private int arraySize;
    private Object[] values;

    public ArrayList() {
        arraySize = 0;
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isNotEnoughSpace()) {
            resize();
        }
        values[arraySize] = value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > arraySize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("wrong index");
        }
        if (isNotEnoughSpace()) {
            resize();
        }
        System.arraycopy(values, index, values, index + 1, arraySize - index);
        values[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isIndexCorrect(index)) {
            return (T) values[index];
        }
        throw new ArrayIndexOutOfBoundsException("we have not this place in our list");
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexCorrect(index)) {
            throw new ArrayIndexOutOfBoundsException("we have not this place in our list");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexCorrect(index)) {
            T removed = (T) values[index];
            int numMoved = arraySize - index - 1;
            System.arraycopy(values, index + 1, values, index, numMoved);
            values[--arraySize] = null;
            return removed;
        }
        throw new ArrayIndexOutOfBoundsException("wrong index");
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == t || t != null && t.equals(values[i])) {
                int numMoved = arraySize - i - 1;
                System.arraycopy(values, i + 1, values, i, numMoved);
                values[--arraySize] = null;
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    public boolean isNotEnoughSpace() {
        return (arraySize == values.length);
    }

    public void resize() {
        int oldCapacity = values.length;
        int newCapacity = (oldCapacity * 3) / 2;
        Object[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newArray, 0, arraySize);
        values = newArray;
    }

    public boolean isIndexCorrect(int index) {
        return index < arraySize && index >= 0;
    }
}
