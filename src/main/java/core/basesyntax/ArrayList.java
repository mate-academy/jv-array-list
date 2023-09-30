package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Capacity can't be less than 0");
        }
        this.arrayList = (T[]) new Object [capacity];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            arrayList = letItGrow();

        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index should be in the range 0 to "
                    + size);
        }
        if (size + 1 > arrayList.length) {
            arrayList = letItGrow();
        }
        if (size != 0) {
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);

        } else {
            System.arraycopy(arrayList, index, arrayList, index + 1, size);
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (arrayList.length <= size + list.size()) {
            arrayList = letItGrow();
        }
        for (int i = 0; i < list.size(); i++) {
            arrayList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size() - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index should be in the range 0 to "
                    + size);
        }
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size() - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index should be in the range 0 to "
                    + size);
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index should be in the range 0 to "
                    + size);
        }
        T removedObject = arrayList[index];
        if (index == size - 1) {
            System.arraycopy(arrayList, index, arrayList, index, 1);
        } else {
            System.arraycopy(arrayList, index + 1, arrayList, index, size - 1);
        }
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        boolean isPresent = false;
        if (element == null) {
            for (int i = 0; i < arrayList.length; i++) {
                if (element == arrayList[i]) {
                    System.arraycopy(arrayList, i + 1, arrayList, i, size - 1);
                    size--;
                    isPresent = true;
                    break;
                }
            }
        }
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null && arrayList[i].equals(element)) {
                System.arraycopy(arrayList, i + 1, arrayList, i, size - 1);
                size--;
                isPresent = true;
                break;
            }
        }
        if (isPresent == false) {
            throw new NoSuchElementException("Element not found");

        }
        return isPresent == true ? element : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] letItGrow() {
        return arrayList = Arrays.copyOf(arrayList, (int)Math. round(arrayList.length * 1.5));
    }
}
