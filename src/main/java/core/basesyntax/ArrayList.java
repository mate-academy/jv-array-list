package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int NOT_FOUND_INDEX = -1;
    private T[] content;
    private int size;

    public ArrayList() {
        content = (T[]) new Object[DEFAULT_SIZE];
    }

    public ArrayList(int customSize) {
        content = (T[]) new Object[customSize];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        content[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (!(index >= 0 && index <= size)) {
            String errorMessage = String.format("Can't add value at index %s. It's out of bounds.",
                    index);
            throw new ArrayListIndexOutOfBoundsException(errorMessage);
        }
        growIfNeeded();
        System.arraycopy(content, index, content, index + 1, size++ - index);
        content[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        growIfNeeded(listSize);

        T[] listArray = convertListToArray(list);
        System.arraycopy(listArray, 0, content, size, listSize);
        size += listSize;
    }

    @Override
    public T get(int index) {
        if (isInRange(index)) {
            return content[index];
        }
        String errorMessage = String.format("Can't get value with index %s. It's out of bounds.",
                index);
        throw new ArrayListIndexOutOfBoundsException(errorMessage);
    }

    @Override
    public void set(T value, int index) {
        if (!isInRange(index)) {
            String errorMessage = String.format("Can't set value at index %s. It's out of bounds.",
                    index);
            throw new ArrayListIndexOutOfBoundsException(errorMessage);
        }
        content[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isInRange(index)) {
            T deletedValue = content[index];
            System.arraycopy(content, index + 1, content, index, size-- - (index + 1));
            return deletedValue;
        }
        String errorMessage = String.format("Can't remove value at index %s. It's out of bounds.",
                index);
        throw new ArrayListIndexOutOfBoundsException(errorMessage);
    }

    @Override
    public T remove(T element) {
        int index = getValueIndex(element);
        if (index == NOT_FOUND_INDEX) {
            throw new NoSuchElementException("There is no such element in array.");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded() {
        if (size == content.length) {
            grow();
        }
    }

    private void growIfNeeded(int sizeToExtend) {
        int currentLength = content.length;
        if (sizeToExtend > currentLength - size) {
            int missingCells = Math.max(sizeToExtend - (currentLength - size), currentLength >> 1);
            grow(missingCells);
        }
    }

    private void grow() {
        increaseContentLengthByNumber(content.length >> 1);
    }

    private void grow(int num) {
        increaseContentLengthByNumber(num);
    }

    private void increaseContentLengthByNumber(int extraCells) {
        int oldLength = content.length;
        int newLength = oldLength + extraCells;
        T[] tempArray = (T[]) new Object[newLength];
        System.arraycopy(content, 0, tempArray, 0, oldLength);
        content = tempArray;
    }

    private T[] convertListToArray(List<T> list) {
        int listSize = list.size();
        T[] array = (T[]) new Object[listSize];
        for (int i = 0; i < listSize; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private boolean isInRange(int index) {
        return index >= 0 && index < size;
    }

    private int getValueIndex(T value) {
        for (int i = 0; i < content.length; i++) {
            T element = content[i];
            if (isObjectsEqual(element, value)) {
                return i;
            }
        }
        return NOT_FOUND_INDEX;
    }

    private boolean isObjectsEqual(T a, T b) {
        return a == b || a != null && a.equals(b);
    }
}
