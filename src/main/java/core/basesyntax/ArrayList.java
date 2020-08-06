package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;
    private int index;

    ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (index == elements.length) {
            resize();
        }
        elements[index] = value;
        index++;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, this.index - index);
        elements[index] = value;
        this.index++;
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = elements[index];
        System.arraycopy(elements, index + 1, elements, index, this.index - index);
        size--;
        this.index--;
        return element;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], value)) {
                return remove(i);
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

    private void checkIndex(int index) {
        if (index < 0 || index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @SuppressWarnings("checked")
    private void resize() {
        Object[] newArray = new Object[elements.length + (elements.length / 2 + 1)];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = (T[]) newArray;
    }
}
