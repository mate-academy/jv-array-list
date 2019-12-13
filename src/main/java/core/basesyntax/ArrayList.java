package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int counterSize = 0;
    private Object[] list;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        list[counterSize] = value;
        counterSize++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(list,index, list,index + 1,counterSize - index);
        list[index] = value;
        counterSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removed = list[index];
        System.arraycopy(list, index + 1, list,index,counterSize - index - 1);
        counterSize--;
        return (T) removed;
    }

    @Override
    public T remove(T t) {
        T removed = t;
        for (int i = 0; i < counterSize; i++) {
            if (t == null || t.equals(list[i])) {
                remove(i);
                return removed;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return counterSize;
    }

    @Override
    public boolean isEmpty() {
        return counterSize == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= counterSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void ensureCapacity() {
        if ((list.length - counterSize) < 1) {
            Object[] interimList = list;
            list = new Object[counterSize * 3 / 2 + 1];
            System.arraycopy(interimList,0, list,0,interimList.length);
        }
    }
}
