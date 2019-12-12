package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ensureCapacity();
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size] = null;
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == array[i] || t != null && t.equals(array[i])) {
                remove(i);
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

    private void ensureCapacity() {
        Object[] temporary;
        if (size == array.length) {
            temporary = array;
            array = new Object[size * 3 / 2 + 1];
            System.arraycopy(temporary, 0, array, 0, temporary.length);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
