package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    private static final double ZOOM_SIZE = 1.5;
    private Object[] elementData = new Object[INIT_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexForAdd(index);
        if (elementData.length == size) {
            resize();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int numNew = list.size();
        for (int i = 0; i < numNew; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        fastRemove(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (((element == null) && (elementData[i] == null))
                    || ((element != null) && (element.equals(elementData[i])))) {
                index = i;
                break;
            }
        }
        try {
            T result = (T) elementData[index];
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Specified element not found: " + element);
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

    @Override
    public String toString() {
        return "ArrayList{" + "elementData=" + Arrays.toString(elementData)
                + ", size=" + size + '}';
    }

    public void resize() {
        int newLength = (int) (elementData.length * ZOOM_SIZE);
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + ", Size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist, Index: "
                    + index + ", Size: " + size);
        }
    }
}
