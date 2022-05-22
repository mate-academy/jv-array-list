package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS
            = "failed action to index invalid position";
    private static final String MESSAGE_NOT_SUCH_ELEMENT = "there is no such element present";

    private int arrayCapacity = 10;
    private int newElementIndex = 0;
    private T[] storage;

    public ArrayList() {
        storage = (T[]) new Object[arrayCapacity];
    }

    @Override
    public void add(T value) {
        validateCapacity(newElementIndex);
        storage[newElementIndex++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > newElementIndex) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        validateCapacity(newElementIndex);
        if (index == newElementIndex) {
            storage[index] = value;
            newElementIndex++;
            return;
        }
        T[] current = (T[]) new Object[newElementIndex - index];
        System.arraycopy(storage, index, current, 0, current.length);
        storage[index] = value;
        System.arraycopy(current, 0, storage, index + 1, current.length);
        newElementIndex++;
    }

    @Override
    public void addAll(List<T> list) {
        int topBound = newElementIndex + list.size();
        validateCapacity(topBound);
        for (int i = newElementIndex, j = 0; i < topBound; i++, j++) {
            storage[i] = list.get(j);
            newElementIndex++;
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= newElementIndex) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= newElementIndex) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        storage[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= newElementIndex) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        final T current = storage[index];
        if (index < (newElementIndex - 1)) {
            System.arraycopy(storage,index + 1, storage, index, storage.length - index - 1);
        }
        storage[newElementIndex - 1] = null;
        newElementIndex--;
        return current;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < storage.length; i++) {
            if (Objects.equals(storage[i], element)) {
                if (i < (newElementIndex - 1)) {
                    System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
                }
                storage[newElementIndex - 1] = null;
                newElementIndex--;
                return element;
            }
        }
        throw new NoSuchElementException(MESSAGE_NOT_SUCH_ELEMENT);
    }

    @Override
    public int size() {
        return newElementIndex;
    }

    @Override
    public boolean isEmpty() {
        return storage[0] == null;
    }

    private void validateCapacity(int calculateCapacity) {
        if (calculateCapacity >= arrayCapacity) {
            arrayCapacity = newElementIndex + newElementIndex / 2;
            T[] temporary = (T[]) new Object[storage.length];
            System.arraycopy(storage, 0, temporary, 0, storage.length);
            storage = (T[]) new Object[arrayCapacity];
            System.arraycopy(temporary, 0, storage, 0, temporary.length);
        }
    }
}
