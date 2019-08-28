package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private Object [] arr = new Object [16];
    private int size = 0;

    @Override
    public void add(T value) {
        reSize();
        arr [size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        reSize();
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        reSize();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        reSize();
        arr[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removeElem = (T) arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size--] = null;
        return  removeElem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return  size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private  void reSize() {
        if (size >= arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }
}
