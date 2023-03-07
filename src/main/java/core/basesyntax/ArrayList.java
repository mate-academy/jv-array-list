package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size;
    private int maxSize;

    public ArrayList() {
        size = 0;
        maxSize = DEFAULT_SIZE;
        array = (T[]) (new Object[maxSize]);
    }

    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size >= maxSize) {
            array = getExpanded();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't add an element at that index");
        } else if (index == size) {
            array[index] = value;
        } else {
            array = insertAndCopy(value, index);
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element with such index");
        } else {
            return array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element with such index");
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T oldValue = null;
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element with such index");
        } else {
            oldValue = get(index);
            array = removeAndCopy(index);
            size--;
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        return removeAndCopy(element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] getExpanded() {
        maxSize *= 2;
        T[] expandedArray = (T[]) (new Object[maxSize]);
        System.arraycopy(array, 0, expandedArray, 0, array.length);
        return expandedArray;
    }

    private T[] insertAndCopy(T value, int index) {
        T[] copyWithInsertion = (T[]) (new Object[maxSize]);
        for (int i = 0; i <= size; i++) {
            if (i == index) {
                copyWithInsertion[i] = value;
            } else if (i > index) {
                copyWithInsertion[i] = array[i - 1];
            } else {
                copyWithInsertion[i] = array[i];
            }
        }
        return copyWithInsertion;
    }

    private T[] removeAndCopy(int index) {
        T[] copyWithRemoval = (T[]) (new Object[maxSize]);
        for (int i = 0; i < size; i++) {
            if (i > index) {
                copyWithRemoval[i - 1] = array[i];
            } else if (i < index) {
                copyWithRemoval[i] = array[i];
            }
        }
        return copyWithRemoval;
    }

    private T removeAndCopy(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("you tried to remove a non-existent element");
    }
}
