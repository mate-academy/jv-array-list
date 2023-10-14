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
        moveElementsRight(index + 1);
        arrayUnderneath[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int firstEmptyIndex = size;
        int indexFollower = 0;
        size += list.size();
        growIfArrayFull();
        for (int i = firstEmptyIndex; i < size; i++) {
            arrayUnderneath[i] = list.get(indexFollower);
            indexFollower++;
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
        moveElementsLeft(index);
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int indexToRemove = -1;
        T elementToRemove = null;

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (element == arrayUnderneath[i]) {
                    elementToRemove = arrayUnderneath[i];
                    indexToRemove = i;
                    break;
                }
            }
            if (indexToRemove == -1) {
                throw new NoSuchElementException("No such element: " + element);
            }
            moveElementsLeft(indexToRemove);
            return elementToRemove;
        }

        for (int i = 0; i < size; i++) {
            if (element.equals(arrayUnderneath[i])) {
                elementToRemove = arrayUnderneath[i];
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1) {
            throw new NoSuchElementException("No such element: " + element);
        }
        moveElementsLeft(indexToRemove);
        return elementToRemove;
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
        while (size >= arrayUnderneath.length) {
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

    private void moveElementsLeft(int startingIndex) {
        for (int i = startingIndex; i < size; i++) {
            arrayUnderneath[i] = arrayUnderneath[i + 1];
        }
        size--;
    }

    private void moveElementsRight(int startingIndex) {
        for (int i = size - 1; i >= startingIndex; i--) {
            arrayUnderneath[i] = arrayUnderneath[i - 1];
        }
    }
}
