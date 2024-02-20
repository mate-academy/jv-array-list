package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private int iterator;
    T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
        size = array.length;
        iterator = 0;
    }
    @Override
    public void add(T value) {
        array[iterator] = value;
        iterator++;
        size++;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        array[index] = null;
        return array[index];
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidation(int index) {
        if (index <= 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
    }
}
