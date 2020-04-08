package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int capacity;

    public ArrayList() {
        capacity = 0;
        massive = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        maxCapacity();
        massive[capacity] = value;
        capacity++;
    }

    @Override
    public void add(T value, int index) {
        checkAvailable(index);
        maxCapacity();
        System.arraycopy(massive, index, massive,index + 1,capacity - index);
        massive[index] = value;
        capacity++;
    }

    @Override
    public void addAll(List<T> list) {
        int zero = 0;
        while (zero < list.size()) {
            T test = list.get(zero);
            add(test);
            zero++;
        }
    }

    @Override
    public T get(int index) {
        if (index < capacity && index >= 0) {
            return (T) massive[index];
        }
        throw new ArrayIndexOutOfBoundsException("The index exceeds the array length!");
    }

    @Override
    public void set(T value, int index) {
        checkAvailable(index);
        massive[index] = value;
    }

    @Override
    public T remove(int index) {
        checkAvailable(index);
        T result = massive[index];
        System.arraycopy(massive, index + 1, massive, index, capacity - index - 1);
        capacity--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < capacity; i++) {
            if (t == massive[i]
                    || t != null && t.equals(massive[i])) {
                return remove(i);
            }
        }
        throw new java.util.NoSuchElementException("No such element");
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
        if (capacity == massive.length) {
            T[] newArr = (T[]) new Object[massive.length * 3 / 2];
            System.arraycopy(massive, 0, newArr, 0, capacity);
            massive = newArr;
        }
    }
}
