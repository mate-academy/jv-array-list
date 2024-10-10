package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    int quantity = DEFAULT_SIZE;
    public Object[] elements = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[quantity] = value;
        quantity += 1;
    }

    @Override
    public void add(T value, int index) {
        checkInRangeForAddition(index);
        growIfArrayFull();
        Object [] temp = new Object[elements.length];
        System.arraycopy(elements, 0, temp, 0, temp.length);
        for (int i = 0, j = 0; i <= quantity ; i++, j++) {
            if (i == index) {
                elements[i] = value;
                i++;
            }
            elements[i] = temp[j];
        }
        quantity += 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
      checkInRangeForGet(index);
        return (T) (elements[index]);
    }

    @Override
    public void set(T value, int index) {
      checkInRangeForGet(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkInRangeForGet(index);
        Object [] temp = new Object[elements.length];
        System.arraycopy(elements, 0, temp, 0, elements.length);
        for (int i = 0, j = 0; i < quantity; i++, j++) {
            if (i == index) {
                j++;
            }
            if (j != quantity) {
                elements[i] = temp[j];
            }
        }
        quantity -= 1;
        elements[quantity] = null;
        return (T) (temp[index]);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < quantity; i++) {


            if (elements[i] == null && element == null) {
                return remove(i);
            }
            if (element != null && elements[i] == null) {
                continue;
            }
            if (elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return quantity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void growIfArrayFull() {
        if (size() == elements.length) {
            int index = 0;
            if (elements.length % 2 == 0) {
                index = elements.length + elements.length / 2;
            } else {
                index = elements.length + elements.length / 2 + 1;
            }
            Object[] temp = new Object[index];
            for (int i = 0; i < elements.length; i++) {
                temp[i] = elements[i];
            }
            elements = temp;
        }
    }

    private void checkInRangeForGet(int index) {
        if (index >= quantity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index goes beyond of size list");
        }
    }

    private void checkInRangeForAddition(int index) {
        if (index > quantity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index goes beyond of size list");
        }
    }
}
