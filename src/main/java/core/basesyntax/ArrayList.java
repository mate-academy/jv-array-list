package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            indexCheck(index);
            if (size >= elementData.length) {
                elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2);
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= elementData.length) {
            elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2);
        }
        int i;
        for (i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T temp = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return temp;
    }

    @Override
    public T remove(T t) throws NoSuchElementException {
        int i = 0;
        for (; i < size; i++) {
            if (Objects.equals(elementData[i], t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element does not exist!!!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index input!!!");
        }
    }
}
