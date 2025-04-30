package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayData;
    private Object[] defaultArray = {};
    private int size;

    public ArrayList() {
        this.arrayData = defaultArray;
    }

    private void grow() {
        if (arrayData.length == size) {
            int oldCapacity = arrayData.length;
            int newCapacity = oldCapacity >> 1;
            arrayData = Arrays.copyOf(arrayData, newCapacity);
        }
    }

    private void checkLength(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public void add(T value) {
        grow();
        arrayData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkLength(index);
        grow();
        for (int i = index; i <= size + 1; i++) {
            arrayData[index + 1] = arrayData[index];
        }
        arrayData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkLength(index);
        return (T) arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        checkLength(index);
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkLength(index);
        T removeValue = (T) arrayData[index];
        System.arraycopy(arrayData, index + 1, arrayData, index, index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        T removeElement;
        for (int i = 0; i < size; i++) {
            if (arrayData[i].equals(element)) {
                removeElement = (T) arrayData[i];
                System.arraycopy(arrayData, (i + 1), arrayData, size, size - 1);
            }
        }
        size--;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
