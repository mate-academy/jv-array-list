package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROWTH_MULTIPLIER = 1.5;
    private T[] arrayUnderneath;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Capacity can't a minus number!");
        }
        arrayUnderneath = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        arrayUnderneath[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

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
        int indexToRemove = -1;
        T foundedElement = null;
        for (int i = 0; i < size; i++) {
            if (element.equals(arrayUnderneath[i])) {
                foundedElement = arrayUnderneath[i];
                break;
            }
            indexToRemove++;
        }
        if (indexToRemove == -1) {
            throw new NoSuchElementException("No such element: " + element);
        }
        for (int i = indexToRemove; i < size; i++) {
            arrayUnderneath[i] = arrayUnderneath[i + 1];
        }
        size--;
        return foundedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == arrayUnderneath.length) {
            int newArraySize = (int) Math.round(arrayUnderneath.length * GROWTH_MULTIPLIER);
            T[] newArray = (T[]) new Object[newArraySize];
            System.arraycopy(arrayUnderneath, 0, newArray, 0, arrayUnderneath.length);
            arrayUnderneath = newArray;
        }
    }
}
