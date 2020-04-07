package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int size = 0;
    private T [] arrayList;

    public ArrayList() {
        arrayList = (T[]) new Object[CAPACITY];
    }

    public ArrayList(int size) {
        ensureCapacity(size);
    }

    public void ensureCapacity(int size) {
        if (size >= CAPACITY) {
            T[] oldArrayList = (T[]) new Object[this.size];
            oldArrayList = arrayList;
            arrayList = (T[]) new Object[size];
            System.arraycopy(oldArrayList,0,arrayList,0,oldArrayList.length);
        }
    }

    @Override
    public void add(T value) {
        add(value, size());
    }

    @Override
    public void add(T value, int index) {
        assertIndexForAdd(index);
        if (arrayList.length == size) {
            ensureCapacity((size() * 3) / 2 + 1);
        }
        System.arraycopy(arrayList,index,arrayList,index + 1,size - index);
        arrayList[index] = value;
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
        assertIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        assertIndex(index);
        arrayList[index] = value;
        return;
    }

    @Override
    public T remove(int index) {
        assertIndex(index);
        T removeValue = arrayList[index];
        System.arraycopy(arrayList,index + 1,arrayList,index,size - index);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if (t == arrayList[i] || (t != null && t.equals(arrayList[i]))) {
                return remove(i);
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
        return (size() == 0);
    }

    private void assertIndexForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void assertIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}


