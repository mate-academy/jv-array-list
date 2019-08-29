package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private  int capasity = 7;
    private int sizeArray;
    private Object[] list1;

    ArrayList() {
        list1 = (T[]) new Object[capasity];
    }

    @Override
    public void add(T value) {
        resize();
        list1[sizeArray] = value;
        sizeArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        resize();
        System.arraycopy(list1, index, list1, index + 1, list1.length - 2);
        list1[index] = value;
        sizeArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= sizeArray || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) list1[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        list1[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T remove = (T) list1[index];
        for (int i = index; i < list1.length - 1; i++) {
            list1[i] = list1[i + 1];
        }
        sizeArray--;
        return remove;
    }

    @Override
    public T remove(T t) {
        int result = 0;
        while (result < size()) {
            if (list1[result] == t) {
                return remove(result);
            }
            result++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean isEmpty() {
        return list1.length == 0;
    }

    public void resize() {
        if (sizeArray >= list1.length) {
            list1 = Arrays.copyOf(list1, list1.length << 1);
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < list1.length; i++) {
            s += "" + list1[i] + "\n";
        }
        return s;
    }
}
