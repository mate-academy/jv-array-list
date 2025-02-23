package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        fullSize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        auditIndexAdd(index);
        fullSize();
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
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
        auditIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        auditIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        auditIndex(index);
        T removeType = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removeType;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + "doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void fullSize() {
        if (size == elementData.length) {
            newList();
        }
    }

    private void newList() {
        int newElementSize = (elementData.length * 3) / 2 + 1;
        Object[] newElement = new Object[newElementSize];
        System.arraycopy(elementData, 0, newElement, 0, size);
        elementData = newElement;
    }

    public void auditIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than "
                    + "the array size, or less than 0.");
        }
    }

    public void auditIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than "
                    + "the array size, or less than 0.");
        }
    }
}
