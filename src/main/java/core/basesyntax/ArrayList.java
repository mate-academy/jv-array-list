package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] array;
    private int count = 0;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        arraySize();
        array[count++] = value;
    }

    @Override
    public void add(T value, int index) {
        verification(index);
        arraySize();
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = value;
        count++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            arraySize();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        verification(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        verification(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        verification(index);
        Object removedObject = array[index];
        System.arraycopy(array, index + 1, array, index, count - index - 1);
        count--;
        return (T) removedObject;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if ((t == array[i]) || (t != null && array[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public void verification(int index) {
        if ((index < 0) || (index >= count)) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void arraySize() {
        if (count >= array.length) {
            array = Arrays.copyOf(array, count * 3 / 2 + 1);
        }
    }
}
