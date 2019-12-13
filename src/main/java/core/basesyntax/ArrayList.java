package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private int newCapacity;
    private int capacity = 10;
    private int size;

    private Object[] value;
    private Object[] newValue;

    public <T> ArrayList() {
        this.value = new Object[capacity];
    }

    private void extendArray() {

        newCapacity = capacity << 1;
        newValue = new Object[newCapacity];
        System.arraycopy(this.value, 0, newValue, 0, capacity);
        this.value = newValue;
        capacity = newCapacity;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            extendArray();
        }
        this.value[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == capacity) {
            extendArray();
        }
        System.arraycopy(this.value, index, this.value, index + 1, size - index);
        this.value[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) throws Exception {

        while (size + list.size() > capacity) {
            extendArray();
        }
        Object[] copyListToArray = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            copyListToArray[i] = list.get(i);
        }

        System.arraycopy(copyListToArray, 0, this.value, size, copyListToArray.length);
        size += list.size();

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.value[index];
    }

    @Override
    public void set(T value, int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.value[index] = value;
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object[] removedValue = new Object[1];
        removedValue[0] = this.value[index];

        System.arraycopy(this.value, index + 1, this.value, index, size - index - 1);
        size--;

        return (T) removedValue[0];
    }

    @Override
    public T remove(T t) {

        for (int i = 0; i < size; i++) {
            if (t == null || this.value[i].equals(t)) {
                for (int j = i; j < size - 1; j++) {
                    this.value[j] = this.value[j + 1];
                }
                this.value[size - 1] = null;
                size--;
                return t;
            }
        }
        throw new NoSuchElementException();
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
