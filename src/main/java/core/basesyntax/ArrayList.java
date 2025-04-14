package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private T[] masiv;
    private int size;

    public ArrayList() {
        masiv = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        moreCapacity();
        masiv[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        moreCapacity();
        for (int i = size - 1; i >= index; i--) {
            masiv[i + 1] = masiv[i];
        }
        masiv[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return masiv[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        masiv[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T removedElement = masiv[index];
        for (int i = index; i < size - 1; i++) {
            masiv[i] = masiv[i + 1];
        }
        masiv[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (masiv[i] != null && masiv[i].equals(element)
                    || masiv[i] == null && element == null) {
                T removedElement = remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("No such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (!(index < size && index >= 0)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void moreCapacity() {
        if (size == masiv.length) {
            masiv = Arrays.copyOf(masiv, (int)(masiv.length * 1.5) + 1);
        }
    }

}
