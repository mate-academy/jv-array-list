package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] arrayList;
    private int size;
    private int defaultCapacity = 10;

    public ArrayList() {
        arrayList = (T[]) new Object[defaultCapacity];
    }

    @Override
    public void add(T value) {
        arrayList[size] = value;
        size++;
        updateCapacity();
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            arrayList[index] = value;
            size++;
            updateCapacity();
            return;
        }
        if (index < size && index >= 0) {
            for (int i = size; i > index; i--) {
                arrayList[i] = arrayList[i - 1];
            }
            arrayList[index] = value;
            size++;
            updateCapacity();
            return;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            arrayList[size] = list.get(i);
            size++;
            updateCapacity();
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return arrayList[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            arrayList[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T value = arrayList[index];
            for (int i = index; i < size; i++) {
                arrayList[i] = arrayList[i + 1];
            }
            size--;
            return value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public T remove(T element) {
        T value = null;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == null && element == null
                    || element != null && element.equals(arrayList[i])) {
                value = arrayList[i];

                for (int j = i; j < size; j++) {
                    arrayList[j] = arrayList[j + 1];
                }
                size--;
                return value;
            }
        }
        throw new NoSuchElementException("No Such Element Exception");
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

    private void updateCapacity() {
        if (size == defaultCapacity) {
            defaultCapacity = defaultCapacity + defaultCapacity / 2;
            T[] arrayListCopy = arrayList;
            arrayList = (T[]) new Object[defaultCapacity];
            for (int i = 0; i < arrayListCopy.length; i++) {
                arrayList[i] = arrayListCopy[i];
            }
        }
    }
}
