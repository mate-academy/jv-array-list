package core.basesyntax;

import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private int maxSize = 0;
    private Object[] elementData;

    @Override
    public void add(T value) {
        if (this.isEmpty()) {
            elementData = new Object[DEFAULT_SIZE];
            maxSize = DEFAULT_SIZE;
        } else {
            if (this.size == maxSize) {
                maxSize = maxSize + maxSize / 2;
                Object[] oldElementData = elementData;
                elementData = new Object[maxSize];
                elementData = oldElementData;
            }
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < size) {
          Object[] oldElementData = new Object[size];
          oldElementData = elementData;
          for (int i = 0; i < index; i++) {
              elementData[i] = oldElementData[i];
          }
          elementData[index] = value;
          for (int i = index + 1; i < maxSize; i++) {
              elementData[i] = oldElementData[i-1];
          }
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        T oldValue = (T) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    @Override
    public T remove(T element) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }
}
