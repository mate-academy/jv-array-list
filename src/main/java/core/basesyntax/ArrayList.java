package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_BEGINNING = 0;
    private static final int GROWTH_FACTOR = 2;
    private static final String INDEX_ERR_MSG = "Index: [%d] is out of bounds for list size [%d]!";

    private int size;
    private T[] elementStore;

    public ArrayList() {
        elementStore = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        elementStore[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!(index > -1 && index <= size)) {
            throw new ArrayListIndexOutOfBoundsException(String.format(INDEX_ERR_MSG, index,size));
        }
        size++;
        growIfFull();
        System.arraycopy(elementStore, index, elementStore,
                index + 1, calculateElementsToCopy(index));
        set(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        growToCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexLegal(index);
        return elementStore[index];
    }

    @Override
    public T remove(int index) {
        T oldValue;
        isIndexLegal(index);
        oldValue = elementStore[index];
        if (dataArrayIsFull()) {
            elementStore[index] = null;
            size--;
            return oldValue;
        }
        System.arraycopy(elementStore, index
                + 1, elementStore, index, calculateElementsToCopy(index));
        elementStore[size] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementStore[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: "
                + element.toString() + " in list.");
    }

    @Override
    public void set(T value, int index) {
        isIndexLegal(index);
        elementStore[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(elementStore[0]);
        for (int i = 1; i < elementStore.length; i++) {
            if (i < size) {
                sb.append(", ").append(elementStore[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private int calculateElementsToCopy(int index) {
        return size - index;
    }

    private void copyDataStore(int newCapacity) {
        T[] newStore = (T[]) new Object[newCapacity];
        System.arraycopy(elementStore, ARRAY_BEGINNING, newStore, ARRAY_BEGINNING, size);
        elementStore = newStore;
    }

    private void growIfFull() {
        if (dataArrayIsFull()) {
            int newCapacity = size + size / GROWTH_FACTOR;
            copyDataStore(newCapacity);
        }
    }

    private void growToCapacity(int requiredCapacity) {
        int newCapacity = size + requiredCapacity;
        copyDataStore(newCapacity);
    }

    private void isIndexLegal(int index) {
        if (!(index > -1 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException(String.format(INDEX_ERR_MSG, index,size));
        }
    }

    private boolean dataArrayIsFull() {
        return size == elementStore.length;
    }
}
