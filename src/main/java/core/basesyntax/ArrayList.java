package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private int newCapacity = 10;
    private int capacity = 10;
    private int actualSize = 0;
    private Object[] value = new Object[capacity];
    private Object[] newValue;

    public ArrayList() {

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
        if (actualSize == capacity) {
            extendArray();
        }
        this.value[actualSize] = value;
        actualSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > capacity - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (actualSize == capacity) {
            extendArray();
        }
        for (int i = capacity; i >= index; i--) {
            this.value[i] = this.value[i - 1];
        }
        this.value[index] = value;
        actualSize++;
    }

    @Override
    public void addAll(List<T> list) {
        while (actualSize + list.size() > capacity) {
            extendArray();
        }
        System.arraycopy(list, 0, this.value, capacity, capacity);
        actualSize += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > actualSize) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.value[index];
    }

    @Override
    public void set(T value, int index) {

        if (index < 0 || index > actualSize) {
            throw new IndexOutOfBoundsException();
        }
        this.value[index] = value;
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index > actualSize) {
            throw new IndexOutOfBoundsException();
        }
        Object[] removedValue = new Object[1];
        removedValue[0] = this.value[index];

        for (int i = index; i < actualSize; i++) {
            this.value[i] = this.value[i + 1];
        }
        this.value[actualSize - 1] = null;
        actualSize--;

        return (T) removedValue[0];
    }

    @Override
    public T remove(T t) {

        return null;
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize > 0;
    }
}
