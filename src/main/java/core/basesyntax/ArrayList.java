package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int position;
    private Object[] elementData;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        position = 0;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();

        elementData[position++] = value;

    }

    @Override
    public void add(T value, int index) {
        check(index);
        grow();
        if (index < position) {
            System.arraycopy(elementData, index, elementData, index + 1, position - index);
        }
        this.elementData[index] = value;
        position++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        check(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        check(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        check(index);
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, position - index);
        position--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < position; i++) {
            if (t == elementData[i] || (t != null && t.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such value!");
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    private void check(int index) {
        if (index >= position || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index + " out Of Bounds");
        }
    }

    private void grow() {
        if ((position + 1) == capacity) {
            T[] clone = (T[]) Arrays.copyOf(elementData, capacity);
            capacity = 3 * capacity / 2 + 1;
            elementData = Arrays.copyOf(clone, capacity);
        }
    }
}
