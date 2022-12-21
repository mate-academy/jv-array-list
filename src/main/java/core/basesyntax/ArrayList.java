package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private final int INIT_SIZE = 10;
    private final double RESIZE_BOUND = 1.5;
    private int size;
    private Object[] array = new Object[INIT_SIZE];
    private void resize(int newLength) {
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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

