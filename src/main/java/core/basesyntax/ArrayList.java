package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void grow(T[] oldArray) {
        int newCapacity = reSize(size);
        T[] growArray = (T[]) new Object[newCapacity];
        System.arraycopy(oldArray, 0, growArray, 0, newCapacity);
    }

    private int reSize(int size) {
        int oldCapacity = size;
        return oldCapacity + (oldCapacity >> 1);
    }

    private int getIndex(T element) {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == element) {
                counter = i;
            }
        }
        return counter;
    }

    @Override
    public void add(T value) {
        if (size > array.length) {
            grow(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index " + index + " is not exist!");
        }
        if (index == size) {
            add(value);
        }
        if (size >= array.length) {
            grow(array);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; 2 * size >= array.length; i++) {
            grow(array);
        }
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        System.arraycopy(array, 0, array, size, size);
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index " + index + " is not exist!");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        add(value, index);
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index " + index + " is not exist!");
        }
        System.arraycopy(array, index + 1, array, index, size);
        size--;
        return array[index];
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        if (getIndex(element) >= size) {
            throw new NoSuchElementException("This element " + element + " is not exist!");
        }
        return remove(getIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (array != null) {
            return false;
        }
        return true;
    }
}
