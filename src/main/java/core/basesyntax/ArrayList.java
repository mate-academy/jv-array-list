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

    private boolean hasLackOfSpace(int amount) {
        return array.length <= currentAmount + amount;
    }

    private T[] increaseCapacity(int amount) {
        int biggerLength = Math.max((array.length + (array.length >> 1)), amount);
        T[] biggerArray = (T[]) new Object[biggerLength];
        System.arraycopy(array, 0, biggerArray, 0, currentAmount);
        return biggerArray;
    }

    private int newLength() {
        return array.length + (array.length >> 1);
    }

    @Override
    public void add(T value) {
        if (hasLackOfSpace(1)) {
            array = increaseCapacity(newLength());
        }
        array[currentAmount] = value;
        currentAmount++;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentAmount) {
            throw new ArrayIndexOutOfBoundsException("No such index exists");
        }
        if (hasLackOfSpace(1)) {
            array = increaseCapacity(newLength());
        }
        System.arraycopy(array, index, array, index + 1, currentAmount - index);
        currentAmount++;
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        while (hasLackOfSpace(list.size())) {
            array = increaseCapacity(newLength());
        }
        for (int i = 0; i < list.size(); i++) {
            array[currentAmount + i] = list.get(i);
        }
        currentAmount += list.size();
    }

    @Override
    public T get(int index) {
        if (index < currentAmount) {
            return array[index];
        }
        throw new ArrayIndexOutOfBoundsException("No such index exists");
    }

    @Override
    public void set(T value, int index) {
        if (index >= currentAmount) {
            throw new ArrayIndexOutOfBoundsException("No such index exists");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < currentAmount) {
            T removedObject = array[index];
            currentAmount--;
            System.arraycopy(array, index + 1, array, index, currentAmount);
            return removedObject;
        }
        throw new ArrayIndexOutOfBoundsException("No such index exists");
    }

    @Override
    public T remove(T t) {
        for (int index = 0; index < currentAmount; index++) {
            if (t != null && t.equals(array[index]) || t == array[index]) {
                T removedObject = array[index];
                currentAmount--;
                System.arraycopy(array, index + 1, array, index, currentAmount);
                return removedObject;
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
}
