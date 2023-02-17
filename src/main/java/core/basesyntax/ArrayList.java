package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASIC_LENGTH = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[BASIC_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            addSpase();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
        if (index == size) {
            add(value);
            return;
        } else if (list.length == size) {
            addSpase();
        }
        addByIndex(value, index);
    }

    private void addSpase() {
        T[] array = (T[]) new Object[list.length + (list.length >> 1)];
        System.arraycopy(list, 0, array, 0, list.length);
        list = array;
    }

    private void addByIndex(T value, int index) {
        System.arraycopy(list,index,list,index + 1,size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private boolean indexIsValid(int index) {
        if (index < size && index >= 0) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
    }

    @Override
    public T get(int index) {
        indexIsValid(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        indexIsValid(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        indexIsValid(index);
        T temp = list[index];
        System.arraycopy(list,index + 1,list,index,size - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        int elementIndex = foundIndexByElement(element);
        if (isValueByIndex(elementIndex)) {
            return remove(elementIndex);
        }
        throw new NoSuchElementException("ArrayList don`t have this element!");
    }

    private boolean isValueByIndex(int index) {
        return index == -1 ? false : true;
    }

    private int foundIndexByElement(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (compare(list[i], element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean compare(T element1, T element2) {
        return element1 == element2 || (element1 != null) && element1.equals(element2);
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
