package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] values;
    private int amountOfElements;

    public ArrayList() {
        values = new Object[ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        values[amountOfElements] = value;
        amountOfElements++;
    }

    @Override
    public void add(T value, int index) {
        if (index > amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        grow();
        System.arraycopy(values, index, values, index + 1, amountOfElements - index);
        values[index] = value;
        amountOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        T removes = (T) values[index];
        System.arraycopy(values, index + 1, values, index, amountOfElements - index - 1);
        amountOfElements--;
        return removes;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < amountOfElements; i++) {
            if (Objects.equals(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such the element present");
    }

    @Override
    public int size() {
        return amountOfElements;
    }

    @Override
    public boolean isEmpty() {
        return amountOfElements == 0;
    }

    private void grow() {
        if (amountOfElements == values.length) {
            Object[] valuesTemp = new Object[values.length + values.length / 2];
            System.arraycopy(values, 0, valuesTemp, 0, amountOfElements);
            values = valuesTemp;
        }
    }
}