package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private T[] arrayList = (T[]) new Object[INITIAL_CAPACITY];

    @Override
    public void add(T value) {
        if (size == arrayList.length - 1) {
            resize();
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range, size of array is  "
                    + size);
        }

        if (size == arrayList.length - 1) {
            resize();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);

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
        indexCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);

        T removedItem = arrayList[index];

        System.arraycopy(arrayList, index + 1, arrayList, index, size - index);
        size--;
        return removedItem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null) {
                System.arraycopy(arrayList, i + 1, arrayList, i, size - i);
                size--;
                return null;
            }
            if (t.equals(arrayList[i])) {
                T removedItem = arrayList[i];
                System.arraycopy(arrayList, i + 1, arrayList, i, size - i);
                size--;
                return removedItem;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        arrayList = Arrays.copyOf(arrayList, arrayList.length * 3 / 2 + 1);
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range, size of array is  "
                    + size);
        }
    }
}
