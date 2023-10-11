package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASE_SIZE = 10;
    private static final String INDEX_EXCEPTION_MESSAGE = "Provided index is < 0 or > list size. Please, provide valid index.";
    private Object[] objects;
    private int size;

    public ArrayList() {
        this.objects = new Object[BASE_SIZE];
    }

    private void checkFreeSpace(int neededSpace) {
        if (neededSpace > objects.length) {
            int newLength = (int)(objects.length * 1.5);
            if (newLength < neededSpace) {
                newLength = neededSpace;
            }
            Object[] newArray = new Object[newLength];
            System.arraycopy(objects, 0, newArray,0, objects.length);
            objects = new Object[]{newArray};
        }
    }


    private void expandArray() {
        objects = new Object[size + (size >> 1)];
    }
    private void checkEmptySpace() {
        if (size >= objects.length) {
            Object[] newArray = new Object[objects.length + (objects.length >> 1)];
            System.arraycopy(objects, 0, newArray,0, objects.length);
            objects = new Object[]{newArray};
        }
    }

    private void sizeIncrease() {
        size++;
    }

    private void sizeDecrease() {
        size--;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void add(T value) {
        checkFreeSpace(size + 1);
        objects[size] = value;
        sizeIncrease();
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        checkFreeSpace(size + 1);
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
        sizeIncrease();
    }

    @Override
    public void addAll(List<T> list) {
        checkFreeSpace(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            objects[size] = list.get(i);
            sizeIncrease();
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
        T returnValue = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        sizeDecrease();
        objects[size] = null;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? objects[i] == null : element.equals(objects[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such value in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 || objects == null;
    }
}
