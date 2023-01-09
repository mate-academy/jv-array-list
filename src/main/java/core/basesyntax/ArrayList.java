package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 10;
    private static final double GROW_INDEX = 1.5;
    private int maxSize = DEFAULT_INDEX;
    private int sizeOfArray;
    private T[] arrayDefault;
    private T[] arrayTemporary;

    public ArrayList() {
        arrayDefault = (T[]) new Object[maxSize];
    }

    public void errorCheck(int index, int size) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception: "
                    + index + " for size" + size);
        }
    }

    public void resize() {
        if (sizeOfArray == maxSize) {
            maxSize = (int) (maxSize * GROW_INDEX);
            arrayTemporary = (T[]) new Object[maxSize];
            System.arraycopy(arrayDefault, 0, arrayTemporary, 0, sizeOfArray);
            arrayDefault = arrayTemporary;
        }
    }

    @Override
    public void add(T value) {
        resize();
        for (int i = 0; i < sizeOfArray; i++) {
            if ((arrayDefault[i] != null && arrayDefault[i] == value)
                    || (arrayDefault[i] != null && arrayDefault[i].equals(value))) {
                arrayDefault[i] = value;
                return;
            }
        }
        arrayDefault[sizeOfArray] = value;
        sizeOfArray++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception: "
                    + index + " size " + sizeOfArray);
        }
        resize();
        if (index < sizeOfArray) {
            System.arraycopy(arrayDefault, index, arrayDefault, index + 1, sizeOfArray - index);
            arrayDefault[index] = value;
            sizeOfArray++;
            return;
        }
        arrayDefault[index] = value;
        sizeOfArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T value = list.get(i);
            add(value);
            resize();
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        errorCheck(index, sizeOfArray);
        return arrayDefault[index];
    }

    @Override
    public void set(T value, int index) {
        errorCheck(index, sizeOfArray);
        arrayDefault[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        errorCheck(index, sizeOfArray);
        T result = arrayDefault[index];
        System.arraycopy(arrayDefault, index + 1, arrayDefault, index, sizeOfArray - index - 1);
        sizeOfArray--;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        boolean resultElement = false;
        for (int i = 0; i < sizeOfArray; i++) {
            if ((element != null && element.equals(arrayDefault[i]))
                    || element == arrayDefault[i]) {
                remove(i);
                resultElement = true;
                return element;
            }
        }
        if (resultElement != true) {
            throw new NoSuchElementException("No such index exception: " + element + " is absent");
        }
        return element;
    }

    @Override
    public int size() {
        return sizeOfArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeOfArray == 0;
    }
}
