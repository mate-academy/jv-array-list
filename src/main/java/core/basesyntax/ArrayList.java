package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int counter;

    private T[] storage = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        checkExtention();
        storage[counter++] = value;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexInBound(index);
        checkExtention();
        if (index == counter) {
            add(value);
            return;
        }

        T tmp = storage[index];
        counter++;
        for (int i = index; i < counter; i++) {
            if (i == index) {
                storage[i] = value;
            } else {
                T tmp2 = storage[i];
                storage[i] = tmp;
                tmp = tmp2;
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        verifyIndexInBoundOrEmpty(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndexInBoundOrEmpty(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndexInBoundOrEmpty(index);
        T returnValue = storage[index];
        for (int i = index; i < counter - 1; i++) {
            storage[i] = storage[i + 1];
        }
        counter--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < counter; i++) {
            if (element != null && element.equals(storage[i])) {
                return remove(i);
            } else if (element == null && storage[i] == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    private void checkExtention() {
        if (counter == storage.length) {
            extend();
        }
    }

    private void verifyIndexInBound(int index) {
        if (index < 0 || index > counter) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
    }

    private void verifyIndexInBoundOrEmpty(int index) {
        if (isEmpty() || (index < 0 || index >= counter)) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
    }

    private void verifyIsNotEmpty() {
        if (isEmpty()) {
            throw new ArrayListIndexOutOfBoundsException("Arraylist is empty");
        }
    }

    private void extend() {
        T[] copy = storage.clone();
        double capacityMultiplier = 1.5;
        storage = (T[]) new Object[(int) (storage.length * capacityMultiplier)];
        for (int i = 0; i < copy.length; i++) {
            storage[i] = copy[i];
        }
    }
}
