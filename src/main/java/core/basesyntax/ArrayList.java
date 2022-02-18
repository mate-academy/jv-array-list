package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int maxArraySize = 10;
    private int currentArraySize = 0;
    private T[] inner = (T[]) new Object[maxArraySize];

    public ArrayList() {
    }

    @Override
    public void add(T value) {
        if (currentArraySize < maxArraySize) {
            inner[currentArraySize] = value;
        } else {
            maxArraySize++;
            T[] innerCopy = (T[]) new Object[maxArraySize];
            System.arraycopy(inner, 0, innerCopy, 0, maxArraySize - 1);
            innerCopy[currentArraySize] = value;
            this.inner = innerCopy;
        }
        currentArraySize++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > currentArraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
        try {
            maxArraySize++;
            T[] copyAfter = (T[]) new Object[maxArraySize];
            System.arraycopy(inner, 0, copyAfter, 0, index);
            copyAfter[index] = value;
            System.arraycopy(inner, index, copyAfter, index + 1, currentArraySize - index);
            this.inner = copyAfter;
            currentArraySize++;
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T current = (T) list.get(i);
            add(current);
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= currentArraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
        try {
            return inner[index];
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= currentArraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
        try {
            inner[index] = value;
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= currentArraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
        try {
            T[] copyAfter = (T[]) new Object[currentArraySize];
            System.arraycopy(inner, 0, copyAfter, 0, currentArraySize - index);
            System.arraycopy(inner, index + 1, copyAfter, index, currentArraySize - index - 1);
            T output = inner[index];
            this.inner = copyAfter;
            currentArraySize--;
            return output;
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        try {
            for (int k = 0; k < currentArraySize; k++) {
                if (Objects.equals(element, inner[k])) {
                    return remove(k);
                }
            }
            throw new NoSuchElementException("Element not found!");
            //return null;
        } catch (RuntimeException e) {
            throw new NoSuchElementException("Element not found!");
        }
    }

    @Override
    public int size() {
        return currentArraySize;
    }

    @Override
    public boolean isEmpty() {
        if (currentArraySize == 0) {
            return true;
        }
        return false;
    }
}
