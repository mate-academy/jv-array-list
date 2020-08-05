package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private int index;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    private void growArray() {
        Object[] newArray = new Object[elements.length + elements.length / 2 + 1];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(T value) {
        if (index == elements.length) {
            growArray();
        }
        elements[index] = value;
        index++;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index == elements.length) {
            growArray();
        }
        System.arraycopy(elements, index, elements, index + 1, this.index - index);
        elements[index] = value;
        this.index++;
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
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    private void removeAction(int index) {
        System.arraycopy(elements, index + 1, elements, index, this.index - index);
        size--;
        this.index--;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = (T) elements[index];
        removeAction(index);
        return result;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            size--;
            this.index--;
            return null;
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (t == elements[i] || t.equals(elements[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        T result = (T) elements[index];
        removeAction(index);
        return result;
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
