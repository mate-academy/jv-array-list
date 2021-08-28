package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private boolean arrayIsEmpty = true;
    private T[] arrayData;
    private int size;
    private int currentSizeOfArray = INITIAL_SIZE;
    private static final int INITIAL_SRC_POS = 0;
    private static final double GROW_SIZE = 1.5;

    @SuppressWarnings({"unchecked"})
    public ArrayList() {
        arrayData = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        if (checkForEmpty(value)) {
            if (!ensureCapacity()) {
                grow();
            }
            arrayData[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist, your index is "
                    + index + " last index is " + size);
        }
        else if (index == size && ensureCapacity()) {
            arrayData[index] = value;
            size++;
            }
        else if (indexCapacity(index) && ensureCapacity()) {
            arrayCopySet(index);
            arrayData[index] = value;
            size++;
        }
        else if (indexCapacity(index) && !ensureCapacity()) {
            grow();
            arrayCopySet(index);
            arrayData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() >= currentSizeOfArray) {
            grow();
            }
        for (int i = 0; i < list.size(); i++) {
            arrayData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if(indexCapacity(index)) {
            return arrayData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index are not exist, please input right index");
    }

    @Override
    public void set(T value, int index) {
        if (!indexCapacity(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist, please input right index");
        }
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!indexCapacity(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist, please input right index");
        }
        T value = arrayData[index];
        if (indexCapacity(index) && ensureCapacity()) {
            arrayCopyRemove(index);
            size--;
        } else if (index == size && ensureCapacity()) {
            grow();
            arrayCopyRemove(index);
            size--;
        }
        return value;
    }

    @Override
    public T remove(T element) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if ((element == null
                    && element == arrayData[i])
                    || element != null && element.equals(arrayData[i])) {
                if (!ensureCapacity()) {
                    grow();
                }
                arrayCopyRemove(i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Element are not exist in data");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @SuppressWarnings({"unchecked"})
    private void grow() {
        T[] arrayDataTemp = (T[]) new Object[(int) (currentSizeOfArray * GROW_SIZE)];
        System.arraycopy(arrayData, INITIAL_SRC_POS,
                arrayDataTemp, INITIAL_SRC_POS, arrayData.length);
        arrayData = arrayDataTemp;
        currentSizeOfArray = (int) (currentSizeOfArray * GROW_SIZE);
    }

    private boolean checkForEmpty(T value) {
        if (arrayIsEmpty) {
            arrayData[0] = value;
            size++;
            arrayIsEmpty = false;
            return false;
        }
        return true;
    }

    private void arrayCopySet(int index) {
        int numberOfCopiedElements = size +1;
            System.arraycopy(arrayData, index, arrayData, ++index, numberOfCopiedElements - index);
    }

    private void arrayCopyRemove(int index) {
        System.arraycopy(arrayData, ++index, arrayData, --index, size - index);
    }

    private boolean ensureCapacity() {
        return (size + 1) < currentSizeOfArray;
    }

    private boolean indexCapacity(int index) {
        return ((index >= 0 && index < size));
    }
}
