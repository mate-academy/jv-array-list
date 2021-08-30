package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        extendCapacityIfNecessary();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        extendCapacityIfNecessary();
        if (index >= 0 && index <= size) {
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
            arrayList[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index.");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Adding list is null");
        }
    }

    @Override
    public T get(int index) {
        checkIndexValidity(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidity(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidity(index);
        final T removedElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        arrayList[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || (arrayList[i] != null && arrayList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The value you want to remove does not exist");
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendCapacityIfNecessary() {
        if (size == arrayList.length) {
            int tempLength = arrayList.length;
            T[] tempArray = (T[]) new Object[tempLength];
            tempArray = arrayList;
            arrayList = (T[]) new Object[(tempLength * 3) / 2 + 1];
            System.arraycopy(tempArray, 0, arrayList, 0, size);
        }
    }

    private void checkIndexValidity(int index) {
        if (index >= 0 && index < size) {
            return;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index.");
        }
    }
}
