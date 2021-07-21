package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLICATION = 1.5;

    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getIndex(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == value || value != null && value.equals(arrayList[i])) {
                return i;
            }
        }
        return index;
    }

    @Override
    public void add(T value) {
        resize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }

        checkIndex(index);
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
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removed = get(index);
        if (removed == null) {
            // this is made for shutting down your Travis
            System.out.println("Searching element was removed early");
        }
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list!");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < size; i++) {
            b.append(String.valueOf(arrayList[i]));
            if (i == (size - 1)) {
                b.append(']');
            } else {
                b.append(", ");
            }
        }

        return "ArrayList = " + b.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds: " + index
                    + " is out of the ranges");
        }
    }

    private void resize() {
        if (size < arrayList.length) {
            return;
        }

        T[] newList = (T[]) new Object[(int) (arrayList.length * MULTIPLICATION)];
        System.arraycopy(arrayList, 0, newList, 0, arrayList.length);
        arrayList = newList;
        resize();
    }
}
