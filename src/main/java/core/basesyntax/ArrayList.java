package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private T[] array;
    private int targetIndex = 0;
    private int actualSize = 0;

    public ArrayList() {
        array = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        if (targetIndex > array.length - 1) {
            resize();
        }

        array[targetIndex] = value;
        targetIndex++;
        actualSize++;
    }

    @Override
    public void add(T value, int index) {
        if (actualSize > array.length - 1) {
            resize();
        }

        if (index >= 0 && index <= targetIndex) {
            System.arraycopy(array, index, array, index + 1, array.length - index - 1);
            array[index] = value;
            targetIndex++;
            actualSize++;
            return;
        }

        throw new ArrayListIndexOutOfBoundsException("Error");
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T targetElement = list.get(i);
            add(targetElement);
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index <= targetIndex - 1) {
            return array[index];
        }

        throw new ArrayListIndexOutOfBoundsException("Error");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index <= targetIndex - 1) {
            array[index] = value;
            return;
        }

        throw new ArrayListIndexOutOfBoundsException("Error");
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index <= targetIndex - 1) {
            final T targetElement = array[index];
            actualSize--;
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            array[array.length - 1] = null;
            return targetElement;
        }

        throw new ArrayListIndexOutOfBoundsException("Error");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {

            if (Objects.equals(array[i], element)) {
                final T targetElement = array[i];
                actualSize--;
                System.arraycopy(array, i + 1, array, i, array.length - i - 1);
                array[array.length - 1] = null;
                return targetElement;
            }
        }

        throw new NoSuchElementException("Error");
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize() {
        Object[] biggerArray = new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, biggerArray, 0, array.length);
        array = (T[]) biggerArray;
    }
}
