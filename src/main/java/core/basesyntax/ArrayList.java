package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (objects.length == size) {
            increaseObjectsLength();
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        Object[] increasedObjects = new Object[objects.length + 1];
        if (index > 0) {
            System.arraycopy(objects, 0, increasedObjects, 0, index);
        }
        System.arraycopy(objects, index, increasedObjects, index + 1, objects.length - index);
        increasedObjects[index] = value;
        objects = increasedObjects;
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
        validateIndex(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Object[] decreasedObjects = new Object[objects.length - 1];
        if (index > 0) {
            System.arraycopy(objects, 0, decreasedObjects, 0, index);
        }
        System.arraycopy(objects, index + 1, decreasedObjects, index,
                decreasedObjects.length - index);
        Object removableObject = objects[index];
        objects = decreasedObjects;
        size--;
        return (T) removableObject;
    }

    @Override
    public T remove(T t) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((objects[i] != null && ((T) objects[i]).equals(t)) || objects[i] == t) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            throw new NoSuchElementException();
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

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void increaseObjectsLength() {
        Object[] increasedObjects = new Object[objects.length + 1 << objects.length];
        System.arraycopy(objects, 0, increasedObjects, 0, objects.length);
        objects = increasedObjects;
    }
}
