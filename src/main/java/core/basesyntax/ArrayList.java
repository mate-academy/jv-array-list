package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final int START_SIZE = 0;
    private static final int NONE_ELEMENT_FIND = -1;
    private Object[] array;
    private int realSize;
    private int capacity;

    enum ActionMethod {
        INSERT,
        GET
    }

    public ArrayList() {
        array = new Object[START_CAPACITY];
        realSize = START_SIZE;
        capacity = START_CAPACITY;
    }

    private void isWrongIndex(int index, ActionMethod action)
            throws ArrayListIndexOutOfBoundsException {
        if (action == ActionMethod.GET) {
            if (index < 0 || index >= realSize) {
                throw new ArrayListIndexOutOfBoundsException("Index is out of range");
            }
        }
        if (action == ActionMethod.INSERT) {
            if (index < 0 || index > realSize) {
                throw new ArrayListIndexOutOfBoundsException("Index is out of range");
            }
        }
    }

    private void grow() {
        capacity = capacity + (capacity >> 1);
        Object[] tempArray = new Object[capacity];
        System.arraycopy(array, 0, tempArray, 0, realSize);
        array = tempArray;
    }

    @Override
    public void add(T value) {
        if (realSize == capacity) {
            grow();
        }
        array[realSize] = value;
        realSize++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index, ActionMethod.INSERT);
        if (realSize == capacity) {
            grow();
        }
        Object[] tempArray = new Object[capacity];
        System.arraycopy(array, 0, tempArray, 0, index);
        tempArray[index] = (T) value;
        System.arraycopy(array, index, tempArray, index + 1, realSize - index);
        array = tempArray;
        realSize++;
    }

    @Override
    public void addAll(List<T> list) {
        ArrayList<T> newArrayList = new ArrayList<>();
        newArrayList = (ArrayList) list;
        while (realSize + list.size() > capacity) {
            grow();
        }
        System.arraycopy(newArrayList.array, 0, array, realSize, list.size());
        realSize = realSize + list.size();
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index, ActionMethod.GET);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index, ActionMethod.GET);
        array[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index, ActionMethod.GET);
        Object[] tempArray = new Object[capacity];
        if (index == START_SIZE) {
            System.arraycopy(array, 1, tempArray, 0, realSize);
        }
        if (index > START_SIZE && index != realSize - 1) {
            System.arraycopy(array, 0, tempArray, 0, index - 1);
            System.arraycopy(array, index + 1, tempArray, index, realSize - index);
        }
        T removeValue = (T) array[index];
        array = tempArray;
        if (realSize > START_SIZE) {
            realSize--;
        }
        return removeValue;
    }

    @Override
    public T remove(T element)
            throws ArrayListIndexOutOfBoundsException, NoSuchElementException {
        int indexFindElement = NONE_ELEMENT_FIND;
        for (int i = 0; i < realSize; i++) {
            if ((array[i] == null && element == null)
                    || array[i] != null && array[i].equals(element)) {
                indexFindElement = i;
            }
        }
        if (indexFindElement != NONE_ELEMENT_FIND) {
            T removeValue = (T) array[indexFindElement];
            remove(indexFindElement);
            return removeValue;
        }
        throw new NoSuchElementException("Element was not found in List");
    }

    @Override
    public int size() {
        return realSize;
    }

    @Override
    public boolean isEmpty() {
        return realSize == 0;
    }
}
