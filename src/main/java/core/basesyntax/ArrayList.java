package core.basesyntax;

import java.util.Arrays;
/**
 * Реалізувати свій ArrayList, який імплементує інтерфейс List
 */
public class ArrayList<T> implements List<T> {
    private Object[] arrayList = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        this.checkSize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size) {
            this.checkSize();
            for (int i = size; i > index; i--) {
                arrayList[i] = arrayList[i-1];
            }
            arrayList[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size) {
            return (T) arrayList[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index < size) {
            arrayList[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < size) {
            for (int i = index; i < size; i++) {
                set((T) arrayList[i + 1], i);
            }
            return (T) arrayList[size--];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i].equals(t)) {
                return this.remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    private void checkSize() {
        if (size == arrayList.length) {
            arrayList = Arrays.copyOf(arrayList, arrayList.length + (arrayList.length >> 1));
        }
    }
}
