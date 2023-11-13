package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] objects;
    private int size;

    public ArrayList() {
        size = 0;
        objects = new Object[10];
    }

    @Override
    public void add(T value) {
        if (size == objects.length) {
            Object[] newObject = new Object[objects.length + (int) objects.length / 2];
            for (int i = 0; i < objects.length; i++) {
                newObject[i] = objects[i];
            }
            objects = newObject;
        }

        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == objects.length) {
            Object[] newObject = new Object[objects.length + (int) objects.length / 2];
            for (int i = 0; i < objects.length; i++) {
                newObject[i] = objects[i];
            }
            objects = newObject;
        }
        if (size == 0 && index == size) {
            objects[0] = value;
            size++;
        } else if ((size > index || size == 0) && index >= 0) {
            for (int i = size; i > index; i--) {
                objects[i] = objects[i - 1];
            }
            objects[index] = value;
            size++;
        } else if (index == size) {
            objects[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            objects[size] = list.get(i);
            size++;
            if (size == objects.length) {
                Object[] newObject = new Object[objects.length + (int) objects.length / 2];
                for (int j = 0; j < objects.length; j++) {
                    newObject[j] = objects[j];
                }
                objects = newObject;
            }
        }
    }

    @Override
    public T get(int index) {
        if (size > index && index >= 0) {
            return (T) objects[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    @Override
    public void set(T value, int index) {
        if (size > index && index >= 0) {
            objects[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index invalid");
        }
    }

    @Override
    public T remove(int index) {
        if (size > index && index >= 0) {
            final T newElem = (T) objects[index];

            for (int i = index; i < size - 1; i++) {
                objects[i] = objects[i + 1];
            }
            objects[size - 1] = null;
            size--;
            return newElem;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index invalid");
        }
    }

    @Override
    public T remove(T element) {
        int isPresent = 0;
        T newElem = element;
        if (element == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (newElem != null) {
                if (newElem.equals(objects[i])) {
                    isPresent++;
                    objects[i] = objects[i + 1];
                    newElem = (T) objects[i + 1];
                }
            } else if (!element.equals(newElem)) {
                objects[i] = objects[i + 1];
                newElem = (T) objects[i + 1];
            }
        }
        if (isPresent == 0) {
            throw new NoSuchElementException();
        }
        size--;
        return (T) element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
