package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MINIMUM_LENGTH_ARRAY = 10;

    private static final double CAPACITY_MULTIPLIER = 1.5;

    T[] values = (T[]) new Object[MINIMUM_LENGTH_ARRAY];
    private int size = 0;
    @Override
    public void add(T value) {
        if (size() == values.length) {
            grow();
        }
        values[size] = value;
        size++;
        return;
    }

    @Override
    public void add(T value, int index) {
        if (index == size()) {
            add(value);
            return;
        }
        checkNullAndSize(index);
        if (size() == values.length) {
            grow();
        }
        System.arraycopy(values,index,values,index + 1, size() - index);
        values[index] = value;
        size++;
    }

    public void checkNullAndSize (int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + "is not valid for size " + size + ".");
        }
    }

    @Override
    public void addAll(List<T> list) {
       int sizeAddAll = size + list.size();
      for (int i = size; i < sizeAddAll; i++) {
          values[i] = list.get(i - list.size() - 1);
          add(values[i]);
      }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkNullAndSize(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        index = searchIndex(index);
        if (index == -1) {
            throw new ArrayListIndexOutOfBoundsException("index out of bound");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        checkNullAndSize(index);
        T valuesIndex = values[index];
        if (index == size - 1) {
        } else {
            System.arraycopy(values, index + 1, values, index, size() - index);
        }
        size--;
        return valuesIndex;
    }

    public int searchIndex(T element) {
        for (int i = 0; i < values.length; i++ ) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return i;
            }
        }
    return -1;
    }

    public int searchIndex(int index) {
        for (int i = 0; i < size; i++ ) {
            if (i == index) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(T element) {
        int index = searchIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("Index is out of bound array!");
        }
        remove(index);
        //size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
          return true;
        }
        return false;
    }

    private void grow()
    {
        T[] newValues = (T[]) new Object[(int) (values.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

}
