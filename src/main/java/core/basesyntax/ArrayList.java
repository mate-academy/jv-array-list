package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_ARRAY_LENGTH = 0;
    private Object[] objects;

    public ArrayList() {
        objects = new Object[START_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        objects = grow((T[]) (objects));
        if (objects.length != 0) {
            objects[objects.length - 1] = value;
        } else {
            objects = grow((T[]) (objects));
            objects[0] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        try {
            if (objects.length == 0) {
                objects = grow((T[]) (objects));
                objects[0] = value;
            } else {
                Object [] clonedArray = new Object[objects.length + 1];
                for (int i = 0; i <= index; i++) {
                    clonedArray[i] = (i == index) ? value : objects[i];
                }
                for (int i = index; i < objects.length; i++) {
                    clonedArray[i + 1] = objects[i];
                }
                objects = grow((T[]) (objects));
                objects = clonedArray;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("No such index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        try {
            return (T) (objects[index]);
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            objects[index] = value;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Such index does not exist");
        }

    }

    @Override
    public T remove(int index) {
        Object removed = -1;
        try {
            if (objects.length != 0) {
                Object [] temp = new Object[objects.length - 1];
                removed = objects[index];
                for (int i = 0; i < index; i++) {
                    temp[i] = objects[i];
                }
                if (index != objects.length - 1) {
                    for (int i = index + 1; i < objects.length; i++) {
                        temp[i - 1] = objects[i];
                    }
                }
                objects = temp;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Such index does not exist");
        }
        return (T) (removed);
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < objects.length; i++) {
            index = (Objects.equals(element, objects[i])) ? i : index;
        }
        if (index == -1) {
            throw new NoSuchElementException("Such element does not exist");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return objects.length;
    }

    @Override
    public boolean isEmpty() {
        if (objects.length == 0) {
            return true;
        }
        for (int i = 0; i < objects.length; i++) {
            if (Objects.equals(objects[i], "")
                    || Objects.equals(objects[i], null)) {
                return true;
            }
        }
        return false;
    }

    private T[] grow(T[] inputArray) {
        int value = inputArray.length + 1;
        Object [] startArray = new Object[value];
        if (inputArray.length > 0) {
            startArray = Arrays.copyOf(inputArray, value);
        }
        return (T[]) (startArray);
    }
}
