package core.basesyntax;

import java.util.NoSuchElementException;
/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int amountOfElements = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (amountOfElements == array.length) {
            reSize();
        }
        array[amountOfElements] = value;
        amountOfElements++;
    }

    @Override
    public void add(T value, int index) {
        amountOfElements++;
        System.arraycopy(array, index, array, index + 1, amountOfElements - index);
        set(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (amountOfElements <= index || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return (T) array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (amountOfElements <= index || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index > amountOfElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            T elementToRemove = get(index);
            System.arraycopy(array, index + 1, array, index, amountOfElements - index);
            amountOfElements--;
            return elementToRemove;
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < amountOfElements; i++) {
            if (array[i] == null || array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return amountOfElements;
    }

    public void reSize() {
        Object[] newArray = new Object[(array.length * 3) / 2 + 1];
        System.arraycopy(array, 0, newArray, 0, amountOfElements);
        array = (T[]) newArray;
    }

    @Override
    public boolean isEmpty() {
        if (amountOfElements == 0) {
            return true;
        } else {
            return false;
        }
    }
}
