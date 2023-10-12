package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_INDEX = 1.5;
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int numberOfElements;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity(numberOfElements + 1, elements.length);
        elements[numberOfElements] = value;
        numberOfElements++;
    }

    @Override
    public void add(T value, int index) {
        checkCapacity(numberOfElements + 1, elements.length);
        if (index == numberOfElements) {
            elements[index] = value;
            numberOfElements++;
            return;
        } else if (index != 0) {
            rangeIndexCheck(index);
        }

        for (int i = numberOfElements; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        numberOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        checkCapacity(numberOfElements + list.size(), list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[numberOfElements++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        rangeIndexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeIndexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeIndexCheck(index);
        final T removedElement = (T)elements[index];

        for (int i = index + 1; i < numberOfElements; i++) {
            elements[i - 1] = elements[i];
        }
        elements[numberOfElements - 1] = null;
        numberOfElements--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        if (index != -1) {
            remove(index);
        } else {
            throw new java.util.NoSuchElementException("No such element found");
        }
        return element;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    private int findIndex(T element) {
        for (int i = 0; i < numberOfElements; i++) {
            if (element == null && elements[i] == null) {
                return i;
            }
            if (elements[i] != null && elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void checkCapacity(int newSize, int size) {
        if (newSize > size) {
            Object[] newElements = new Object[calculateCapacity(newSize, size)];
            System.arraycopy(elements, 0, newElements, 0, numberOfElements);
            elements = newElements;
        }
    }

    private int calculateCapacity(int newSize, int capacity) {
        while (newSize > capacity) {
            capacity = (int) (capacity * GROWTH_INDEX);
        }
        return capacity;
    }

    private void rangeIndexCheck(int index) {
        if (index >= numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " outside array");
        }
    }
}
