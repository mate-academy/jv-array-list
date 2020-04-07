package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private T[] newArray;
    private int size;

    public ArrayList() {
        newArray = (T[]) new Object[ARRAY_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= newArray.length) {
            expandCapacity();
        }
        newArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (validIndex(index)) {
            System.arraycopy(newArray, index, newArray, index + 1, size - index);
            newArray[index] = value;
            size++;
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
        return validIndex(index) ? newArray[index] : null;
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        newArray[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        Object result = newArray[index];
        System.arraycopy(newArray, index + 1, newArray, index, size - index - 1);
        size--;
        return (T) result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] != null && newArray[i].equals(t) || newArray[i] == t) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void expandCapacity() {
        int expandedCapacity = (int) (size + (size * 1.5));
        T[] oldArr = newArray;
        newArray = (T[]) new Object[expandedCapacity];
        System.arraycopy(oldArr,0,newArray,0,size);
    }

    private boolean validIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }
}


