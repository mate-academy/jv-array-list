package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final double INCREASE_ARRAY = 1.5;
    private Object[] arraylist = new Object[ARRAY_SIZE];
    private int size;

    public ArrayList() {
        this.arraylist = arraylist;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size + 1 <= arraylist.length) {
            arraylist[size] = value;
        } else if (size + 1 > ARRAY_SIZE) {
            grow();
            arraylist[size] = value;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckForAdd(index);
        if (size + 1 == arraylist.length) {
            grow();
        }
        System.arraycopy(arraylist,index,arraylist,index + 1,size - index);
        arraylist[index] = value;
        size++;
    }

    private void grow() {
        Object [] newArray = new Object[ (int)(arraylist.length * INCREASE_ARRAY)];
        System.arraycopy(arraylist,0,newArray,0,size);
        arraylist = newArray;
    }

    @Override
    public void addAll(List<T> list) {
        int startIndexForList = size();
        if (list != null && (size + list.size() <= arraylist.length)) {
            for (int j = 0; j < list.size(); j++) {
                add(list.get(j),startIndexForList + j);
            }
        } else if (list != null && size() + list.size() > arraylist.length) {
            grow();
            for (int j = 0; j < list.size(); j++) {
                add(list.get(j), startIndexForList + j);
            }
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) arraylist[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        arraylist[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T returnValue = (T) arraylist[index];
        System.arraycopy(arraylist,index + 1,arraylist,index,size - index - 1);
        size--;
        return returnValue;
    }

    @Override
    public T remove(T t) {
        int index = isElementExist(t);
        return remove(index);
    }

    private int isElementExist(T t) {
        for (int i = 0;i < size();i++) {
            if (arraylist[i] != null && arraylist[i].equals(t) || t == arraylist[i]) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    private void indexCheck(int i) throws ArrayIndexOutOfBoundsException {
        if (size() <= i || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bound");

        }
    }

    private void indexCheckForAdd(int i) throws ArrayIndexOutOfBoundsException {
        if (size < i || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bound");
        }
    }
}
