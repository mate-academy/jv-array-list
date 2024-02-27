package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final Object[] EMPTY_DATAARRAY = {};
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataArray;
    private int size;

    public ArrayList() {
        this.dataArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index);
        if (index == size && size != dataArray.length) {
            add(value);
        } else {
            addByIndex(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        if (list.size() + size > dataArray.length) {
            growTo(list);
        }
        for (int i = 0; i < list.size(); i++) {

            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexExist(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
        return removeElement(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (areObjectsEquals(element, dataArray[i])) {
                return removeElement(i);
            }
        }
        throw new NoSuchElementException("There aren't such elements in the list like " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        if (dataArray.length == size) {
            grow();
        }
    }

    private void grow() {
        Object[] fullArray = dataArray;
        dataArray = new Object[fullArray.length + fullArray.length / 2];
        System.arraycopy(fullArray, 0, dataArray, 0, fullArray.length);
    }

    private void growTo(List<T> list) {
        Object[] fullArray = dataArray;
        dataArray = new Object[size + list.size()];
        System.arraycopy(fullArray, 0, dataArray, 0, fullArray.length);
    }

    private void addByIndex(T value, int index) {
        growIfFull();
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size++;
    }

    private void isIndexExist(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index don't exist");
        } else {
            return;
        }
    }

    private void indexValidation(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index don't exist");
        } else {
            return;
        }
    }

    private boolean areObjectsEquals(T element, Object listElement) {
        return (element == null && listElement == null)
                || (element != null && element.equals(listElement));
    }

    private T removeElement(int index) {
        indexValidation(index);
        size--;
        System.arraycopy(dataArray, 0, dataArray, 0, index);
        Object objectToDelete = dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        dataArray[size] = null;
        return (T) objectToDelete;
    }
}
