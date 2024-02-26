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

    public ArrayList(int capacity) {
        if (capacity == 0) {
            this.dataArray = EMPTY_DATAARRAY;
        } else if (capacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Capacity can't be negative");
        } else {
            this.dataArray = new Object[capacity];
        }
    }

    @Override
    public void add(T value) {
        growIfFull();
        addOnNextFreeSell(value);
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index);
        if (index == size && size != dataArray.length) {
            addOnNextFreeSell(value);
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
            growTo(dataArray, list);
        }
        for (int i = 0; i < list.size(); i++) {

            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isIndexExist(index)) {
            return (T) dataArray[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexExist(index)) {
            dataArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (isIndexExist(index)) {
            return removeElement(index);
        }
        return null;
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
            grow(dataArray);
        }
    }

    private void grow(Object[] fullArray) {
        dataArray = new Object[fullArray.length + fullArray.length / 2];
        System.arraycopy(fullArray, 0, dataArray, 0, fullArray.length);
    }

    private void growTo(Object[] fullArray, List<T> list) {
        dataArray = new Object[size + list.size()];
        System.arraycopy(fullArray, 0, dataArray, 0, fullArray.length);
    }

    private void addSize() {
        growIfFull();
        size++;
    }

    private void addOnNextFreeSell(T value) {
        addSize();
        dataArray[size - 1] = value;
    }

    private void addByIndex(T value, int index) {
        addSize();
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
    }

    private boolean isIndexExist(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index don't exist");
        } else {
            return true;
        }
    }

    private void indexValidation(int index) {
        if (index >= 0 && index <= size) {
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("This index don't exist");
    }

    private boolean areObjectsEquals(T element, Object listElement) {
        return (element == null && listElement == null)
                || (element != null && element.equals(listElement));
    }

    private T removeElement(int index) {
        indexValidation(index);
        Object objectToDelete = dataArray[index];
        if (size - 1 == index) {
            size--;
            dataArray[index] = null;
            return (T) objectToDelete;
        }
        size--;
        System.arraycopy(dataArray, 0, dataArray, 0, index);
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        dataArray[size] = null;
        return (T) objectToDelete;
    }
}
