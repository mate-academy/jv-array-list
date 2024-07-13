package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resize();
        if (index < size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = value;
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
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T returnedElement = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[size - 1] = null;
        size--;
        return returnedElement;
    }

    /* @Override
     public T remove(T element) {
         for (int i = 0; i < data.length; i++) {
             if (element == null && data[i] == null
                     || element != null && element.equals(data[i])) {
                 return remove(i);
             }
         }
         throw new NoSuchElementException("This element: " + element
                 + " does not belong to this list");
     }*/
    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list, " + element);
        }
        return remove(index);
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
        if (data.length == size) {
            int newCapacity = (int) (data.length * GROW_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(data, 0, newArray, 0, size);
            data = (T[]) newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " is invalid, "
                    + "size of this list is " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " is invalid, "
                    + "size of this list is " + size);
        }
    }

    private int indexOf(T element) {
        int index = -1;
        for (int i = 0; i < data.length; i++) {
            if (element == null && data[i] == null
                    || data[i] != null && data[i].equals(element)) {
                return i;
            }
        }
        return index;
    }
}
