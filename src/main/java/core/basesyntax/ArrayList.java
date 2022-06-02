package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int BASED_ARRAY_SIZE = 10;
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[BASED_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception");
        }
        grow();
        System.arraycopy(objects,index,objects,index + 1,objects.length - index - 1);
        objects[index] = value;
        size++;
    }

    private void grow() {
        if (size == objects.length) {
            T[] arraySours;
            arraySours = objects;
            objects = (T[])new Object[arraySours.length + 5];
            System.arraycopy(arraySours,0,objects,0,objects.length - 5);

        }
    }

    @Override
    public void addAll(List<T> list) {
        int size = this.size;
        int listIndex = 0;
        for (int j = size; j < list.size() + size; j++) {
            objects[j] = list.get(listIndex);
            listIndex++;
            this.size++;
        }

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception");
        }
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception");
        }
        T element = objects[index];
        System.arraycopy(objects,index + 1, objects,index, objects.length - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T value) {
        T[] arrayContainer = objects;
        T element = objects[0];
        for (int j = 0; j < size(); j++) {
            if (Objects.equals(objects[j], value)) {
                element = objects[j];
                System.arraycopy(arrayContainer,j + 1, objects,j, objects.length - j - 1);
                break;
            }
            if (j == size() - 1) {
                throw new NoSuchElementException();
            }
        }
        size--;
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
}
