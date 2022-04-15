package core.basesyntax;

import java.sql.Struct;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] tempArray;
    private int size;

    public ArrayList() {
        tempArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == tempArray.length) {
            grow();
        }
        tempArray[size] = value;
        size++;
    }

    private void grow () {
        int newLength = (int) (tempArray.length * 1.5);
        T[] newTempArray = (T[]) new Object[newLength];
        System.arraycopy(tempArray,0, newTempArray, 0, size);
        tempArray = newTempArray;
    }

    // checked
    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index. Index should be: 0 <= index <= " + size);
        }
        if (size == tempArray.length) {
            grow();
        }
        System.arraycopy(tempArray, index, tempArray, (index + 1), (size - index));
        tempArray[index] = value;
        size++;
    }

    // TODO
    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        IndexCheck(index);
        return tempArray[index];
    }

    // TODO check
    @Override
    public void set(T value, int index) {
        IndexCheck(index);
        tempArray[index] = value;
        size++;
    }

    // TODO
    @Override
    public T remove(int index) {
        IndexCheck(index);

        return null;
    }

    private void IndexCheck(int index) {
        if (index >= 0 && index < size) {
            return;
        } throw new ArrayListIndexOutOfBoundsException("Wrong index. Index should be: 0 <= index < " + size);
    }

    // TODO
    @Override
    public T remove(T element) {
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

    @Override
    public String toString() {
        return "ArrayList{" +
                "tempArray=" + Arrays.toString(tempArray) +
                '}';
    }
}
