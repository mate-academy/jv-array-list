package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            arrayList = addSize(arrayList);
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == arrayList.length) {
            arrayList = addSize(arrayList);
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
            arrayList[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("IndexOutOfBoundsException");
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return arrayList[index];
        }
        throw new ArrayListIndexOutOfBoundsException("IndexOutOfBoundsException");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            arrayList[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("IndexOutOfBoundsException");
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T result = arrayList[index];
            System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
            arrayList[--size] = null;
            return result;
        }
        throw new ArrayListIndexOutOfBoundsException("IndexOutOfBoundsException");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || (arrayList[i] != null && arrayList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElementException");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] addSize(T[] arrayList) {
        T[] newList = (T[]) new Object[(int) (size * 1.5)];
        System.arraycopy(arrayList, 0, newList, 0, size);
        return newList;
    }
}
