package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private int counter = 0;
    private int capacity = 10;
    private Object[] arrayList;

    public ArrayList() {

        arrayList = new Object[capacity];
    }

    public void ensureCapacity() {
        if (counter >= arrayList.length) {
            arrayList = Arrays.copyOf(arrayList, arrayList.length * 3 / 2);
        }
    }

    public void checker(int index) {
        if (index < 0 || index >= counter) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean valueExists(T t) {
        for (int i = 0; i < size(); i++) {
            if (arrayList[i] == t || arrayList[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arrayList[counter] = value;
        counter++;
    }

    @Override
    public void add(T value, int index) {
        checker(index);
        ensureCapacity();
        System.arraycopy(arrayList, index, arrayList, index + 1, counter - index);
        arrayList[index] = value;
        counter++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checker(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checker(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checker(index);
        Object toRemove = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, counter - index);
        counter--;
        return (T) toRemove;
    }

    @Override
    public T remove(T t) {
        try {
            if (!valueExists(t)) {
                throw new NoSuchElementException();
            } else {
                for (int i = 0; i < size(); i++) {
                    if (t == arrayList[i] || t.equals(arrayList[i])) {
                        remove(i);
                    }
                }
            }
            return t;
        } catch (NullPointerException e) {
            for (int i = 0; i < size(); i++) {
                if (arrayList[i] == null) {
                    remove(i);
                }
            }
            return t;
        }
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }
}
