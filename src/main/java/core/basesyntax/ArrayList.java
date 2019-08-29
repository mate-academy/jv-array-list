package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity;
    private int size;
    private Object[] data;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    public ArrayList(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        this.data = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public void add(T value) {
        resize();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Given index is bigger than size.");
        }
        resize();
        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = value;
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
            throw new ArrayIndexOutOfBoundsException("Index is bigger than size.");
        }
        T returnValue = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        this.size--;
        return returnValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(data[i])) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void resize() {
        if (size > capacity * LOAD_FACTOR) {
            Object[] newData = new Object[capacity * 3 / 2];
            System.arraycopy(this.data, 0, newData, 0, this.size);
            this.data = newData;
            this.capacity = capacity * 3 / 2;
        }
    }
}
