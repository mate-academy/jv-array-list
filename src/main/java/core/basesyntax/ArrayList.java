package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    private static final double COEFFICIENT_OF_EXPANSION = 1.5;
    private int size;
    private int lengthOfArrayList;
    private T[] arrayList;

    public ArrayList() {
        this.arrayList = (T[]) new Object[INIT_SIZE];
        this.size = 0;
        this.lengthOfArrayList = INIT_SIZE;
    }

    @Override
    public void add(T value) {
        expand();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        expand();
        for (int i = size(); i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }
        arrayList[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= lengthOfArrayList) {
            lengthOfArrayList = list.size();
            expand();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = arrayList[index];
        for (int i = index; i < size() - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[size() - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (arrayList[i] != null && arrayList[i].equals(element) || arrayList[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size && index != 0) {
            throw new ArrayListIndexOutOfBoundsException("Not allowed index " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size() + 1) {
            throw new ArrayListIndexOutOfBoundsException("Not allowed index " + index);
        }
    }

    private void expand() {
        if (size >= lengthOfArrayList) {
            arrayList = Arrays.copyOf(arrayList, (int) (lengthOfArrayList
                    * COEFFICIENT_OF_EXPANSION));
            lengthOfArrayList = (int) (lengthOfArrayList * COEFFICIENT_OF_EXPANSION);
        }
    }
}