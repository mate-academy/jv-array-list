package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private int size;
    private int defaultCapacyty = 10;
    private Object[] arr;

    public ArrayList(int currentCapacity) {
        if (currentCapacity > 0) {
            this.arr = new Object[currentCapacity];
        } else {
            throw new IllegalArgumentException("Wrong initial capacity: " + currentCapacity);
        }
    }

    public ArrayList() {
        this.arr = new Object[defaultCapacyty];
    }

    private void setCapacity() {
        if (size >= arr.length) {
            arr = Arrays.copyOf(arr, (int) (arr.length * 1.5));
        }
    }

    @Override
    public void add(T value) {
        setCapacity();
        arr[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        setCapacity();
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return (T) arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = (T) arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        this.size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(arr[i])) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
