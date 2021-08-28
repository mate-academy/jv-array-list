package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final int INITIAL_SRC_POS = 0;
    private static final double GROW_SIZE = 1.5;
    private boolean arrayIsEmpty = true;
    private T[] arrayData;
    private int size;
    private int currentSizeOfArray = INITIAL_SIZE;

    @SuppressWarnings({"unchecked"})
    public ArrayList() {
        arrayData = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        if (!arrayIsEmpty) {
            if (!ensureCapacity()) {
                grow();
            }
            arrayData[size] = value;
            size++;
        } else {
            arrayData[size] = value;
            size++;
            arrayIsEmpty = false;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist, your index is "
                    + index + " last index is " + size);
        } else if (index == size && ensureCapacity()) {
            arrayData[index] = value;
            size++;
        } else if (indexCapacity(index) && ensureCapacity()) {
            arrayCopyAdd(index);
            arrayData[index] = value;
        } else {
            grow();
            arrayCopyAdd(index);
            arrayData[index] = value;
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
        if (indexCapacity(index)) {
            return arrayData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index are not exist,"
                + " please input right index");
    }

    @Override
    public void set(T value, int index) {
        if (!indexCapacity(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist,"
                    + " please input right index");
        }
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist,"
                    + " please input right index");
        }
        T value = arrayData[index];
        arrayCopyRemove(index);
        return value;
    }

    @Override
    public T remove(T element) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if ((element == null
                    && arrayData[i] == null)
                    || element != null && element.equals(arrayData[i])) {
                arrayCopyRemove(i);
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

    private void arrayCopyAdd(int index) {
        size++;
        System.arraycopy(arrayData, index, arrayData, ++index, size - index);
    }

    private void arrayCopyRemove(int index) {
        System.arraycopy(arrayData, ++index, arrayData, --index, size - index);
        size--;
    }

    private boolean ensureCapacity() {
        return (size + 1) < currentSizeOfArray;
    }

    private boolean indexCapacity(int index) {
        return ((index >= 0 && index < size));
    }
}
