package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            increaseSize();
        }
        array[size] = value;
        ++size;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexCheck(index);
        System.arraycopy(array, index, array, index + 1, size++ - index);
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T value = array[index];

        System.arraycopy(array,index + 1,array,index,size - index - 1);
        --size;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            //Objects.equals(element, array[i])
            if (element == array[i] || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseSize() {
        T[] tempArray = array;
        array = (T[]) new Object[tempArray.length + tempArray.length / 2];

        System.arraycopy(tempArray,0,array,0,tempArray.length);
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
