package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resizeIfNeed();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        throwExceptionIfIndexOutOfBounds(index);
        resizeIfNeed();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        throwExceptionIfIndexOutOfBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        throwExceptionIfIndexOutOfBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        throwExceptionIfIndexOutOfBounds(index);
        T t = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return t;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], t)) {
                return remove(i);
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

    private void throwExceptionIfIndexOutOfBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void resizeIfNeed() {
        if (array.length == size) {
            int oldCapacity = array.length;
            Object[] temp = array;
            array = new Object[oldCapacity + (oldCapacity >> 1)];
            System.arraycopy(temp, 0, array, 0, oldCapacity);
        }
    }
}
