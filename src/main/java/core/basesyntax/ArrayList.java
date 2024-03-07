package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_RATE = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        setGrowRate();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
        setGrowRate();
        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            setGrowRate();
            arrayList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        isExist(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        isExist(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        isExist(index);
        for (int i = index; i < size; i++) {
            if (i == arrayList.length - 1) {
                break;
            }
            arrayList[i] = arrayList[i + 1];
        }
        T temp = arrayList[index];
        arrayList[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size - 1; i++) {
            if (Objects.equals(element, arrayList[i])) {
                index = i;
                remove(index);
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("This value does not exist");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (arrayList == null || size == 0) {
            return true;
        }
        return false;
    }

    public boolean isExist(int index) throws ArrayListIndexOutOfBoundsException {
        if (index != 0
                && index >= size
                || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
        return true;
    }

    public void setGrowRate() {
        if (size == arrayList.length) {
            T[] newArrayList = (T[]) new Object [(int) (arrayList.length * GROW_RATE)];
            System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
            arrayList = newArrayList;
        }
    }
}
