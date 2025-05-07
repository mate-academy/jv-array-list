package core.basesyntax;

import core.basesyntax.exceptions.ArrayListIndexOutOfBoundsException;
import core.basesyntax.interfaces.List;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double SIZE_INCREASE_FACTOR = 1.5;
    private int size;
    private T[] arrayList;

    public ArrayList() {
        this.arrayList = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " out of range");
        }
        growIfArrayFull();
        for (int i = size - 1; i >= index; i--) {
            arrayList[i + 1] = arrayList[i];
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T deleteValue = get(index);
        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;
        return deleteValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i), element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no object with this value");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == arrayList.length) {
            arrayList = Arrays.copyOf(arrayList, (int) (arrayList.length * SIZE_INCREASE_FACTOR));
        }
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " out of range");
        }
    }

}
