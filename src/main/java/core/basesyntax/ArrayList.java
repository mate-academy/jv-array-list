package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int count;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        expandingAnArray();
        array[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        expandingAnArray();
        if (index > count || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index doesn't exist");
        }
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = value;
        count++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        indexException(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexException(index);
        T deletedIndex = array[index];
        System.arraycopy(array, index + 1, array, index, count - index);
        count--;
        return deletedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < count; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private void expandingAnArray() {
        if (count == array.length) {
            T[] newArray = (T[]) new Object[count + count / 2];
            System.arraycopy(array, 0, newArray, 0, count);
            array = newArray;
        }
    }

    private void indexException(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayListIndexOutOfBoundsException("Such index doesn't exist");
        }
    }
}
