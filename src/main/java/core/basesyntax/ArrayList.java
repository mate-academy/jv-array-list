package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 10;
    private static final double GROW_INDEX = 1.5;
    private int maxSize = DEFAULT_INDEX;
    private int sizeOfArray;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[maxSize];
    }

    @Override
    public void add(T value) {
        resize();
        array[sizeOfArray] = value;
        sizeOfArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception: "
                    + index + " size " + sizeOfArray);
        }
        resize();
        if (index < sizeOfArray) {
            System.arraycopy(array, index, array, index + 1, sizeOfArray - index);
            array[index] = value;
            sizeOfArray++;
            return;
        }
        array[index] = value;
        sizeOfArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T value = list.get(i);
            add(value);
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        errorCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        errorCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        errorCheck(index);
        T result = array[index];
        System.arraycopy(array, index + 1, array, index, sizeOfArray - index - 1);
        sizeOfArray--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeOfArray; i++) {
            if ((element != null && element.equals(array[i]))
                    || element == array[i]) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such index exception: " + element + " is absent");
    }

    @Override
    public int size() {
        return sizeOfArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeOfArray == 0;
    }

    private void resize() {
        if (sizeOfArray == maxSize) {
            maxSize = (int) (maxSize * GROW_INDEX);
            T[] arrayTemporary = (T[]) new Object[maxSize];
            System.arraycopy(array, 0, arrayTemporary, 0, sizeOfArray);
            array = arrayTemporary;
        }
    }

    private void errorCheck(int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception: "
                    + index + " for size" + sizeOfArray);
        }
    }
}
