package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STARTING_SIZE = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[STARTING_SIZE];
    }

    @Override
    public void add(T value) {
        isFull();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size() + 1 >= objects.length) {
            isFull();
        }
        indexCheckForAdd(index);
        Object[] newArray = new Object[objects.length];
        System.arraycopy(objects, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(objects, index, newArray, index + 1, objects.length - index - 1);
        objects = (T[]) newArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        isFull();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        Object answer;
        indexCheck(index);
        answer = objects[index];
        Object[] newArr = new Object[objects.length];
        System.arraycopy(objects, 0, newArr, 0, index);
        System.arraycopy(objects, index + 1, newArr, index, objects.length - index - 1);
        objects = (T[]) newArr;
        size--;
        return (T) answer;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isFull() {
        if (size >= objects.length) {
            Object[] newObject = new Object[(int) (objects.length * MULTIPLIER)];
            System.arraycopy(objects, 0, newObject, 0, objects.length);
            objects = (T[]) newObject;
        }
    }

    private void indexCheck(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size());
        }
    }

    private void indexCheckForAdd(int index) {
        if (size() != 0 && index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size());
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null && objects[i] == null
                    || value != null && value.equals(objects[i])) {
                return i;
            }
        }
        return -1;
    }
}
