package core.basesyntax;

import java.sql.SQLOutput;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size;

    ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        values[size] = value;
        increaseSize();
    }

    @Override
    public void add(T value, int index) {
        if (index <= values.length && index >= 0) {
            T[] temp = (T[]) new Object[values.length];
            int amountElementSecondPartOfArray = size - index;
            System.arraycopy(values, 0, temp, 0, values.length);
            System.arraycopy(temp, 0, values, 0, index);
            values[index] = value;
            System.arraycopy(temp, index, values, index + 1, amountElementSecondPartOfArray);
            increaseSize();
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        values[index] = value;

    }

    // С какого м, С какого э, В какой м, С какого э, сколько элементов.
    @Override
    public T remove(int index) {
            T[] temp = (T[]) new Object[values.length];
            System.arraycopy(values, 0, temp,0,values.length);
            // копирование в временный массив
            values = (T[]) new Object[values.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            // скопировал первую часть. 01234
            int amountElementAfterIndex = size - index;
            System.arraycopy(temp, index + 1, values, index, amountElementAfterIndex);
            size--;

        return (T) values;
    }

    @Override
    public T remove(T element) {
        return null;
    }
    // С какого м, С какого э, В какой м, С какого э, сколько элементов.

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseSize() {
        if (size == values.length - 1) {
            T[] temp = (T[]) new Object[values.length];
            System.arraycopy(values, 0, temp, 0, values.length);
            values = (T[]) new Object[(int) (Math.ceil(values.length * 1.5))];
            System.arraycopy(temp, 0, values, 0, temp.length);
        }
        size++;
    }
}