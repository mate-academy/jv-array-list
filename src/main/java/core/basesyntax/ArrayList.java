package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CARPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CARPACITY];

    }

    @Override
    public void add(T value) {
        sizeChecking();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        sizeChecking();
        if (index == size) {
            add(value);
            return;
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        size++;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexChecking(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecking(index);
        T[] tempArray = (T[]) elementData;
        final T removedValue = tempArray[index];
        System.arraycopy(tempArray, 0, elementData, 0, index);
        System.arraycopy(tempArray, index + 1, elementData, index, tempArray.length - 1 - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(t, elementData[i])) {
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

    private int increaseCarpasity(T[] array, int size) {
        int oldCrpacity = elementData.length;
        int minCarpacity = 0;
        int newCarpacity = 0;
        if (size <= 1) {
            return minCarpacity = size + 1;
        }
        return newCarpacity = (int) (array.length * 1.5);
    }

    private boolean indexChecking(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    private void sizeChecking() {
        if (size == elementData.length) {
            int newCarpacity = increaseCarpasity((T[]) elementData, size);
            T[] tempArray = (T[]) new Object[newCarpacity];
            System.arraycopy((T[]) elementData, 0, tempArray, 0, size);
            elementData = tempArray;
        }
    }

}
