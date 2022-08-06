package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= list.length) {
            ensureCapacity();
        }
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception " + index);
        }

        if (size >= list.length) {
            ensureCapacity();
        }

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
        int elementIndex = checkIndex(index);
        return (T) list[elementIndex];
    }

    @Override
    public void set(T value, int index) {
        int ind = checkIndex(index);
        list[ind] = value;
    }

    @Override
    public T remove(int index) {
        int elementIndex = checkIndex(index);
        T oldValue = (T) list[elementIndex];
        int numMoved = size - elementIndex - 1;
        if (numMoved > 0) {
            System.arraycopy(list, elementIndex + 1, list, elementIndex, numMoved);
        }
        list[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || element != null && element.equals(list[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("No such element in the list.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds index " + index);
        }
        return index;
    }

    private void ensureCapacity() {
        Object[] newList = new Object[(int) (size + size * 1.5)];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        ArrayList<?> arrayList = (ArrayList<?>) o;

        if (size != arrayList.size) {
            return false;
        }
        return Arrays.equals(list, arrayList.list);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(list);
        result = 31 * result + size;
        return result;
    }
}
