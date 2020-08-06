package core.basesyntax;

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
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(preferredNewCapacity());
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (size == elementData.length) {
                elementData = grow(preferredNewCapacity());
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elementData.length) {
            elementData = grow(size + list.size());
        }
        // К сожалению, тут нет возможности осуществить быстрое копирование
        // с помощью System.arraysCopy так как наш кастомный лист не имеет метода toArray()
        // и нам в любом случае придется перебирать его по одному элементу.
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == t
                    || elementData[i] != null && elementData[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list: " + t);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private Object[] grow(int minCapacity) {
        int newCapacity = Math.max(preferredNewCapacity(), minCapacity);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    private int preferredNewCapacity() {
        int oldCapacity = elementData.length;
        return oldCapacity + (oldCapacity >> 1);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
