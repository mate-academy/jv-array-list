package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String OUT_OF_BOUNDS_EXCEPTION_MESSAGE
            = "Index is out of this arrays size";
    private static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE
            = "This elements isn't in this array";
    private int nextEmptyPosition;
    private T[] elements;

    public ArrayList() {
        this.nextEmptyPosition = 0;
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkAndIncreaseCapacity();
        elements[nextEmptyPosition++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > nextEmptyPosition) {
            throw new ArrayIndexOutOfBoundsException(OUT_OF_BOUNDS_EXCEPTION_MESSAGE
                    + " Index: " + index + " Array size: " + nextEmptyPosition);
        }
        checkAndIncreaseCapacity();
        System.arraycopy(elements, index, elements, index + 1,
                elements.length - index - 1);
        nextEmptyPosition++;
        elements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexInRange(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInRange(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index,
                elements.length - index - 1);
        nextEmptyPosition--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < nextEmptyPosition; i++) {
            if (elements[i] == null ? elements[i] == t : elements[i].equals(t)) {
                return this.remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
    }

    @Override
    public int size() {
        return nextEmptyPosition;
    }

    @Override
    public boolean isEmpty() {
        return nextEmptyPosition == 0;
    }

    private void increaseCapacity() {
        T[] temp = elements;
        elements = (T[]) new Object[elements.length + (elements.length >> 1)];
        System.arraycopy(temp, 0, elements, 0, temp.length);
    }

    private void checkAndIncreaseCapacity() {
        if (elements.length <= nextEmptyPosition) {
            increaseCapacity();
        }
    }

    private void checkIndexInRange(int index) {
        if (index < 0 || index >= nextEmptyPosition) {
            throw new ArrayIndexOutOfBoundsException(OUT_OF_BOUNDS_EXCEPTION_MESSAGE
                    + " Index: " + index + " Array size: " + nextEmptyPosition);
        }
    }
}
