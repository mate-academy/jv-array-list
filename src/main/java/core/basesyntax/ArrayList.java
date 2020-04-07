package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementArr;

    public ArrayList() {
        size = 0;
        elementArr = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elementArr.length) {
            growCapacity();
        }
        elementArr[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (isInArray(index)) {
            System.arraycopy(elementArr, index, elementArr, index + 1, size - index);
            elementArr[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return isInArray(index) ? elementArr[index] : null;
    }

    @Override
    public void set(T value, int index) {
        if (isInArray(index)) {
            System.arraycopy(elementArr, index, elementArr, index + 1, size - index);
            elementArr[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (isInArray(index)) {
            value = elementArr[index];
            removeElement(index);
        }
        return value;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementArr[i] == t
                    || (elementArr[i] != null && elementArr[i].equals(t))) {
                removeElement(i);
                return t;
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

    private void growCapacity() {
        int newCapacity = size + (size >> 1);
        T[] oldArr = elementArr;
        elementArr = (T[]) new Object[newCapacity];
        System.arraycopy(oldArr,0,elementArr,0,size);
    }

    private boolean isInArray(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    private void removeElement(int index) {
        System.arraycopy(elementArr,index + 1, elementArr, index, size - index);
        size--;
    }
}
