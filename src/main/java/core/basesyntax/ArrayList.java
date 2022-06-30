package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH = 10;
    private static final double LENGTH_INCREASING = 1.5;
    private int index;
    private T[] ourArrayList;

    public ArrayList() {
        ourArrayList = (T[]) new Object[START_LENGTH];
        index = 0;
    }

    @Override
    public void add(T value) {
        if (index == ourArrayList.length) {
            addLengthToArrayList();
        }
        ourArrayList[index] = value;
        index++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException(exceptionMessage(index));
        } else if (index - this.index == 1) {
            add(value);
            return;
        } else if (this.index + 1 >= ourArrayList.length) {
            addLengthToArrayList();
        }
        this.index++;
        addValueByIndex(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (indexNotExist(index)) {
            throw new ArrayListIndexOutOfBoundsException(exceptionMessage(index));
        }
        return ourArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (indexNotExist(index)) {
            throw new ArrayListIndexOutOfBoundsException(exceptionMessage(index));
        }
        ourArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (indexNotExist(index)) {
            throw new ArrayListIndexOutOfBoundsException(exceptionMessage(index));
        }
        T[] newList = (T[]) new Object[(int)(ourArrayList.length - 1)];
        for (int i = 0; i < index; i++) {
            newList[i] = ourArrayList[i];
        }
        for (int i = index; i < newList.length; i++) {
            newList[i] = ourArrayList[i + 1];
        }
        this.index--;
        T removedElement = ourArrayList[index];
        ourArrayList = newList;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(element,ourArrayList[i])) {
                return remove(i);
            }
        }
        if (true) {
            throw new NoSuchElementException("Such element " + element.toString()
                    + " doesn't exist in the List");
        }
        return null;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void addLengthToArrayList() {
        T[] newList = (T[]) new Object[(int)(ourArrayList.length * LENGTH_INCREASING)];
        for (int i = 0; i < ourArrayList.length; i++) {
            newList[i] = ourArrayList[i];
        }
        ourArrayList = newList;
    }

    private void addValueByIndex(T value, int index) {
        T[] newList = (T[]) new Object[ourArrayList.length];
        newList[index] = value;
        for (int i = 0; i < index; i++) {
            newList[i] = ourArrayList[i];
        }
        for (int i = index + 1; i < size(); i++) {
            newList[i] = ourArrayList[i - 1];
        }
        ourArrayList = newList;
    }

    private boolean indexNotExist(int index) {
        return index < 0 || index >= size();
    }

    private String exceptionMessage(int index) {
        return "Invalid index " + index;
    }
}
