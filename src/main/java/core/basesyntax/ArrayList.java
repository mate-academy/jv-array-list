package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int defaultCapacity = 10;
    private Object[] dataElement;
    private int size = 0;
    private int newCapacity = 0;

    private void resize(int size) {
        if (dataElement != null && size == dataElement.length) {
            newCapacity += size * 2;
            Object[] dataElement1 = new Object[newCapacity];
            System.arraycopy(this.dataElement, 0,
                    dataElement1, 0,size);
            this.dataElement = dataElement1;
        }
    }

    private void checkNullArray() {
        if (dataElement == null) {
            dataElement = new Object[defaultCapacity];
        }
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new
            ArrayListIndexOutOfBoundsException("Your index out of range");
        }
    }

    private void removeElement(int index) {
        int s = size + 1;
        checkIndex(index,s);
        size--;
        Object[] dataElement;
        dataElement = this.dataElement;
        System.arraycopy(dataElement, index + 1, dataElement,
                index,size - index);

    }

    @Override
    public void add(T value) {
        checkNullArray();
        resize(size);
        dataElement[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        int s = size + 1;
        checkIndex(index,s);
        checkNullArray();
        resize(s);
        Object[] dataElement;
        dataElement = this.dataElement;
        System.arraycopy(dataElement, index,
                dataElement, index + 1,
                s - index);
        dataElement[index] = value;
        size = s;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index,size);
        return (T) dataElement[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,size);
        dataElement[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,size);
        Object value = dataElement[index];
        removeElement(index);
        return (T) value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (dataElement[i] == element
                    || dataElement[i] != null
                    && dataElement[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            throw new NoSuchElementException("not find element");
        }
        removeElement(index);
        return element;
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
