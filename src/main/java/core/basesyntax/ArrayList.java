package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int SIZE = 10;
    private T[] elementData;
    private int lastIndex;

    public ArrayList() {
        elementData = (T[]) new Object[SIZE];
        lastIndex = 0;
    }

    @Override
    public void add(T value) {
        if (lastIndex >= elementData.length) {
            ensureCapacity();
        }
        elementData[lastIndex] = value;
        lastIndex++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        System.arraycopy(elementData, index, elementData, index + 1, lastIndex - index);
        elementData[index] = value;
        lastIndex++;
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
        for (int i = 0; i < lastIndex; i++) {
            if (i == index) {
                return elementData[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        for (int i = 0; i < lastIndex; i++) {
            if (i == index) {
                elementData[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        T item = null;
        if (checkIndex(index)) {
            item = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, lastIndex - index);
            lastIndex--;
        }
        return item;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < lastIndex; i++) {
            if (elementData[i] == t || elementData[i] != null && elementData[i].equals(t)) {
                System.arraycopy(elementData, i + 1, elementData, i, lastIndex - i);
                lastIndex--;
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    private boolean checkIndex(int index) {
        if (index >= lastIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    private T[] ensureCapacity() {
        T[] array = elementData;
        elementData = (T[]) new Object[(elementData.length * 3) / 2 + 1];
        System.arraycopy(array, 0, elementData, 0, lastIndex);
        return array;
    }
}
