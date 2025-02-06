package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elements;
    private int size = 0;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity() {
        if (size == elements.length) {
          int newCapacity = (int) (elements.length * GROWTH_FACTOR);
          elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1,size - index);
        size++;
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
      return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; 1 < size; i++) {
          if (Objects.equals(elements[i], element)) {
            return remove(i);
          }
        }
        throw  new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
      if (index < 0 || index >= size) {
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
      }
    }

    private void checkIndexForAdd(int index) {
      if (index < 0 || index > size) {
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
      }
    }
}
