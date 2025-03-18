package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private T[] myArrayList = (T[]) new Object[10];
    private int size = 0;

    public T[] getMyArrayList() {
        return myArrayList;
    }

    public void setMyArrayList(T[] myArrayList) {
        this.myArrayList = myArrayList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void incrSize() {
        if (size != myArrayList.length) {
            return;
        }

        int newSize = (int) (myArrayList.length * 1.5);

        T[] newArr = (T[]) new Object[newSize];

        System.arraycopy(myArrayList, 0, newArr, 0, myArrayList.length);

        myArrayList = newArr;
    }

    @Override
    public void add(T value) {
        incrSize();
        myArrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }

        if (size == myArrayList.length) {
            incrSize();
        }

        for (int i = size; i > index; i--) {
            myArrayList[i] = myArrayList[i - 1];
        }

        myArrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return myArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        myArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }

        final T removedElement = myArrayList[index];

        for (int i = index; i < size - 1; i++) {
            myArrayList[i] = myArrayList[i + 1];
        }

        myArrayList[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, myArrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
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
