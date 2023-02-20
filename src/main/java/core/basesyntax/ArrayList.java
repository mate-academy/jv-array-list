package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String ADD_MESSAGE_EXCEPTION = "Can't add to index ";
    private static final String REMOVE_MESSAGE_EXCEPTION = "Can't remove to index ";
    private static final String SET_MESSAGE_EXCEPTION = "Can't set to index ";
    private static final String GET_MESSAGE_EXCEPTION = "Can't get to index ";
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
        checkIndexInclusiveSize(index, ADD_MESSAGE_EXCEPTION + index);
        if (size == listArray.length) {
            resizeListArray(index);
        } else {
            System.arraycopy(listArray, index, listArray, index + 1, size - index);
        }
        listArray[index] = value;
        size++;
    }

    private void checkIndex(int index, String message) {
        if (!(index >= 0 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException(message);
        }
    }

    private void checkIndexInclusiveSize(int index, String message) {
        if (!(index >= 0 && index <= size)) {
            throw new ArrayListIndexOutOfBoundsException(message);
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeListArray(int index) {
        T[] temp = (T[]) new Object[listArray.length + (listArray.length >> 1)];
        System.arraycopy(listArray, 0, temp, 0, index);
        System.arraycopy(listArray, index, temp, index + 1, size - index);
        listArray = temp;
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
        checkIndex(index, GET_MESSAGE_EXCEPTION + index);
        return listArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, SET_MESSAGE_EXCEPTION + index);
        listArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, REMOVE_MESSAGE_EXCEPTION + index);
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
}
