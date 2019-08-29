package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private Object[] storage;
    private static final int defaultCapacity = 16;
    private int size = 0;

    public ArrayList() {
        storage = new Object[defaultCapacity];
    }

    ArrayList(int capacity) {
        storage = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == storage.length - 1) {
            resize();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexException(index);
        resize();
        System.arraycopy(this.storage, index, this.storage,
                    index + 1, this.size - index);
        storage[index] = value;
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
        checkIndexException(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexException(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexException(index);
        final T result = (T) storage[index];
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(storage[i])) {
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

    private void checkIndexException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void resize() {
        Object[] extended = new Object[storage.length * 2];
        System.arraycopy(storage, 0, extended, 0, size);
        storage = extended;
    }

}
