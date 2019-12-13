package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] elementData;

    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == elementData.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
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
        if (index > size - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementData[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void resize() {
        T[] newArray = (T[]) new Object[elementData.length * 3 / 2];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index > size || elementData[index] == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = (T) elementData[index];
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return temp;
    }

    @Override
    public T remove(T t) {
        Boolean exist = elementExists(t);
        if (!exist) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(t) || elementData[i] == t || t == null) {
                remove(i);
                return t;
            }
        }
        return t;
    }

    private Boolean elementExists(T t) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null)
                    || (elementData[i].equals(t) || elementData[i] == t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
