package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final double capacityIndex = 1.5;
    private int size = 0;
    private int capacity = 10;
    private Object[] array;

    public ArrayList() {
        array = new Object[capacity];
    }

    @Override
    public void add(T value) {
        resize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index < 0, change index!!!");
        }
        resize();
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        if (list.isEmpty()) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        for (int i = 0; i < list.size(); i++) {
            resize();
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("wrong index");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index < 0, change index!!!");
        }
        array[index] = value;
    }

    private void shiftToLeft(int start) {
        size--;
        if (size <= 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(array, start + 1, array, start, size - start);
        }
        array[size] = null;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index < 0, change index!!!");
        }
        Object removed;
        removed = get(index);
        shiftToLeft(index);
        return (T) removed;
    }

    @Override
    public T remove(T element) {
        if (size == 0) {
            throw new NoSuchElementException("the entered element is missing");
        }
        int i;
        for (i = 0; i < size; i++) {
            if (array[i] == null && element == null) {
                break;
            }
            if ((array[i] != null) && (array[i].equals(element))) {
                break;
            }
        }
        if (i < size) {
            shiftToLeft(i);
            return element;
        }
        throw new NoSuchElementException("the entered element is missing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void resize() {
        if (array.length == size) {
            int newCapacity = (int) (array.length * capacityIndex);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
