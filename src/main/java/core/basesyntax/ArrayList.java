package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private T[] arrayList;
    private int count = 0;
    private int capacity = 10;

    public ArrayList() {
        arrayList = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (count >= capacity) {
            enlargeArray();
        }
        arrayList[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            System.arraycopy(arrayList, index, arrayList, index + 1, count - index);
            arrayList[index] = value;
            count++;
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
        if (index < count) {
            return arrayList[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < count) {
            arrayList[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            T element = arrayList[index];
            System.arraycopy(arrayList, index + 1, arrayList, index, count - index);
            count--;
            return element;
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < count; i++) {
            if (t == null && arrayList[i] == null
                    || t != null && t.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private void enlargeArray() {
        capacity = capacity + (capacity >> 1);
        T[] newArrayList = (T[]) new Object[capacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, count);
        arrayList = newArrayList;
    }
}
