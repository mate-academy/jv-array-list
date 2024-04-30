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
        Object[] newArray = new Object[objects.length];
        try {
            if (size() != 0 && index > size() || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Index "
                        + index + " out of bounds for length " + size());
            }
            int prevIndex = 0;
            for (int i = 0; i < newArray.length; i++) {
                if (i == index) {
                    newArray[i] = value;
                } else {
                    newArray[i] = objects[prevIndex++];
                }
            }
            size++;
            objects = newArray;
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(e.getMessage());
        }

    }

    @Override
    public void addAll(List<T> list) {
        int index = size;
        while (list.size() > objects.length) {
            isFull();
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                objects[index++] = list.get(i);
                size++;
            }
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override
    public T get(int index) {
        try {
            if (index >= size() || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Index "
                        + index + " out of bounds for length " + size());
            }
            return (T) objects[index];

        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            if (index >= size() || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Index "
                        + index + " out of bounds for length " + size());
            }
            objects[index] = value;
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override
    public T remove(int index) {
        Object answer;
        try {
            if (index >= size() || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Index "
                        + index + " out of bounds for length " + size());
            }
            answer = objects[index];
            int prevIndex = 0;
            boolean objectFound = false;

            for (int i = 0; i < size(); i++) {
                if (objects[i] == null && answer != null) {
                    objects[prevIndex++] = objects[i];
                    continue;
                }

                if (objects[i] != answer || objectFound) {
                    objects[prevIndex++] = objects[i];
                } else {
                    objectFound = true;
                }
            }

            while (prevIndex < size()) {
                objects[prevIndex++] = null;
            }
            size--;
            return (T) answer;
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override
    public T remove(T element) {
        try {
            int j = 0;
            boolean objectFound = false;

            for (int i = 0; i < size(); i++) {
                if (objects[i] == null && element != null) {
                    objects[j++] = objects[i];
                    continue;
                }

                if (objects[i] == null && element == null) {
                    objectFound = true;
                    continue;
                }

                if (!objects[i].equals(element) || objectFound) {
                    objects[j++] = objects[i];
                } else {
                    objectFound = true;
                }
            }

            while (j < size()) {
                objects[j++] = null;
            }
            if (!objectFound) {
                throw new NoSuchElementException();
            }
            size--;
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
}
