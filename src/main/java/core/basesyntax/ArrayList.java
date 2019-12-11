package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public void ensureCapacity() {
        Object[] newElementData = Arrays.copyOf(elementData, size + 1);
        elementData = newElementData;
    }

    public void rangeCheckForAdd(int index) {
        if (index > elementData.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("" + index + "out of bound");
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        ensureCapacity();
        Object[] tempData = new Object[elementData.length + 1];
        System.arraycopy(elementData, 0, tempData, 0, index);
        tempData[index] = value;
        System.arraycopy(elementData, index, tempData, index + 1, (elementData.length - index));
        elementData = tempData;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ensureCapacity();
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        Object[] tempData = new Object[elementData.length];
        System.arraycopy(elementData, 0, tempData, 0, index);
        System.arraycopy(elementData, index + 1, tempData, index, elementData.length - index - 1);
        Object removedObject = elementData[index];
        elementData = tempData;
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T t) {
        boolean elementExist = false;
        int index = 0;
        for (int i = 0; i < elementData.length; i++) {
            if (t != null && t.equals(elementData[i]) || t == elementData[i]) {
                index = i;
                elementExist = true;
            }
        }
        if (!elementExist) {
            throw new NoSuchElementException("There is no such element");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
