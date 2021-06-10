package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] objects;
    private int size;

    public ArrayList() {
        objects = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        objects = (E[]) new Object[capacity];
    }

    @Override
    public void add(E value) {
        add(value, size);
    }

    @Override
    public void add(E value, int index) {
        if (size == objects.length) {
            grow();
        }
        if (index > size || index < 0) {
            throwIndexException();
        }
        System.arraycopy(objects, index, objects,index + 1, objects.length - index - 1);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<E> list) {
        for (int t = 0; t < list.size(); t++) {
            add(list.get(t));
        }
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throwIndexException();
        }
        return objects[index];
    }

    @Override
    public int indexOf(E element) {
        for (int t = 0; t < size; t++) {
            if (element == objects[t] || element != null
                    && element.equals(objects[t])) {
                return t;
            }
        }
        return -1;
    }

    @Override
    public void set(E value, int index) {
        if (index >= size || index < 0) {
            throwIndexException();
        }
        objects[index] = value;
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throwIndexException();
        }
        E whatIsRemoved = objects[index];

        System.arraycopy(objects, index + 1, objects, index, objects.length - index - 1);
        //for (int t = index; t <= size - 1; t++) {
        //    if (t == size || t + 1 == size) {
        //        break;
        //    }
        //    objects[t] = objects[t + 1];
        //}
        size--;
        return whatIsRemoved;
    }

    @Override
    public E remove(E element) {
        int index = indexOf(element);
        if (index >= 0) {
            return remove(index);
        }
        throw new NoSuchElementException("No such element in ArrayList!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        E[] valuesBuffer = objects;
  
        objects = (E[]) new Object[objects.length + (objects.length >> 1)];
        System.arraycopy(valuesBuffer, 0, objects, 0, valuesBuffer.length);
    }

    private void throwIndexException() {
        throw new ArrayListIndexOutOfBoundsException("There is no index like that!");
    }
}
