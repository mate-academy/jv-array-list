package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static int INITIAL_CAPACITY = 10;
    private int size;
    private T[] values;
    private T[] biggerArray;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size <= INITIAL_CAPACITY && biggerArray == null) {
            values[size++] = value;
        }

    }

    @Override
    public void add(T value, int index) {
    if (!(checkIndexPosition(values,index))) {
        throw new ArrayListIndexOutOfBoundsException("");
    }
    }

    @Override
    public void addAll(List<T> list) {
    }

    @Override
    public T get(int index) {
       if ((checkIndexPosition(values, index))) {
           throw new ArrayListIndexOutOfBoundsException("");
       }
        return values[index];
    }
    @Override
    public void set(T value, int index) {
      if (!(checkIndexPosition(values, index))) {
          throw new ArrayListIndexOutOfBoundsException("");
      }
      values[index] = value;
    }

    @Override
    public T remove(int index) {
       if (!(checkIndexPosition(values, index))) {
           throw new ArrayListIndexOutOfBoundsException("");
       }
        return null;
    }

    @Override
    public T remove(T element) {
      if (!(checkElement(values, element))) {
          throw new NoSuchElementException("");
      }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }
    public boolean checkElement(T[] values, T element) {
        for (T value : values) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkIndexPosition(T[] values, int index) {
        if (!(index < 0 || index >= size)) {
            return true;
        }
        return false;
    }
}
