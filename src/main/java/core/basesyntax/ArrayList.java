package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROWTH_MULTIPLIER = 1.5;
    private T[] arrayUnderneath;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new RuntimeException("Capacity can't a minus number!");
        }
        arrayUnderneath = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T value) {
        size++;
        growIfArrayFull();
        arrayUnderneath[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        size++;
        growIfArrayFull();
        System.arraycopy(arrayUnderneath, index, arrayUnderneath, index + 1, arrayUnderneath.length - (index + 1));
        arrayUnderneath[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validIndexCheck(index);
        return arrayUnderneath[index];
    }

    @Override
    public void set(T value, int index) {
        validIndexCheck(index);
        arrayUnderneath[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndexCheck(index);
        T elementToRemove = arrayUnderneath[index];
        removeElement(index);
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayUnderneath[i] || (element != null
                    && element.equals(arrayUnderneath[i]))) {
                T elementToRemove = arrayUnderneath[i];
                removeElement(i);
                return elementToRemove;
            }
        }
        throw new NoSuchElementException("No such element: " + element);
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
        if (size >= arrayUnderneath.length) {
            int newArraySize = (int) Math.round(arrayUnderneath.length * GROWTH_MULTIPLIER);
            T[] newArray = (T[]) new Object[newArraySize];
            System.arraycopy(arrayUnderneath, 0, newArray, 0, arrayUnderneath.length);
            arrayUnderneath = newArray;
        }
    }

    private void validIndexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void removeElement(int indexToRemove) {
        System.arraycopy(arrayUnderneath, indexToRemove + 1, arrayUnderneath, indexToRemove,
                arrayUnderneath.length - (indexToRemove + 1));
        size--;
    }
}
