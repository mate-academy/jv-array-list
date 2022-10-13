package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SCALE_FACTOR = 1.5;
    private static final int FIRST_INDEX = 0;
    private int size;
    private Object[] arrList;

    public ArrayList() {
        arrList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        arrList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + " out of size " + size);
        }
        resize();
        System.arraycopy(arrList, index, arrList, index + 1, size - index);
        arrList[index] = value;
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
        indexCheck(index);
        return (T)arrList[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        arrList[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T value = (T) arrList[index];
        size--;
        System.arraycopy(arrList, index + 1, arrList, index, size - index);

        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrList[i] || element != null && element.equals(arrList[i])) {
                T value = (T) arrList[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("This element does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] tempArrList = (T[]) arrList;
        arrList = new Object[(int) (size * SCALE_FACTOR)];
        System.arraycopy(tempArrList, FIRST_INDEX, arrList, FIRST_INDEX, size);
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + " out of size " + size);
        }
    }

    private void resize() {
        if (arrList.length == size) {
            grow();
        }
    }
}
