package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            elementData = Arrays.copyOf(elementData, elementData.length + elementData.length / 2);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexExtends(index);
        if (elementData.length == size) {
            elementData = Arrays.copyOf(elementData, elementData.length + elementData.length / 2);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        size++;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size > elementData.length) {
            elementData = Arrays.copyOf(elementData, size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexExtends(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexExtends(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExtends(index);
        T t = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return t;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], t)) {
                remove(i);
                return t;
            }
        }
        throw new NoSuchElementException("NoSuchElementException");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void indexExtends(int i) {
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException");
        }
    }
}
