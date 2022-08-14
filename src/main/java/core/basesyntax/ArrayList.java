package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String MESSAGE = "the index is greater than size";
    private int size;
    private T[] arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    private T[] beginning;
    private T[] end;

    private void resize() {
        if (size == arrayList.length) {
            beginning = arrayList;
            arrayList = (T[]) new Object[(int) (size * 1.5)];
            for (int i = 0; i < beginning.length; i++) {
                arrayList[i] = beginning[i];
            }
        }
    }

    @Override
    public void add(T value) {

        resize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE);
        }
        if (size == index) {
            add(value);
        } else {
            resize();
            for (int i = size; i > index; i--) {
                arrayList[i] = arrayList[i - 1];
            }
            arrayList[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            arrayList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE);
        }
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE);
        }
        if (index == size) {
            add(value);
        } else {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE);
        }
        beginning = (T[]) new Object[index];
        end = (T[]) new Object[arrayList.length - index - 1];
        System.arraycopy(arrayList, 0, beginning, 0, index);
        System.arraycopy(arrayList, index + 1, end, 0, arrayList.length - index - 1);
        T result = arrayList[index];
        for (int i = 0; i < size - 1; i++) {
            if (i < index) {
                arrayList[i] = beginning[i];
            } else {
                arrayList[i] = end[i - index];
            }
        }
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] != null && arrayList[i].equals(element) || arrayList[i] == element) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException();
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
