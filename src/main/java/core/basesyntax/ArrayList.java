package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private  int capacity = DEFAULT_SIZE;
    private int size;
    private T[] arrayData = (T[]) new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {
        if (size == capacity) {
            this.grow();
        }
        arrayData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size");
        }
        if (size == capacity) {
            this.grow();
        }
        for (int i = size; i >= index; i--) {
            arrayData[i+1] = arrayData[i];
            if (i == index) {
                arrayData[index] = value;
            }
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size");
        }
        return arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size");
        }
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size");
        }
        T removeElement = arrayData[index];
        for (int i = index; i < size ; i++) {
            if(i < size - 1) {
                arrayData[i] = arrayData[i+1];
            }
        }
        arrayData[size - 1] = null;
        size--;

        return removeElement;
    }

    @Override
    public T remove(T element) {
        int index = this.getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("Not found element");
        }

        for (int i = index; i < size; i++) {
            arrayData[i] = arrayData[i + 1];
        }
        arrayData[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    private void grow() {
        capacity += capacity / 2;
        T[] newArrayData = (T[]) new Object[capacity];
        for(int i = 0; i < arrayData.length; i++) {
            newArrayData[i] = arrayData[i];
        }
        arrayData = newArrayData;
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayData[i] == element || arrayData[i] != null && arrayData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
