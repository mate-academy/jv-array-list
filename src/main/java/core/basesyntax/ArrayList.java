package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Storage<T>[] dataStorage;

    public ArrayList() {
        dataStorage = new Storage[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        sizeCheck();
        dataStorage[size] = new Storage<>(value);
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            indexCheck(index);
        }
        sizeCheck();
        System.arraycopy(dataStorage, index, dataStorage, index + 1, size - index);
        dataStorage[index] = new Storage<>(value);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > dataStorage.length - size) {
            grow(size + list.size());
        }
        Storage<T>[] newStorages = new Storage[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newStorages[i] = new Storage<>(list.get(i));
        }
        System.arraycopy(newStorages, 0, dataStorage, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return dataStorage[index].object;
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        dataStorage[index].object = value;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (dataStorage[i].object == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(dataStorage[i].object)) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T object = dataStorage[index] == null ? null : dataStorage[index].object;
        size--;
        System.arraycopy(dataStorage, index + 1, dataStorage, index, size - index);
        dataStorage[size] = null;
        return object;
    }

    private void indexCheck(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index less than zero");
        } else if (index != 0 && index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Not found element with index: " + index);
        }
    }

    private void sizeCheck() {
        if (size == dataStorage.length) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = dataStorage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        grow(newCapacity);
    }

    private void grow(int newCapacity) {
        Storage<T>[] newStorages = new Storage[newCapacity];
        System.arraycopy(dataStorage, 0, newStorages, 0, size);
        dataStorage = newStorages;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static class Storage<T> {
        private T object;

        public Storage(T object) {
            this.object = object;
        }
    }
}
