package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private Object[] elementData = new Object[SIZE];
    private Object[] bufferedData = new Object[SIZE];
    private static int s;
    private static int SIZE = 10;

    public ArrayList() {
        s = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
    public String buffer() {
        return Arrays.toString(bufferedData);
    }
    private void grow() {
        if (s == elementData.length) {
            SIZE *= 1.5;
            bufferedData = new Object[SIZE];
            System.arraycopy(elementData, 0, bufferedData, 0, elementData.length);
            elementData = new Object[SIZE];
            System.arraycopy(bufferedData, 0, elementData, 0, elementData.length);
            bufferedData = new Object[SIZE];
        }
    }

    @Override
    public void add(T value) {
        grow();
        elementData[s] = value;
        s++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        System.arraycopy(elementData, index, bufferedData, 0, elementData.length - index);
        elementData[index] = value;
        System.arraycopy(bufferedData, 0, elementData, index + 1, elementData.length - index - 1);
        s++;
        bufferedData = new Object[SIZE];
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - index - 1);
        s--;
        return null;
    }

    @Override
    public T remove(T element) {
        remove(Arrays.asList(elementData).indexOf(element));
        return null;
    }

    @Override
    public int size() {
        return s;
    }

    @Override
    public boolean isEmpty() {
        return s == 0;
    }
}
