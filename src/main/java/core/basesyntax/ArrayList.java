package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size] = value;
        size += 1;
    }

    private void expandArray() {
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T t) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
