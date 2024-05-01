package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STARTING_SIZE = 10;
    private Object[] objects;
    private int size = 0;

    public ArrayList() {
        objects = new Object[STARTING_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= objects.length) {
            isFull();
            objects[size] = value;
            size++;
            return;
        }
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
        objects = newArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > objects.length) {
            isFull();
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) objects[index];
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
        objects = newArr;
        size--;
        return (T) answer;

    }

    @Override
    public T remove(T element) {
        try {
            boolean objectFound = false;
            for (int i = 0; i < size(); i++) {
                if (objects[i] == null && element != null) {
                    continue;
                }

                if (objects[i] == null && element == null) {
                    objectFound = true;
                    remove(i);
                    break;
                }

                if (objects[i].equals(element)) {
                    objectFound = true;
                    remove(i);
                    break;
                }
            }
            if (!objectFound) {
                throw new NoSuchElementException();
            }
            return element;
        } catch (NoSuchElementException | NullPointerException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Object o : objects) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    private void isFull() {
        Object[] newObject = new Object[(int) (objects.length * 1.5)];
        System.arraycopy(objects, 0, newObject, 0, objects.length);
        objects = newObject;
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
}
