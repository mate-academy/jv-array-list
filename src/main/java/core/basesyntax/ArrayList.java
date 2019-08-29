package core.basesyntax;

/**
 * Реалізувати свій ArrayList, який імплементує інтерфейс List.
 */
public class ArrayList<T> implements List<T> {
    private final static int INITIAL_CAPACITY = 10;
    private Object[] data = new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        checkSize();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        checkSize();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
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
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        for (int i = index; i < size; i++) {
            set((T) data[i + 1], i);
        }
        size--;
        return result;
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

    private void checkSize() {
        if (size == data.length) {
            Object[] newData = new Object[size + (size >> 1)];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }
}
