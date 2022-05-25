package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int ARRAY_SIZE = 10;
    private int size;
    private Object[] elementData = new Object[ARRAY_SIZE];

    private void correctIndexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

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
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        correctIndexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        correctIndexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        correctIndexCheck(index);
        T t = (T) elementData[index];
        for (int i = 0; i < size; i++) {
            if (index == size - 1) {
                elementData[index] = null;
            } else if (i == index) {
                System.arraycopy(elementData, index + 1, elementData, index, size - index);
            }
        }
        size--;
        return t;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    public void grow() {
        Object[] newElementData = new Object[(int) (elementData.length * 1.5)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
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
