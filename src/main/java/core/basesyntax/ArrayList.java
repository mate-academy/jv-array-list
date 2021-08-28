package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int STANDARD_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[STANDARD_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (hasArraySpace()) {
            arrayList[size] = value;
            size++;
            return;
        }
        extendCapacity();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!hasArraySpace()) {
            extendCapacity();
        }
        if (index < 0 || index > arrayList.length || index > size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Your index does not suit rules");
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            T[] addArray = (T[]) new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                addArray[i] = list.get(i);
            }
            while (arrayList.length < size + addArray.length) {
                extendCapacity();
            }
            System.arraycopy(addArray, 0, arrayList, size, addArray.length);
            size = size + addArray.length;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Adding list is null");
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("An element you"
                    + " want to get does not exist");
        }
        if (size != 0) {
            return arrayList[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("An element you"
                    + " want to remove does not exist");
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("An element you"
                    + " want to remove does not exist");
        }
        final T removedElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        arrayList[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, arrayList[i])) {
                System.arraycopy(arrayList, i + 1, arrayList, i, size - i - 1);
                arrayList[size] = null;
                size--;
                return element;
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

    private void extendCapacity() {
        int tempLength = arrayList.length;
        T[] tempArray = (T[]) new Object[tempLength];
        tempArray = arrayList;
        arrayList = (T[]) new Object[(tempLength * 3) / 2 + 1];
        System.arraycopy(tempArray, 0, arrayList, 0, size);
    }

    private boolean hasArraySpace() {
        return size < arrayList.length;
    }
}
