
package core.basesyntax;

import java.util.NoSuchElementException;
public class ArrayList<T> implements List<T> {

    private T[] list;
    private int size;
    private int currentSize;
    private ArrayListIndexOutOfBoundsException exception
            = new ArrayListIndexOutOfBoundsException("Index is out of bounds");

    public ArrayList() {
        this.size = 0;
        this.currentSize = 10;
        this.list = (T[]) new Object[currentSize];
    }

    @Override
    public void add(T value) {
        if (size == currentSize) {
            makeListBigger();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw exception;
        }

        if (currentSize <= size + 1) {
            makeListBigger();
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = value;
        size++;
    }

    private void makeListBigger() {
        currentSize = currentSize * 3 / 2;
        T[] newList = (T[]) new Object[currentSize];
        System.arraycopy(this.list, 0, newList, 0, size);
        list = newList;
    }

    @Override
    public void addAll(List<T> list) {

        while (currentSize < this.size + list.size()) {
            makeListBigger();
        }

        for (int i = 0; i < list.size(); i++) {
            this.list[size + i] = list.get(i);
        }

        size += list.size();
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw exception;
        }
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw exception;
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw exception;
        }

        final T removedElement = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        list[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(Object value) {
        for (int i = 0; i < size; i++) {
            if ((value == null && list[i] == null) || (value != null && value.equals(list[i]))) {
                T removedValue = list[i];
                for (int j = i; j < size - 1; j++) {
                    list[j] = list[j + 1];
                }
                list[--size] = null;
                return removedValue;
            }
        }
        throw new NoSuchElementException("No such element");
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