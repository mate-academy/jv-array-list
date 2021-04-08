package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SHOULD_GROW = 1.5;
    private static final int MIN_INDEX_VALUE = 0;
    private static final int DIFFERENCE_IN_SIZES = 1;
    private int size;
    private Object[] objects;

    public ArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < objects.length) {
            if (index <= size && index >= MIN_INDEX_VALUE) {
                System.arraycopy(objects, index, objects,
                        index + DIFFERENCE_IN_SIZES, size - index);
                objects[index] = value;
                size++;
            } else {
                throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList bounds");
            }
        } else {
            objects = grow();
            add(value,index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + (size - DIFFERENCE_IN_SIZES) < objects.length) {
            for (int i = MIN_INDEX_VALUE; i < list.size(); i++) {
                add(list.get(i));
            }
        } else {
            objects = grow();
            addAll(list);
        }
    }

    private Object[] grow() {
        Object[] biggerOne = new Object[(int)(objects.length * SHOULD_GROW)];
        System.arraycopy(objects, MIN_INDEX_VALUE, biggerOne, MIN_INDEX_VALUE, size);
        return biggerOne;
    }

    @Override
    public T get(int index) {
        if (index < size && index >= MIN_INDEX_VALUE) {
            return (T) objects[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= MIN_INDEX_VALUE) {
            objects[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= MIN_INDEX_VALUE) {
            T removedElement = (T) objects[index];
            System.arraycopy(objects, index + DIFFERENCE_IN_SIZES,
                    objects, index, size - index);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList bounds");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = MIN_INDEX_VALUE; i < size; i++) {
            if (objects[i] == null ? objects[i] == element
                    : objects[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in an Array list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == MIN_INDEX_VALUE;
    }
}
