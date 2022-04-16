package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList(int size) {
        if (size > 0) {
            this.elementData = new Object[size];
            this.size = 0;
        } else if (size == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
            this.size = 0;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "size " + size);
        }
        indexInRange(index, elementData.length - 1);
        Object[] oldElemData = elementData;
        Object[] newElemData = new Object[elementData.length + 1];
        System.arraycopy(oldElemData, 0, newElemData, 0, index);
        newElemData[index] = value;
        System.arraycopy(oldElemData, index, newElemData, index + 1, size - index);
        size++;
        elementData = newElemData;
        return;
    }

    private void indexInRange(int index, int end) {
        if (index == 0) {
            return;
        }
        if (index < 0 || index > end) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "size " + size);
        }
    }

    private void grow() {
        int newSize = (int) (elementData.length + (elementData.length * 0.5));
        grow(newSize);
    }

    private void grow(int count) {
        int newSize = elementData.length + count;
        Object[] tmp = new Object[newSize];
        System.arraycopy(elementData, 0, tmp, 0, elementData.length);
        elementData = tmp;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        indexInRange(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexInRange(index, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexInRange(index, size - 1);
        final T oldValue = (T) elementData[index];
        Object[] newArr = new Object[elementData.length];
        System.arraycopy(elementData, 0, newArr, 0, index);
        if (index != size - 1) {
            System.arraycopy(elementData, index + 1, newArr, index, elementData.length - index - 1);
        }
        size--;
        elementData = newArr;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null) {
                if (element == null) {
                    index = i;
                    break;
                } else {
                    continue;
                }
            }
            if (elementData[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("there is no such element " + element);
        }
        T oldValue = (T) elementData[index];
        remove(index);
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
}
