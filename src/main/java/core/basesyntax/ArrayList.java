package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int MAGIC_NUM = 2;
    private int count;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
        count = 0;

    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[count++] = value;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > count) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
        checkCapacity();
        System.arraycopy(elements, index, elements, index + 1, count - index);
        elements[index] = value;
        count++;

    }

    @Override
    public void addAll(List<T> list) {
        Object[] temp = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            temp[i] = list.get(i);
        }

        for (int i = 0; i < temp.length; i++) {
            add((T) temp[i]);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T remove = (T) elements[index];
        System.arraycopy(elements,index + 1,elements,index,count - index - 1);
        elements[--count] = null;
        return remove;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < count; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(elements[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Non existing element");
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
        }
    }

    public void checkCapacity() {
        if (count == elements.length) {
            int newSize = elements.length + elements.length / MAGIC_NUM;
            Object [] newElem = new Object[newSize];
            System.arraycopy(elements,0,newElem,0,elements.length);
            elements = newElem;
        }
    }

}
