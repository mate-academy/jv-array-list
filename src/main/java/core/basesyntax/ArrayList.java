package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private int size;

    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[capacity];
    }

    public ArrayList(int arrayListCapacity) {
        this.elementData = (T[]) new Object[arrayListCapacity];
        capacity = arrayListCapacity;
    }

    private void increaseCapacity() {
        if (this.size == elementData.length) {
            capacity = (capacity * 3) / 2 + 1;
            T[] tempElementData = (T[]) new Object[capacity];
            System.arraycopy(elementData, 0, tempElementData, 0, size);
            elementData = tempElementData;
        }
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            increaseCapacity();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > this.capacity - this.size) {
            capacity = this.capacity - this.size + list.size();
            T[] tempElementData = (T[]) new Object[capacity];
            System.arraycopy(elementData, 0, tempElementData, 0, this.size);
            elementData = tempElementData;
            for (int i = 0; i < list.size(); i++) {
                elementData[this.size + i] = list.get(i);
            }
            this.size += list.size();
        } else {
            for (int i = 0; i < list.size(); i++) {
                elementData[this.size + i] = list.get(i);
            }
            this.size += list.size();
        }
    }

    @Override
    public T get(int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return elementData[i];
            }
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
    }

    @Override
    public void set(T value, int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                elementData[i] = value;
                return;
            }
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = 0; i < size; i++) {
                if (index == i) {
                    T removed = elementData[i];
                    System.arraycopy(elementData, i + 1, elementData, i, size - (i + 1));
                    size--;
                    return removed;
                }
            }
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                T removed = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - (i + 1));
                size--;
                return removed;
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
