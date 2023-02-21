package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_MESSAGE = "Wrong index ";
    private static final String NO_SUCH_MESSAGE = "No such element in array list";
    private static final int DEFAULT_CAPACITY = 10;
    private T[] listArray;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.listArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexInclusiveSize(index);
        if (size == listArray.length) {
            resizeListArray(index);
        } else {
            System.arraycopy(listArray, index, listArray, index + 1, size - index);
        }
        listArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return listArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        listArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T temp = listArray[index];
        if (index < size - 1) {
            System.arraycopy(listArray, index + 1, listArray, index, size - index - 1);
        }
        listArray[--size] = null;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < listArray.length; i++) {
            if (Objects.equals(listArray[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_MESSAGE + index);
        }
    }

    private void checkIndexInclusiveSize(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_MESSAGE + index);
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeListArray(int index) {
        T[] temp = (T[]) new Object[listArray.length + (listArray.length >> 1)];
        System.arraycopy(listArray, 0, temp, 0, index);
        System.arraycopy(listArray, index, temp, index + 1, size - index);
        listArray = temp;
    }
}
