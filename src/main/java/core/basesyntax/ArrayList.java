package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private Object[] data;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(Object[] data, int size) {
        this.data = data;
        this.size = size;
    }

    private void resize() {
        if (size == data.length) {
            Object[] dataTwo = new Object[data.length + (data.length >> 1)];
            System.arraycopy(data, 0, dataTwo, 0, size);
            data = dataTwo;
        }
    }

    @Override
    public void add(T value) {
        resize();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        resize();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - 1);
        data[size--] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < data.length; i++) {
            if (t != null && data[i] == t) {
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
}
