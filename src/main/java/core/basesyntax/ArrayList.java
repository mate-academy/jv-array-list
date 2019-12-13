package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;
    private Object[] valueList;
    private int sizeCounter;

    public ArrayList() {
        valueList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        valueList[sizeCounter] = value;
        sizeCounter++;
    }

    @Override
    public void add(T value, int index) {
        checkCapacity();
        checkIndex(index);
        System.arraycopy(valueList, index, valueList, index + 1, sizeCounter - index);
        valueList[index] = value;
        sizeCounter++;
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
        return (T) valueList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        valueList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object oldObj = valueList[index];
        System.arraycopy(valueList, index + 1, valueList, index, sizeCounter - index - 1);
        valueList[sizeCounter] = null;
        sizeCounter--;
        return (T) oldObj;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < sizeCounter; i++) {
            if (t == valueList[i] || t != null && t.equals(valueList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return sizeCounter;
    }

    @Override
    public boolean isEmpty() {
        return sizeCounter == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= sizeCounter) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkCapacity() {
        if (sizeCounter >= valueList.length) {
            Object[] temp = valueList;
            valueList = new Object[sizeCounter * 3 / 2 + 1];
            System.arraycopy(temp, 0, valueList, 0, temp.length);
        }
    }
}
