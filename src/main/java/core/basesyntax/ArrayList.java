package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int size;
    private Object[] elementData = new Object[10];

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (listSize > elementData.length - size) {
            return;
        }
        for (int i = 0; i < listSize; i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Sorry");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (index + 1 == size) {
            T t = (T) elementData[index];
            elementData[index] = null;
            size--;
            return t;
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Sorry");
        } else {
            T t = (T) elementData[index];
            for (int i = 0; i < size; i++) {
                if (i >= index) {
                    elementData[i] = elementData[i + 1];
                }
            }
            elementData[--size] = null;
            return t;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && element == elementData[i])
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i); //return remove(i - 1);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    public Object[] grow() {
        Object[] newElementData = new Object[(int) (elementData.length * 1.5)];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        return newElementData;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
