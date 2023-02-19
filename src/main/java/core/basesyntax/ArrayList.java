package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element by negative index");
        } else if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is too much");
        } else if (index == size) {
            add(value);
        } else {
            if (values.length == size) {
                grow();
            }
            T[] newValues = (T[]) new Object[values.length];
            for (int i = 0; i < index; i++) {
                newValues[i] = values[i];
            }
            newValues[index] = value;
            for (int i = index; i < size; i++) {
                newValues[i + 1] = values[i];
            }
            size++;
            values = newValues;
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
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No such element exists");
        } else if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't get element by negative index");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No such index exists");
        } else if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't set to negative position");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove by negative index");
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No such element exists");
        }
        T[] newValues = (T[]) new Object[values.length];
        for (int i = 0; i < index; i++) {
            newValues[i] = values[i];
        }
        for (int i = index + 1; i < values.length; i++) {
            newValues[i - 1] = values[i];
        }
        T result = values[index];
        values = newValues;
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        T[] newValues = (T[]) new Object[values.length];
        int elementIndex = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                elementIndex = i;
                break;
            }
            if (i == size - 1) {
                throw new NoSuchElementException("No such element exists");
            }
        }
        for (int i = 0; i < elementIndex; i++) {
            newValues[i] = values[i];
        }
        for (int i = elementIndex + 1; i < size; i++) {
            newValues[i - 1] = values[i];
        }
        values = newValues;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        int newLength = values.length + (values.length >> 1);
        T[] newArray = (T[]) new Object[newLength];
        for (int i = 0; i < values.length; i++) {
            newArray[i] = values[i];
        }
        values = newArray;
    }
}
