package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    private static final double ZOOM_SIZE = 1.5;
    private static final String BUG_INDEX = "The specified index does not exist";
    private static final String BUG_FOUND = "Specified element not found";
    private Object[] elementData = new Object[INIT_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            resize(lengthZoom());
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BUG_INDEX);
        }
        if (elementData.length == size) {
            resize(lengthZoom());
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int numNew = list.size();
        if (numNew == 0) {
            return;
        }
        if (numNew > (elementData.length - size)) {
            resize((size + numNew));
        }
        for (int i = 0; i < numNew; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BUG_INDEX);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BUG_INDEX);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BUG_INDEX);
        }
        final Object[] es = elementData;
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        int index = -1;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (es[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(es[i])) {
                    index = i;
                    break;
                }
            }
        }
        try {
            T result = (T) es[index];
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(BUG_FOUND);
        }
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
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

    public int lengthZoom() {
        return (int) (elementData.length * ZOOM_SIZE);
    }

    public void resize(int newLength) {
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
}
