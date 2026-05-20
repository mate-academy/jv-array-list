package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int InitialCapacity = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[InitialCapacity];
        size = 0;
    }

    @Override
    public void add(T value) {

    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

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
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
