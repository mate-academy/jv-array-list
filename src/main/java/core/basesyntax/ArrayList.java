package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 0;
    private Object[] elementData;
    private int elements;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.elementData = new Object[capacity];
    }

    public void ensureCapacity() {
        Object[] newElementData = Arrays.copyOf(elementData, elementData.length + 1);
        elementData = newElementData;
    }

    public void rangeCheckForAdd(int index) {
        if (index > elementData.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("" + index + "out of bound");
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[elements] = value;
        elements++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        Object[] tempData = new Object[elementData.length + 1];
        System.arraycopy(elementData, 0, tempData, 0, index);
        tempData[index] = value;
        System.arraycopy(elementData, index, tempData, index + 1, (elementData.length - index));
        elementData = tempData;
        elements++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ensureCapacity();
            elementData[elements++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index);
        for (int i = 0; i < elementData.length; i++) {
            if (index == i && elementData[i] != null) {
                return (T) elementData[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        Object[] tempData = new Object[elementData.length - 1];
        System.arraycopy(elementData, 0, tempData, 0, index);
        System.arraycopy(elementData, index + 1, tempData, index, elementData.length - index - 1);
        Object removedObject = elementData[index];
        elementData = tempData;
        elements--;
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
        return elements;
    }

    @Override
    public boolean isEmpty() {
        return elements == 0;
    }
}
