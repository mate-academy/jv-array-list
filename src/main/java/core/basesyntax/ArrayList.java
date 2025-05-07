package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASIC_LENGTH = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[BASIC_LENGTH];
    }

    @Override
    public void add(T value) {
        if (checkSize()) {
            resize();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
        if (index == size) {
            add(value);
            return;
        } else if (list.length == size) {
            resize();
        }
        addByIndex(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexIsValid(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        indexIsValid(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        indexIsValid(index);
        T temp = list[index];
        System.arraycopy(list,index + 1,list,index,size - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (compare(list[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("ArrayList don`t have this element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexIsValid(int index) {
        if (!(index < size && index >= 0)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
    }

    private boolean compare(T element1, T element2) {
        return element1 == element2 || (element1 != null) && element1.equals(element2);
    }

    private boolean checkSize() {
        return size == list.length;
    }

    private void addByIndex(T value, int index) {
        System.arraycopy(list,index,list,index + 1,size - index);
        list[index] = value;
        size++;
    }

    private void resize() {
        T[] array = (T[]) new Object[list.length + (list.length >> 1)];
        System.arraycopy(list, 0, array, 0, list.length);
        list = array;
    }
}
