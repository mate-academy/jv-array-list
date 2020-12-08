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
    public T remove(T t) {
        return null;
    }

    @Override
    public int size() {
        return CURRENT_SIZE;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
