package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int init_capacity = 8;
    private Object[] data;
    private int size;

    public <T> ArrayList() {
        data = new Object[init_capacity];
        size = 0;
    }

    private void resize() {
        Object[] newData = new Object[size * 3 / 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == data.length) {
            resize();
        }
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = value;
    }
    
    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T deleted = get(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return deleted;
    }
    
    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
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
