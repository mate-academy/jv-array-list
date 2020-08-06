package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        size = 0;
        elements = new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            growCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidationForAdd(index);
        if (elements.length == size) {
            growCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        indexValidation(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T deleted = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deleted;
    }

    @Override
    public T remove(T t) {
        for (int index = 0; index < size; index++) {
            if ((t == null || elements[index] == null)
                    ? elements[index] == t : elements[index].equals(t)) {
                return remove(index);
            }
        }
        if (true) {
            throw new NoSuchElementException("The element is not found");
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] growCapacity() {
        int newCapacity = size + (size >> 1) + 1;
        Object[] grownCapacity = new Object[newCapacity];
        System.arraycopy(elements, 0, grownCapacity, 0, size);
        return elements = grownCapacity;
    }

    public void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range.");
        }
    }

    public void indexValidationForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index is out of range.");
        }
    }
}
