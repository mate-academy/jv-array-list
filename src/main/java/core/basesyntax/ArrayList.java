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
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
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
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elementData[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            final Object[] localArray = elementData;

            T oldValue = (T) localArray[index];
            remover(localArray, index);
            return oldValue;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (t.equals(elementData[i])) {
                final Object[] localElements = elementData;
                T oldValue = (T) localElements[i];
                remover(localElements, i);
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

    public  void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            newCapacity();
        }
    }

    private void newCapacity() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        T[] oldData = elementData;
        elementData = (T[]) new Object[newCapacity];
        System.arraycopy(oldData, 0, elementData, 0, size);
    }

    private void remover(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }
}
