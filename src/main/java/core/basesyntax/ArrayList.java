package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private int sizeOfList = 10;
    private int lastIndex;
    private Object[] list;

    ArrayList() {
        list = new Object[sizeOfList];
    }

    @Override
    public void add(T value) {
        if (lastIndex >= list.length) {
            increaseCapacity();
        }
        list[lastIndex] = value;
        lastIndex++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
        if (lastIndex < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element by this index");
        }
        if (lastIndex == list.length) {
            increaseCapacity();
        }
        System.arraycopy(list, index, list, index + 1, lastIndex - index);
        list[index] = value;
        lastIndex++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));

        }

    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) list[index];
        System.arraycopy(list, index + 1, list, index, --lastIndex - index);
        list[lastIndex] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {

        int index = getIndexOfElement(element);
        try {
            remove(index);
        } catch (RuntimeException e) {
            throw new NoSuchElementException(
                    "There is no such element in ArrayList, method: " + element);
        }
        return element;
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    private void increaseCapacity() {
        int newCapacity = (int) ((int) list.length * 1.5);
        Object[] copiedElements = new Object[newCapacity];
        System.arraycopy(list, 0, copiedElements, 0, lastIndex);
        list = copiedElements;
    }

    private void checkIndexAdd(int index) {
        if (lastIndex < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index");
        }
    }

    private void checkIndex(int index) {
        if (lastIndex <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index");
        }
    }

    private int getIndexOfElement(T element) {
        int index = -1;
        for (int i = 0; i < lastIndex; i++) {
            if ((element == null && element == list[i])
                    || (element != null && element.equals(list[i]))) {
                index = i;
                break;
            }
        }
        return index;
    }
}
