package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private Object[] values;
    private int size = 0;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        values = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resizeGreater();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexException(index);
        resizeGreater();
        System.arraycopy(this.values, index, this.values, index + 1,
                this.size - index);
        this.values[index] = value;
        this.size++;
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
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexException(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexException(index);
        final T t = (T) values[index];
        // Shift data to the left
        for (int j = index; j < size - 1; j++) {
            values[j] = values[j + 1];
        }
        values[size - 1] = null; // This element is now null
        // Decrement size
        size--;
        return t;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(values[i])) {
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
        return this.size == 0;
    }

    private void checkIndexException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void resizeGreater() {
        Object[] newValues = new Object[values.length * 2];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }
}
