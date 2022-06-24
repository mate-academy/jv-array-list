package core.basesyntax;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Objects[] elementsData;
    private Exception ArrayListIndexOutOfBoundsException;


    public Objects[] grow(int currentCapasity){
        int newCapacity = currentCapasity + (currentCapasity >> 1);
        return elementsData = Arrays.copyOf(elementsData, newCapacity);
    }
    @Override
    public void add(T value) {
        if (size == elementsData.length) {
            elementsData = grow(elementsData.length);
        }
        elementsData[size] = (Objects) value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size) {
            try {
                if (index > size) {
                    throw core.basesyntax.ArrayListIndexOutOfBoundsException;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (size == elementsData.length) {
            elementsData = grow(elementsData.length);
        }
        elementsData[index] = (Objects) value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
