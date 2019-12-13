package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int localCapacity) {
        elementData = (T[]) new Object[localCapacity];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
        elementData[index] = value;
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
        if (index < size && index >= 0) {
            return elementData[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T oldValue = (T) elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
            elementData[size--] = null;
            return oldValue;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null) {
                System.arraycopy(elementData, i + 1, elementData, i, size - 1 - i);
                size--;
                return null;
            } else if (t.equals(elementData[i])) {
                T oldValue = (T) elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - 1 - i);
                elementData[size--] = null;
                return oldValue;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public  void ensureCapacity() {
        if (size + 1 > elementData.length) {
            newCapacity();
        }
    }

    private void newCapacity() {
        T[] oldData = elementData;
        elementData = (T[]) new Object[elementData.length + elementData.length / 2];
        System.arraycopy(oldData, 0, elementData, 0, size);
    }
}
