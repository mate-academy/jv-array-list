package core.basesyntax;


public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 16;
    private int size;
    private T[] array = (T[]) new Object[CAPACITY];

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size > array.length - 1) {
            increaseSize();
        }
        if (index < size) {
            for (int i = size + 1; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        } else {
            array[size++] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final T temp = array[index];
        T[] resizedArray = array;
        for (int i = index; i < size - 1; i++) {
            resizedArray[i] = resizedArray[i + 1];
        }
        size--;
        System.arraycopy(array, 0, array = resizedArray, 0, size);
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseSize() {
        int newCapacity = (array.length * 3 / 2) + 1;
        System.arraycopy(array, 0, array = (T[]) new Object[newCapacity], 0, size);
    }
}
