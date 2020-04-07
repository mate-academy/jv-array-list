package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int SIZE = 10;
    private T[] arrayList;
    private int count;

    public ArrayList() {
        arrayList = (T[]) new Object[SIZE];
        count = 0;
    }

    @Override
    public void add(T value) {
        growArray();
        arrayList[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index > count || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        growArray();
        System.arraycopy(arrayList, index, arrayList, index + 1, count - index);
        arrayList[index] = value;
        count++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < count && index >= 0) {
            return arrayList[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index < count && index >= 0) {
            arrayList[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < count && index >= 0) {
            T result = arrayList[index];
            System.arraycopy(arrayList, index + 1, arrayList, index, count - index - 1);
            count--;
            return result;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < count; i++) {
            if (arrayList[i] != null && arrayList[i].equals(t) || arrayList[i] == t) {
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

    public void growArray() {
        if (count == arrayList.length) {
            int newSize = (int) (arrayList.length * 1.5);
            arrayList = Arrays.copyOf(arrayList, newSize);
        }
    }
}
