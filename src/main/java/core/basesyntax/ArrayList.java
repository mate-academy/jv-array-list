package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elementDate;
    private int size;

    public ArrayList() {
        elementDate = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementDate.length) {
            grow();
        }
        elementDate[size] = value;
        size++;
    }

    private void grow() {
        int newArrayCapacity = elementDate.length + elementDate.length / 2;
        T[] newArray = (T[]) new Object[newArrayCapacity];
        System.arraycopy(elementDate, 0, newArray, 0, size);
        elementDate = newArray;
    }

    @Override
    public void add(T value, int index) {
        this.elementDate[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return this.elementDate[index];
    }

    @Override
    public void set(T value, int index) {
        this.elementDate[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", is invalid");
        }

        T removedValue = this.elementDate[index];

        for (int i = index; i < size - 1; i++) {
            this.elementDate[i] = this.elementDate[i + 1];
        }
        size--;
        elementDate[size] = null;

        return removedValue;
    }

    @Override
    public T remove(T element) {;
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
