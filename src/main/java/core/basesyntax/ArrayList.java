package core.basesyntax;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_ARRAY_LENGTH = 10;
    private int arraySize;
    private T[] objects;

    public ArrayList() {
        objects = (T[]) (Array.newInstance(Object.class, START_ARRAY_LENGTH));
    }

    @Override
    public void add(T value) {
        if (objects.length == arraySize) {
            objects = grow(objects);
        }
        objects[arraySize] = value;
        arraySize += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0) {
            boundsValidation(index - 1);
        }
        Object [] clonedArray = new Object[objects.length];
        if (objects.length == arraySize) {
            clonedArray = grow(objects);
        }
        for (int i = 0; i <= index; i++) {
            clonedArray[i] = (i == index) ? value : objects[i];
        }
        for (int i = index; i < arraySize; i++) {
            clonedArray[i + 1] = objects[i];
        }
        arraySize += 1;
        objects = (T[]) (clonedArray);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        boundsValidation(index);
        return (objects[index]);
    }

    @Override
    public void set(T value, int index) {
        boundsValidation(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        boundsValidation(index);
        Object [] temp = new Object[objects.length];
        for (int i = 0; i < index; i++) {
            temp[i] = objects[i];
        }
        if (index != objects.length - 1) {
            for (int i = index + 1; i < objects.length; i++) {
                temp[i - 1] = objects[i];
            }
        }
        arraySize -= 1;
        T removed = objects[index];
        objects = (T[]) (temp);
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < arraySize; i++) {
            index = (Objects.equals(element, objects[i])) ? i : index;
        }
        if (index == -1) {
            throw new NoSuchElementException("Such element does not exist");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        if (arraySize == 0) {
            return true;
        }
        for (int i = 0; i < arraySize; i++) {
            if (Objects.equals(objects[i], "")
                    || Objects.equals(objects[i], null)) {
                return true;
            }
        }
        return false;
    }

    private T[] grow(T[] inputArray) {
        Object [] expandedArray = Arrays.copyOf(inputArray, (int) (inputArray.length * 1.5));
        return (T[]) (expandedArray);
    }

    private void boundsValidation(int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
    }
}
