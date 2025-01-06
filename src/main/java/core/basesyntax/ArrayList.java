package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final static int START_SIZE = 10;
    private Object[] dataStorage;
    private int size;

    public ArrayList() {
        dataStorage = new Object[START_SIZE];
        size = 0;
    }
    @Override
    public void add(T value) {
        size++;
        if (size > dataStorage.length) {
            this.grow();
        }
        this.dataStorage[size-1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add object");
        }
        if (size == dataStorage.length) {
            grow();
        }
        System.arraycopy(dataStorage, index, dataStorage, index + 1, size - index);
        dataStorage[index] = value;
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
            throw new ArrayListIndexOutOfBoundsException("Can't get object");
        }
        return (T) this.dataStorage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't set object");
        }
        this.dataStorage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove object");
        }
        T removedElement = (T) dataStorage[index];
        System.arraycopy(dataStorage, index + 1, dataStorage, index, size - index - 1);
        dataStorage[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (this.dataStorage[i] == null && element == null) {
                return remove(i);
            }
            if (this.dataStorage[i] != null && this.dataStorage[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    private void grow() {
        Object[] newStorage = new Object[(int) (dataStorage.length * 1.5)];
        System.arraycopy(dataStorage, 0, newStorage, 0, dataStorage.length);
        dataStorage = newStorage;
    }
}
