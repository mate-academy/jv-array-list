package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private int quantity = DEFAULT_SIZE;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        growArrayIfFull();
        elements[quantity] = value;
        quantity += 1;
    }

    @Override
    public void add(T value, int index) {
        checkInRangeForAddition(index);
        growArrayIfFull();
        System.arraycopy(elements, index, elements, index + 1, quantity - index);
        elements[index] = value;
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
        Object temp = elements[index];

        if (index != quantity - 1) {
            System.arraycopy(elements, index + 1, elements, index, quantity - index);
        }
        quantity -= 1;
        return (T) (temp);
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
        return quantity == 0;
    }

    private void growArrayIfFull() {
        if (size() == elements.length) {
            Object[] temp = new Object[elements.length + elements.length / 2 + 1];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
    }

    private void checkInRangeForGet(int index) {
        if (index >= quantity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", for Size: " + quantity);
        }
    }

    private void checkInRangeForAddition(int index) {
        if (index > quantity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", for Size: " + quantity + 1);
        }
    }
}
