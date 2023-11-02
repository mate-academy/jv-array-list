package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int size) {
        objects = new Object[size];
    }

    @Override
    public void add(T value) {
        if (size == objects.length) {
            Object[] newArray = new Object[(int) (size * 1.5)];
            System.arraycopy(objects, 0, newArray, 0, objects.length);
            objects = newArray;
        }
        objects[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        size++;
        rangeCheckForAdd(index);
        Object[] elements = new Object[size + 1];
        if (index >= 0) {
            System.arraycopy(objects, 0, elements, 0, index);
        }
        elements[index] = value;
        if (size - index >= 0) {
            System.arraycopy(objects, index, elements, index + 1, size - index);
        }
        objects = elements;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arrFromList = getArrayFromList(list);
        Object[] newArray = new Object[size + arrFromList.length];
        System.arraycopy(objects, 0, newArray, 0, size);
        System.arraycopy(arrFromList, 0, newArray, size, arrFromList.length);
        objects = newArray;
        size += arrFromList.length;
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index);
        for (int i = 0; i < objects.length; i++) {
            if (index == i) {
                return (T) objects[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        T value = get(index);
        for (int i = index; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int indexOf(T value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (objects[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(objects[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private Object[] getArrayFromList(List<T> list) {
        Object[] newArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArray[i] = list.get(i);
        }
        return newArray;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Can't fount value");
        }
    }
}
