package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int length) {
        array = (T[]) new Object[length];
        size = 0;
    }

    @Override
    public void add(T value) {
        extendArray();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        extendArray();
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
        indexCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T toReturnObject = array[index];
        decreaseArray(index);
        return toReturnObject;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i <= size; i++) {
            if (array[i] == t || array[i] != null && array[i].equals(t)) {
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

    public void extendArray() {
        if (size + 1 >= array.length) {
            T[] oldArray = array;
            array = (T[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(oldArray, 0, array, 0, size);
        }
    }

    public void decreaseArray(int indexToRemove) {
        System.arraycopy(array,
                indexToRemove + 1,
                array,
                indexToRemove,
                size - (indexToRemove + 1));
        array[size] = null;
        size--;
    }

    public void indexCheck(int indexToCheck) {
        if (indexToCheck >= size || indexToCheck < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
