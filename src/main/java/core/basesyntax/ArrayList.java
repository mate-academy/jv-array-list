package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private int capasity = 7;
    private static int arrayLength = 0;
    private int indexList = 0;
    private Object[] list1 = (T[]) new Object[capasity];


    @Override
    public void add(T value) {
        resize();
        list1[indexList] = value;
        indexList++;
        arrayLength++;

    }

    @Override
    public void add(T value, int index) {
        if (index >= list1.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        list1[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > list1.length) {
            throw  new ArrayIndexOutOfBoundsException();
        }
        return (T)list1[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > list1.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        list1[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > list1.length) {
            throw  new ArrayIndexOutOfBoundsException();
        }
        T remove = (T) list1[index];
        for (int i = index; i < list1.length - 1; i++) {
            list1[i] = list1[i + 1];
        }
        capasity--;
        return remove;
    }

    @Override
    public T remove(T t) {
        int result = 0;
        if (list1[result].equals(t) || list1[result] == t) {
            remove(result);
            result++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return arrayLength;
    }

    @Override
    public boolean isEmpty() {
        return list1.length == 0;
    }

    public void resize() {
        if (indexList == list1.length) {
            list1 = Arrays.copyOf(list1, capasity << 1);
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