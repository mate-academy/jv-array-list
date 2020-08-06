package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final double THRESHOLD = 0.75;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        changeSize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index is not correct");
        }
        changeSize();
        T[] tmpArray = (T[]) new Object[array.length];
        System.arraycopy(array, 0, tmpArray, 0, index);
        tmpArray[index] = value;
        System.arraycopy(array, index, tmpArray, index + 1, size - index);
        array = tmpArray;
        ++size;
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        --size;
        return element;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < this.array.length; i++) {
            if (Objects.equals(array[i], t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index is not correct");
        }
    }

    private void changeSize() {
        if (size + 1 >= array.length * THRESHOLD) {
            int newSize = (int) (array.length * 1.5);
            T[] tmpArray = (T[]) new Object[newSize];
            System.arraycopy(array, 0, tmpArray, 0, size);
            array = tmpArray;
        }
    }
}
