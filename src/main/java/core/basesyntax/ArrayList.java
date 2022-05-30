package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private static Object[] elementData;
    private static Object[] bufferedData;
    private static int s = 0;
    private static int SIZE = 10;


    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
    public String buffer() {
        return Arrays.toString(bufferedData);
    }
    private void grow() {
            SIZE *= 1.5;
            bufferedData = new Object[SIZE];
            System.arraycopy(elementData, 0, bufferedData, 0, elementData.length);
            elementData = new Object[SIZE];
            System.arraycopy(bufferedData, 0, elementData, 0, elementData.length);
            bufferedData = new Object[SIZE];
    }

    @Override
    public void add(T value) {
        if (isEmpty()) {
            elementData = new Object[SIZE];
            bufferedData = new Object[SIZE];

        }
        if (s == elementData.length) {
            grow();
        }
        elementData[s] = value;
        s++;
    }

    @Override
    public void add(T value, int index) {
        if (isEmpty()) {
            elementData = new Object[SIZE];
            bufferedData = new Object[SIZE];
        }
        if (s == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, bufferedData, 0, elementData.length - index);
        elementData[index] = value;
        System.arraycopy(bufferedData, 0, elementData, index + 1, elementData.length - index - 1);
        s++;
        bufferedData = new Object[SIZE];
    }

    @Override
    public void addAll(List<T> list) {
        if (isEmpty()) {
            elementData = new Object[SIZE];
            bufferedData = new Object[SIZE];
        }
        for (int i = 0; i < list.size(); i++) {
            if (s == elementData.length) {
                grow();
            }
            if (list.get(i) != null) {
                elementData[s] = list.get(i);
                s++;
            }
        }
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
