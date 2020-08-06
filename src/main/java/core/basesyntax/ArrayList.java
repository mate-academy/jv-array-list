package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
        resize();
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
        T removeIndex = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removeIndex;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null ? t == array[i] : t.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The input element don't exist here!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds!");
        }
    }

    private void resize() {
        if (size == array.length) {
            array = Arrays.copyOf(array, (array.length + (array.length >> 1)));
        }
    }

    private void checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("The index is bigger than array length!");
        }
    }
}
