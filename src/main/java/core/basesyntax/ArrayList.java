package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_LENGTH = 10;
    private static final int INITIAL_ARRAY_SIZE = 0;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = new Object[INITIAL_ARRAY_LENGTH];
        size = INITIAL_ARRAY_SIZE;
    }

    @Override
    public void add(T value) {
        sizeUp();
        arrayList[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        sizeUp();
        checkIndex(index);
        arrayShift(index, +1);
        arrayList[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            sizeUp();
            arrayList[size - 1] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        if (index + 1 <= size) {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T returnValue = (T) arrayList[index];
        arrayList[index] = null;
        arrayShift(index, -1);
        sizeDown();
        return returnValue;
    }


    @Override
    public T remove(T element) {
        T returnValue = null;
        for (int i = 0; i < size; i++) {
            if (element.equals((T) arrayList[i])) {
                returnValue = (T) arrayList[i];
                if (size > 1) arrayShift(i, -1);
                size--;
            }
        }
        if (returnValue != null) {
            return returnValue;
        } else {
            throw new NoSuchElementException("Removing element wasn't found");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        Object[] newArrayList = new Object[(int) (arrayList.length * 1.5)];
        System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
        arrayList = newArrayList;
    }

    private void arrayShift(int index, int value) {
        Object[] newArrayList = new Object[arrayList.length];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        if (index + value >= 0) System.arraycopy(arrayList, index, newArrayList, index + value, size - index);
        arrayList = newArrayList;
    }

    private void sizeUp() {
        sizeUpFor(1);
    }

    private void sizeDown(){
        sizeUpFor(-1);
    }

    private void sizeUpFor(int quantity) {
        size += quantity;
        if (size == arrayList.length) resize();
    }


    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
    }
}
