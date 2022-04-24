package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[CAPACITY];
    }

    public ArrayList(List<T> list) {
        objects = new Object[CAPACITY];
        if (list.size() <= CAPACITY) {
            copyListToArrayListAndResize(list);
            return;
        }
        do {
            expandObjectsArray(objects);
        } while (list.size() <= CAPACITY);
        copyListToArrayListAndResize(list);
    }

    @Override
    public void add(T value) {
        if (size < CAPACITY) {
            objects[size] = value;
            size++;
            return;
        }
        expandObjectsArray(objects);
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, 1);
        if (index == size) {
            add(value);
            return;
        }
        if (size + 1 <= CAPACITY) {
            copyArrayToObjectsAndResizeArrayList(
                    objects, index, index + 1, size - index, 1);
            objects[index] = value;
            return;
        }
        expandObjectsArray(objects);
        copyArrayToObjectsAndResizeArrayList(
                objects, index, index + 1, size - index, 1);
        objects[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        ArrayList<T> arrList = new ArrayList<>(list);
        if (size + arrList.size <= CAPACITY) {
            copyArrayToObjectsAndResizeArrayList(
                    arrList.objects, 0, size, arrList.size, arrList.size);
            return;
        }
        do {
            expandObjectsArray(objects);
        } while (size + arrList.size <= CAPACITY);
        copyArrayToObjectsAndResizeArrayList(
                arrList.objects, 0, size, arrList.size, arrList.size);
    }

    @Override
    public T get(int index) {
        checkIndex(index, 0);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, 0);
        if (objects[index] == value) {
            return;
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, 0);
        T element = (T) objects[index];
        copyArrayToObjectsAndResizeArrayList(
                objects, index + 1, index, size - index - 1, -1);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(objects[i], element)) {
                copyArrayToObjectsAndResizeArrayList(
                        objects, i + 1, i, size - i - 1, -1);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int correctionFactor) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index has to be positive");
        } else if (index >= size + correctionFactor) {
            throw new ArrayListIndexOutOfBoundsException("The index is out of range");
        }
    }

    private void resize() {
        CAPACITY *= CAPACITY_EXPAND_FACTOR;
        Object[] newObjects = new Object[CAPACITY];
        System.arraycopy(objects, 0, newObjects, 0, objects.length);
        this.objects = newObjects;
    }

    private void copyListToArrayListAndResize(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            objects[i] = list.get(i);
        }
        size = list.size();
    }

    private void copyArrayToObjectsAndResizeArrayList(
            Object[] src, int srcPos, int destPos, int length, int deltaSize) {
        System.arraycopy(src, srcPos, objects, destPos, length);
        size += deltaSize;
    }
}
