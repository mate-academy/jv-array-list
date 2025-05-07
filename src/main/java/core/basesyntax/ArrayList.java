package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_STORAGE_CAPACITY = 10;
    private static final double STORAGE_GROW_MULTIPLIER = 1.5;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index is out of bounds";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "No such element found";
    private int size;
    private T[] storage;

    public ArrayList() {
        storage = (T[]) new Object[INITIAL_STORAGE_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArrayIfRequired();
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growArrayIfRequired();
        System.arraycopy(storage, index,
                    storage, index + 1,
                    size - index);
        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growArrayIfRequired();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForGet(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGet(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForGet(index);
        T element = storage[index];
        System.arraycopy(storage, index + 1, storage, index, --size - index);
        return element;
    }

    @Override
    public T remove(T element) {
        int elementIndex = findIndex(element);
        if (elementIndex == -1) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
        }
        return remove(elementIndex);
    }

    private int findIndex(T element) {
        int elementIndex = -1;
        for (int i = 0; i < size; i++) {
            if (isElementsEquals(element, storage[i])) {
                elementIndex = i;
                break;
            }
        }
        return elementIndex;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArrayIfRequired() {
        boolean shouldGrow = storage.length < size + 1;
        if (shouldGrow) {
            grow();
        }
    }

    private void grow() {
        int newStorageLength = (int) (storage.length * STORAGE_GROW_MULTIPLIER);
        T[] newStorage = (T[]) new Object[newStorageLength];
        System.arraycopy(storage, 0, newStorage, 0, size);
        storage = newStorage;
    }

    private void checkIndexForGet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private boolean isElementsEquals(T a, T b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
