package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private int size;
    private Object[] array = new Object[10];
    private Object[] tempArray = new Object[10];

    @Override
    public void add(T value) {
        if (size >= array.length) {
            tempArray = new Object[array.length];
            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }
            array = new Object[size * 2];
            for (int i = 0; i < tempArray.length; i++) {
                array[i] = tempArray[i];
            }
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        tempArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        if (size >= array.length) {
            array = new Object[size * 2];
            for (int i = 0; i < tempArray.length; i++) {
                array[i] = tempArray[i];
            }
        }
        for (int i = 0; i < tempArray.length; i++) {
            if (i < index) {
                array[i] = tempArray[i];
            }
            if (i == index) {
                array[i] = value;
            }
            if (i > index) {
                array[i] = tempArray[i - 1];
            }
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= array.length) {
            tempArray = new Object[array.length];
            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }
            array = new Object[size * 2];
            for (int i = 0; i < tempArray.length; i++) {
                array[i] = tempArray[i];
            }
        }
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (i < index) {
                array[i] = tempArray[i];
            } else {
                array[i] = tempArray[i + 1];
            }
        }
        size--;
        return (T) tempArray[index];
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        int count = 0;
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
            if (t.equals(array[i])) {
                count++;
            }
        }
        if (count == 0) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        for (int i = 0; i < tempArray.length; i++) {
            if (t.equals(array[i])) {
                index = i;
            }
        }
        for (int i = 0; i < tempArray.length - 1; i++) {
            if (i < index) {
                array[i] = tempArray[i];
            } else {
                array[i] = tempArray[i + 1];
            }
        }
        size--;
        return (T) tempArray[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
