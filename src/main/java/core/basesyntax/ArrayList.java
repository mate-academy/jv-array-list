package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_ARRAY_SIZE = 10;
    public static final double ARROW_INCREASE_COEFFICIENT = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        resize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " does not exist");
        }
        resize();
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
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
        T removeValue = arrayList[index];
        for (int i = index; i < arrayList.length - 1 - index; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < arrayList.length; i++) {
            if (isEqual(arrayList[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Not found " + element + "element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isEqual(T arrayElement, T element) {
        return arrayElement == element
            || ((arrayElement != null && element != null)
            && arrayElement.getClass().equals(element.getClass())
            && arrayElement.equals(element));
    }

    private void resize() {
        if (size == arrayList.length) {
            int newLength = (int) (arrayList.length * ARROW_INCREASE_COEFFICIENT);
            T[] newArray = (T[]) new Object[newLength];
            System.arraycopy(arrayList, 0, newArray, 0, arrayList.length);
            arrayList = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " does not exist!");
        }
    }
}
