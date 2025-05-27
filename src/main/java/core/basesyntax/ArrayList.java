package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int CREATION_SIZE = 10;
    private static final double SIZE_MULTIPLIER = 1.5;

    private Object[] mainArray;
    private int size;
    private int maxSize;

    public ArrayList() {
        mainArray = new Object[CREATION_SIZE];
        this.size = 0;
        maxSize = CREATION_SIZE;
    }

    @Override
    public void add(T value) {
        if (size == maxSize) {
            grow();
        }
        mainArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == maxSize) {
            grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index must be less than size. Index: "
                    + index + ", size: " + size);
        } else {
            Object temp1 = null;
            Object temp2 = null;
            for (int i = 0; i <= size; i++) {
                if (i == index) {
                    temp1 = mainArray[i];
                    mainArray[i] = value;
                } else if (i > index) {
                    temp2 = mainArray[i];
                    mainArray[i] = temp1;
                    temp1 = temp2;

                }
            }
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int listIndex = 0;
        while (maxSize < list.size()) {
            grow();
        }
        for (int i = size; i < (size + list.size()); i++) {
            mainArray[i] = list.get(listIndex++);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element with index: "
                    + index + " doesn't exist");
        } else {
            return (T) mainArray[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element with index: "
                    + index + " doesn't exist");
        } else {
            mainArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element with index: "
                    + index + " doesn't exist");
        }
        final Object oldValue = mainArray[index];
        int newArrayIndex = 0;
        Object[] tempArray = mainArray;
        mainArray = new Object[maxSize];

        for (int i = 0; i < size; i++) {
            if (i != index) {
                mainArray[newArrayIndex++] = tempArray[i];
            }
        }
        size--;

        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        Object oldValue = null;
        Object[] tempArray = mainArray;
        mainArray = new Object[maxSize];
        boolean isExist = false;
        int newArrayIndex = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, tempArray[i])) {
                isExist = true;
                oldValue = tempArray[i];
                size--;
            } else {
                mainArray[newArrayIndex++] = tempArray[i];
            }
        }
        if (!isExist) {
            throw new NoSuchElementException("Element doesn't exist");
        }

        return (T) oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }

    public void grow() {
        Object[] copyArray = mainArray;
        maxSize = (int) (maxSize * SIZE_MULTIPLIER);
        mainArray = new Object[maxSize];
        for (int i = 0; i < copyArray.length; i++) {
            mainArray[i] = copyArray[i];
        }
    }
}
