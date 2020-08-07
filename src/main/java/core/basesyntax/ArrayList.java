package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] arrayList;

    public ArrayList() {
        arrayList = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resize();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range, size of array is  "
                    + size);
        }

        resize();
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
            if (t == arrayList[i] || arrayList[i] != null && arrayList[i].equals(t)) {
                return remove(i);
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
        if (size == arrayList.length) {
            arrayList = Arrays.copyOf(arrayList, arrayList.length * 3 / 2 + 1);
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range, size of array is  "
                    + size);
        }
    }
}
