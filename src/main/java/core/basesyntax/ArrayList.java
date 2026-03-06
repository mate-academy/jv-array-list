package core.basesyntax;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_NUMERATOR = 3;
    private static final int GROW_DENOMINATOR = 2;

    private Object[] elements;
    private int size;

    public ArrayList() {

        elements = new Object[DEFAULT_CAPACITY];
        size = 0;

    }

    private int calculateNewCapacity(int oldCapacity) {
        return oldCapacity * GROW_NUMERATOR / GROW_DENOMINATOR;
    }

    private void checkIndexForGetSetRemove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Invalid index: " + index + ", size: " + size);
        }

    }

    private void checkIndexForAdd(int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Invalid index: " + index + ", size: " + size);
        }

    }

    private void growIfNeeded() {

        if (size < elements.length) {
            return;
        }

        int oldCapacity = elements.length;
        int newCapacity = calculateNewCapacity(oldCapacity);

        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;

    }

    private int indexOf(T element) {

        for (int i = 0; i < size; i++) {
            T current = (T) elements[i];
            if (element == null) {
                if (current == null) {
                    return i;
                }
            } else {
                if (element.equals(current)) {
                    return i;
                }
            }
        }

        return -1;

    }

    @Override
    public void add(T value) {

        growIfNeeded();
        elements[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {

        checkIndexForAdd(index);
        growIfNeeded();

        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {

        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {

        checkIndexForGetSetRemove(index);
        return (T) elements[index];

    }

    @Override
    public void set(T value, int index) {

        checkIndexForGetSetRemove(index);
        elements[index] = value;

    }

    @Override
    public T remove(int index) {

        checkIndexForGetSetRemove(index);

        int elementsToMove = size - index - 1;
        final T removed = (T) elements[index];

        if (elementsToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsToMove);
        }
        elements[--size] = null;
        return removed;

    }

    @Override
    public T remove(T element) {

        int index = indexOf(element);
        if (index == -1) {
            throw new java.util.NoSuchElementException("Element not found: " + element);
        }
        return remove(index);

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
