package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_SIZE = 10;
    private static final String OF_BOUND_MESSAGE = "Out of bound index in ArrayList";
    private static final String OF_ABSENT_MESSAGE = "Element of ArrayList ain't present";
    private T[] storage;
    private int size;

    public ArrayList() {
        this.storage = (T[]) new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        if (storage.length == size) {
            increaseSize();
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        inspectIndex(index, size + 1);
        T[] tempStorage = (T[]) new Object[size - index];
        System.arraycopy(storage, index, tempStorage, 0, tempStorage.length);
        size = index;
        add(value);
        for (int i = 0; i < tempStorage.length; i++) {
            add(tempStorage[i]);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        inspectIndex(index, size);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        inspectIndex(index, size);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        inspectIndex(index, size);
        T result = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(OF_ABSENT_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void inspectIndex(int index, int bound) {
        if (index < 0 || index >= bound) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
    }

    private void increaseSize() {
        int extendSize = size + (size >> 1);
        T[] tempStorage = (T[]) new Object[extendSize];
        System.arraycopy(storage, 0, tempStorage, 0, storage.length);
        storage = tempStorage;
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Another string");
        arrayList.add(null);
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.add(null);
        arrayList.remove("Java");
        System.out.println(arrayList.size());
        System.out.println(arrayList.get(3));
        arrayList.remove("String");
        System.out.println(arrayList.size());
        System.out.println(arrayList.get(2));
    }

}
