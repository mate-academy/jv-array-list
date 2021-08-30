package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SRC_POS = 0;
    private static final double GROW_SIZE = 1.5;
    private T[] arrayData;
    private int size;
    private int fullSizeOfArray = 10;

    @SuppressWarnings({"unchecked"})
    public ArrayList() {
        arrayData = (T[]) new Object[fullSizeOfArray];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arrayData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexLegal(index);
        ensureCapacity();
        arrayCopyAdd(index);
        arrayData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexGetLegal(index);
        return arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexGetLegal(index);
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexGetLegal(index);
        T value = arrayData[index];
        arrayCopyRemove(index);
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null
                    && arrayData[i] == null)
                    || element != null && element.equals(arrayData[i])) {
                return remove(i);
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
        T[] arrayDataTemp = (T[]) new Object[(int) (fullSizeOfArray * GROW_SIZE)];
        System.arraycopy(arrayData, INITIAL_SRC_POS,
                arrayDataTemp, INITIAL_SRC_POS, arrayData.length);
        arrayData = arrayDataTemp;
        fullSizeOfArray = (int) (fullSizeOfArray * GROW_SIZE);
    }

    private void arrayCopyAdd(int index) {
        System.arraycopy(arrayData, index, arrayData, ++index, ++size - index);
    }

    private void arrayCopyRemove(int index) {
        System.arraycopy(arrayData, ++index, arrayData, --index, size-- - index);
    }

    private boolean ensureCapacity() {
        if ((size + 1) >= fullSizeOfArray) {
            grow();
            return true;
        }
        return false;
    }

    private boolean isIndexLegal(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new ArrayListIndexOutOfBoundsException("index are not exist");
        }
        return true;
    }

    private boolean isIndexGetLegal(int index) {
        if (!(index >= 0 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException("index are not exist");
        }
        return true;
    }
}
