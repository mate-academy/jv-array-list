package core.basesyntax;
import java.util.NoSuchElementException;
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;
    private static final double GROWTH_FACTOR = 1.5;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    private void ensureCapacity() {
        if (elements.length == size) {
            int newSize = (int) (size * GROWTH_FACTOR);
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }
    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }
    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size)
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        ensureCapacity();
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
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
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
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);        elements[size - 1] = null;
        size--;
        return removedElement;
    }
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
                if (element == elements[i]
                        || element != null
                        && element.equals(elements[i])) {
                    return remove(i);
                }
            }
        throw new NoSuchElementException("Element not found");
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
