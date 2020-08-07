package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int currentAmount;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity(newLength());
        array[currentAmount] = value;
        currentAmount++;
    }

    @Override
    public void add(T value, int index) {
        if (index != currentAmount) {
            check(index);
        }
        checkCapacity(newLength());
        System.arraycopy(array, index, array, index + 1, currentAmount - index);
        currentAmount++;
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        checkCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            array[currentAmount + i] = list.get(i);
        }
        currentAmount += list.size();
    }

    @Override
    public T get(int index) {
        check(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        check(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        check(index);
        return removeObject(index);
    }

    @Override
    public T remove(T t) {
        for (int index = 0; index < currentAmount; index++) {
            if (t != null && t.equals(array[index]) || t == array[index]) {
                return removeObject(index);
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return currentAmount;
    }

    @Override
    public boolean isEmpty() {
        return currentAmount == 0;
    }

    private void check(int index) {
        if (index >= currentAmount) {
            throw new ArrayIndexOutOfBoundsException("No such index exists");
        }
    }

    private void checkCapacity(int amount) {
        if (array.length <= currentAmount + amount) {
            int biggerLength = Math.max((array.length + (array.length >> 1)), amount);
            T[] biggerArray = (T[]) new Object[biggerLength];
            System.arraycopy(array, 0, biggerArray, 0, currentAmount);
            array = biggerArray;
        }
    }

    private int newLength() {
        return array.length >> 1;
    }

    private T removeObject(int index) {
        T removedObject = array[index];
        currentAmount--;
        System.arraycopy(array, index + 1, array, index, currentAmount);
        return removedObject;
    }
}
