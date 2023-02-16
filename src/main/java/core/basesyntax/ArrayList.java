package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    private Object[] list;
    private int size;

    public ArrayList() {
        this.list = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index != size) checkIndex(index);
        if(size == list.length) grow();
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        System.arraycopy(list, index + 1, list, index, (size - 1) - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element no found");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = (list.length + list.length / 2);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(list, 0, newArray, 0, list.length);
        list = newArray;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(obj, list[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
