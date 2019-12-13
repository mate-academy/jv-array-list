package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private int newCapacity = 10;
    private int capacity = 10;
    private int actualSize = 0;

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
        if (actualSize == capacity) {
            extendArray();
        }
        this.value[actualSize] = value;
        actualSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= actualSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (actualSize == capacity) {
            extendArray();
        }
        for (int i = capacity - 1; i > index; i--) {
            this.value[i] = this.value[i - 1];
        }
        this.value[index] = value;
        actualSize++;
    }

    @Override
    public void addAll(List<T> list) {
        try {
            while (actualSize + list.size() > capacity) {
                extendArray();
            }
            Object[] copyListToArray = new Object[list.size()];

            for (int i = 0; i < list.size(); i++) {
                copyListToArray[i] = list.get(i);
            }

            System.arraycopy(copyListToArray, 0, this.value, actualSize, copyListToArray.length);
            actualSize += list.size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= actualSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.value[index];
    }

    @Override
    public void set(T value, int index) {

        if (index < 0 || index >= actualSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.value[index] = value;
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index >= actualSize) {
            throw new ArrayIndexOutOfBoundsException();
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

        for (int i = 0; i < actualSize; i++) {
            if (t == null || this.value[i].equals(t)) {
                for (int j = i; j < actualSize - 1; j++) {
                    this.value[j] = this.value[j + 1];
                }
                this.value[actualSize - 1] = null;
                actualSize--;
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }
}
