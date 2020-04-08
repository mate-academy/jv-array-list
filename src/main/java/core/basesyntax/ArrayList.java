package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int capacity;

    public ArrayList() {
        capacity = 0;
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        data[capacity] = value;
        capacity++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > capacity) {
            throw new ArrayIndexOutOfBoundsException(index + " Index is out of bounds");
        }
        System.arraycopy(data, index, data,index + 1,capacity - index);
        data[index] = value;
        capacity++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkAvailable(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkAvailable(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkAvailable(index);
        T result = data[index];
        System.arraycopy(data, index + 1, data, index, capacity - index - 1);
        capacity--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < capacity; i++) {
            if (t == data[i]
                    || t != null && t.equals(data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity == 0;
    }

    private void checkAvailable(int index) {
        if (index < 0 || index >= capacity) {
            throw new ArrayIndexOutOfBoundsException(index + " Index is out of bounds");
        }
    }

    private void increaseCapacity() {
        if (capacity == data.length) {
            T[] newArr = (T[]) new Object[(data.length * 3 / 2)];
            System.arraycopy(data, 0, newArr, 0, capacity);
            data = newArr;
        }
    }
}
