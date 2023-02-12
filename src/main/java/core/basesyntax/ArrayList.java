package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int size;
    private int currentCapacity = 10;

    public ArrayList() {
        elements = (T[]) new Object [currentCapacity];
    }

    private void increaseCapacity() {
        T[] old = elements;
        currentCapacity = currentCapacity + (currentCapacity >> 1);
        elements = (T[]) new Object[currentCapacity];
        System.arraycopy(old,0,elements,0,size);
    }

    public int check(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element || (element != null && element.equals(elements[i])))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(T value) {
        add(value,size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        if (elements.length == size) {
            increaseCapacity();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        int k = 0;
        while (list.size() + size() >= currentCapacity) {
            increaseCapacity();
        }
        for (int i = size(); i < size() + list.size();i++) {
            elements[i] = list.get(k++);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        T removeItem = elements[index];
        for (int i = index; i < size() - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removeItem;
    }

    @Override
    public T remove(T element) {
        if (check(element) == -1) {
            throw new NoSuchElementException("Element doesnt exist");
        }
        return remove(check(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }
}
