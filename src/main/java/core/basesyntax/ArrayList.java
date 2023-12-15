package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_CAPACITY = 10;
    private int size;
    private int capacity;
    private T[] myList;

    @Override
    public void add(T value) {
        if (this.isEmpty()) {
            myList = (T[]) new Object[MAX_CAPACITY];
            capacity = MAX_CAPACITY;
        }
        if (size == capacity) {resize();}
        myList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if ((index > size) || (index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index dosn't much");
        }
        if (size == capacity) {resize();}
        if (size == index) {
            add(value);
        } else {
            for (int i = size; i > index; i--) {
                myList[i] = myList[i - 1];
            }
            myList[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int j = 1;
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if ((index >= size) || (index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index dosn't much");
        }
        return myList[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if ((index >= size) || (index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index dosn't much");
        }
        myList[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException  {
        if ((index >= size) || (index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index dosn't much");
        }
        T element = myList[index];
        size--;
        for(int i = index; i < size; i++) {
            myList[i] = myList[i + 1];
        }
        return  element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {

            if (myList[i] == null) {
                if  (element == null) {
                    size--;
                    for (int j = i; j < size; j++) {
                        myList[j] = myList[j + 1];
                    }
                    return element;
                }
            } else {
                if (myList[i].equals(element)) {
                    size--;
                    for(int j = i; j < size; j++) {
                        myList[j] = myList[j + 1];
                    }
                    return element;
                }
            }

        }
        throw new NoSuchElementException("NoSuchElement");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    private void resize(){
        T[] oldList = myList;
        capacity += capacity / 2;
        myList = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            myList[i] = oldList[i];
        }
    }
}
