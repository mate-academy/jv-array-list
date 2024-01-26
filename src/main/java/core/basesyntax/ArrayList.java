package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static int INITIAL_CAPACITY = 10;
    private int size;
    private T[] values;
    private T[] copyArray;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (value == null) {
            value = (T) "null";
        }
        checkInitialCapacity(INITIAL_CAPACITY);
        if (size <= INITIAL_CAPACITY && copyArray == null) {
            values[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        checkIndexPosition(values,index);
        copyArray = (T[]) new Object[INITIAL_CAPACITY];
        if (value == null) {
            value = (T) "null";
        }
         for (int i = 0, j = 0; i < size; i++, j++) {
             if (index == i) {
                 copyArray[j] = value;
                 copyArray[j + 1] = values[i];
                 j++;
                 continue;
             }
             copyArray[j] = values[i];
         }
         copyToNewArray(copyArray);
    }

    @Override
    public void addAll(List<T> list) {
    }

    @Override
    public T get(int index) {
      checkIndexPosition(values, index);
      if ("null".equals(values[index])) {
          return null;
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
       checkIndexPosition(values, index);
       checkInitialCapacity(INITIAL_CAPACITY);
       copyArray = (T[]) new Object[INITIAL_CAPACITY];
       T element = values[index];

        for (int i = 0, j = 0; i < size; i++, j++) {
            if (index == i) {
                j--;
                continue;
            }
            copyArray[j] = values[i];
        }
        copyToNewArray(copyArray);
        return element;
    }

    @Override
    public T remove(T element) {
      if (!(checkElement(values, element))) {
          throw new NoSuchElementException("");
      }
      copyArray = (T[]) new Object[INITIAL_CAPACITY];
      int currentSize = 0;
      int count = 1;

      if (element == null) {
          element = (T) "null";
      }

      for (int i = 0, j = 0; i < size; i++, j++) {
          if (element.equals(values[i]) && count < 2) {
              j--;
              count++;
              continue;
          }
          copyArray[j] = values[i];
          currentSize++;
      }
      copyToNewArray(copyArray);
      if ("null".equals(element)) {
          return null;
      }
      return element;
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
        if (element == null) {
            element = (T) "null";
        }
        for (T value : values) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIndexPosition(T[] values, int index) {
        if (index < 0 || index >= size) {
           throw new ArrayListIndexOutOfBoundsException("");
        }
        return true;
    }

    public void checkInitialCapacity(int initialCapacity) {
        if (size == INITIAL_CAPACITY) {
            int newSize = INITIAL_CAPACITY * 3 / 2 + 1;
            INITIAL_CAPACITY = newSize;
        }
    }

    public void copyToNewArray(T[] copyArray) {
        values = (T[]) new Object[INITIAL_CAPACITY];
        int count = 0;
        for (int i = 0; i < copyArray.length; i++) {
            if (copyArray[i] == null) {
                continue;
            }
            values[i] = copyArray[i];
            count++;

        }
        size = count;
    }
}
