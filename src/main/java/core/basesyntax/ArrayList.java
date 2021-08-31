package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private static final String ADD = "ADD";
    private static final String NO_ADD = "noADD";
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexValid(index, "ADD");
        resize();
        System.arraycopy(elementData, index,elementData, index + 1, size - index);
        elementData[index] = value;
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
        isIndexValid(index, "noADD");
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index, "noADD");
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index, "noADD");
        T oldRecord = elementData[index];
        System.arraycopy(elementData,index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldRecord;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null
                    && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not find");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == elementData.length) {
            System.arraycopy(elementData,
                    0,
                    elementData = (T[]) new Object[(int) (elementData.length * GROW_COEFFICIENT)],
                    0,
                    size);
        }
    }

    private void isIndexValid(int index, String operation) {
        if ((operation.equals(ADD) && (index > size || index < 0))
                || (operation.equals(NO_ADD) && (index < 0 || index >= size))) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
