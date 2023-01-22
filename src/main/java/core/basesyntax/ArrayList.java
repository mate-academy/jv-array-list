package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_SIZE = 10;
    private static final int DEFAULT_MULTIPLIER = 1;
    private int size;
    private T[] internalArray;

    public ArrayList() {
        internalArray = (T[]) new Object[DEFAULT_LIST_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= 0 && size < internalArray.length) {
            internalArray[size] = value;
            size++;
        } else if (size == internalArray.length) {
            grow();
            internalArray[size] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public void add(T value, int index) {
        int newIndex = index + 1;
        if (index == size) {
            add(value);
            return;
        } else if (index >= 0 && index < size) {
            T[] newArray = (T[]) new Object[internalArray.length + 1];
            for (int i = 0; i < index; i++) {
                newArray[i] = internalArray[i];
            }
            newArray[index] = value;
            int j = index;
            for (int i = newIndex; i < size + 1; i++, j++) {
                newArray[i] = internalArray[j];
            }
            size++;
            internalArray = newArray;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public void addAll(List<T> list) {
        int sizesSum = size + list.size();
        int y = size;
        T[] newArray;
        if (sizesSum > internalArray.length) {
            newArray = (T[]) new Object[sizesSum];
            for (int i = 0; i < size; i++) {
                newArray[i] = internalArray[i];
            }
            for (int i = 0; i < list.size(); i++, y++) {
                newArray[y] = list.get(i);
            }
            internalArray = newArray;
        } else {
            for (int i = 0; i < list.size(); i++, y++) {
                internalArray[y] = list.get(i);
            }
            size = sizesSum;
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return internalArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            internalArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public T remove(int index) {
        T temporaryElement;
        if (index >= 0 && index < size) {
            temporaryElement = internalArray[index];
            for (int i = index; i < size - 1; i++) {
                internalArray[i] = internalArray[i + 1];
            }
            size--;
            return temporaryElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public T remove(T element) {
        int index = searchIndex(element);
        if (index >= 0 && index < size) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Index is out of bound array!");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void grow() {
        int newLength = (int) (internalArray.length + DEFAULT_MULTIPLIER);
        T[] newArray = (T[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newArray[i] = internalArray[i];
        }
        internalArray = newArray;
    }

    public int searchIndex(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (internalArray[i] == null) {
                    return i;
                }
            }
        } else if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(internalArray[i])) {
                    return i;
                }
            }
        } else {
            throw new NoSuchElementException("Cannot search for a null value!");
        }
        return -1;
    }
}
