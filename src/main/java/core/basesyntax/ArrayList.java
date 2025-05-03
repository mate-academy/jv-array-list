package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity();
        if (list == null || list.isEmpty()) {
            return;
        }

        if (size + list.size() > array.length) {
            array = Arrays.copyOf(array, size + list.size());
        }

        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null) || (element != null && element.equals(array[i]))) {
               T removed = array[i];
               System.arraycopy(array, i + 1, array, i, size - i - 1);
               array[--size] = null;
               return removed;
            }
        }
        throw new NoSuchElementException("This element does not exists: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity() {
        if (size  >= array.length) {
            int increasedSize = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, increasedSize);
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
           throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
