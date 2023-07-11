package core.basesyntax;

import java.util.NoSuchElementException;
import org.apache.commons.lang.ObjectUtils;

public class ArrayList<T> implements List<T> {
    private static int dimension = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;
    private int size;
    private T[] array = (T[]) new Object[dimension];

    @Override
    public void add(T value) {
        addable();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        addable();
        addIndexInRange(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        indexInRange(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexInRange(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        addable();
        indexInRange(index);
        final T removeElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (ObjectUtils.equals(element, array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element in array! : " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void addable() {
        if (array.length == size) {
            dimension = (int) (dimension * INCREASING_COEFFICIENT);
            T[] tmpArray = array;
            array = (T[]) new Object[dimension];
            System.arraycopy(tmpArray, 0, array, 0, size);
        }
    }

    public void addIndexInRange(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] out of array range!");
        }
    }

    public void indexInRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] out of array range!");
        }
    }

}
