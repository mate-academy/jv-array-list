package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private int size;
    private final int defaultCapacyty = 10;
    private Object[] arrList;

    public ArrayList(int currentCapacity) {
        if (currentCapacity > 0) {
            this.arrList = new Object[currentCapacity];
        } else {
            throw new IllegalArgumentException("Wrong initial capacity: " + currentCapacity);
        }
    }

    public ArrayList() {
        arrList = new Object[defaultCapacyty];
    }

    private void setCapacity() {
        if (size >= arrList.length) {
            arrList = Arrays.copyOf(arrList, (int) (arrList.length * 1.5));
        }
    }

    @Override
    public void add(T value) {
        setCapacity();
        arrList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        setCapacity();
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(arrList, index, arrList, index + 1, size - index);
        arrList[index] = value;
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
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) arrList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arrList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = (T) arrList[index];
        System.arraycopy(arrList, index + 1, arrList, index, size - index - 1);
        this.size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(arrList[i])) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
