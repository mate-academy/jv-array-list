package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;
    private int lastPutIndex = 0;

    public ArrayList(int capacity) {
        data = new Object[capacity];
    }

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (data.length == size) {
            // скопировать старый, и добавить в новый. уведиченный размер на 1.5
            changeCountValues();
        } else {
            add(value, lastPutIndex);
            changeCountValues();
        }

    }

    @Override
    public void add(T value, int index) {
        if (data.length == size) {
            // скопировать старый, и добавить в новый. уведиченный размер на 1.5
            changeCountValues();
        } else {
            data[index] = value;
            changeCountValues();
        }

    }

    @Override
    public void addAll(List<T> list) {
        if (data.length - list.size() < 0) {
            //скопировать старый, и добавить в новый. уведиченный размер на 1.5
            changeCountValues();
        } else {
//скопировать с 1 во второй
            changeCountValues();
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException(String.format("Index %d out of ArrayList.", index));
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException(String.format("Index %d out of ArrayList.", index));
        }
        data[index] = value;
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
        return size <= 0;
    }

    private void changeCountValues() {
        size = data.length;
        lastPutIndex++;
    }
}
