package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_INDEX = 1.5;
    private static final int INITIAL_CAPACITY = 10;
    private static final String ERROR_ELEMENT_NOT_FOUND_MESSAGE = "No such element found";
    private static final String ERROR_INDEX_OUTSIDE_ARRAY_MESSAGE = "Index outside array";
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

        System.arraycopy(elements, index, elements,
                index + 1, numberOfElements - index);
        elements[index] = value;
        numberOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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
        final T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index,
                numberOfElements - index - 1);
        elements[numberOfElements - 1] = null;
        numberOfElements--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        remove(index);
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
            if (element == elements[i]
                    || (elements[i] != null && elements[i].equals(element))) {
                return i;
            }
        }
        throw new java.util.NoSuchElementException(ERROR_ELEMENT_NOT_FOUND_MESSAGE);
    }

    private void checkCapacity(int newSize, int capacity) {
        if (newSize > capacity) {
            while (newSize > capacity) {
                capacity = (int) (capacity * GROWTH_INDEX);
            }
            Object[] newElements = new Object[capacity];
            System.arraycopy(elements, 0, newElements, 0, numberOfElements);
            elements = newElements;
        }
    }

    private void rangeIndexCheck(int index) {
        if (index >= numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_INDEX_OUTSIDE_ARRAY_MESSAGE);
        }
    }
}
