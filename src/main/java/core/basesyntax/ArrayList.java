package core.basesyntax;

public class ArrayList<T> implements List<T> {

    private static final int CAPACITY = 20;
    private static final int GROWING_INDEX = 2;
    private T[] array;
    private int size;

    public ArrayList() {

        array = (T[]) new Object[CAPACITY];
    }

    public void checkArrayBounds(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayIndexOutOfBoundsException("Not correct index " + index);
        }
    }

    public void grow() {
        int newCapacity = (int) (array.length * GROWING_INDEX);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array,0,newArray,0,array.length);
        array = (T[]) newArray;

    }

    @Override
    public void add(T value) {

        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkArrayBounds(index);
            grow();
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
        checkArrayBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkArrayBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArrayBounds(index);
        T deletedElement = array[index];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        T deletedElement;
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                deletedElement = array[i];
                remove(i);
                return deletedElement;
            }
        }
        throw new RuntimeException("Not exsisting element.");
    }

    @Override
    public int size() {

        return size();
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }
}
