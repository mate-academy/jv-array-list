package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Элемента с таким индексом нет в списке!");
        }
        checkCapacity();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        isValid(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        isValid(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        isValid(index);
        T element = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, values[i])) {
                remove(i);
                return t;
            }
        }
        throw new NoSuchElementException("Такого элемента нет в списке!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Элемента с таким индексом нет в списке!");
        }
    }

    private void checkCapacity() {
        if (size == values.length) {
            T[] extendedValues = (T[]) new Object[(int) Math.ceil(values.length * 1.5)];
            System.arraycopy(values, 0, extendedValues, 0, size);
            values = extendedValues;
        }
    }
}
