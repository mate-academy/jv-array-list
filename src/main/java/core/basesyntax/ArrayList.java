package core.basesyntax;

public class ArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLIER = 1.5;
    private static final String MESSAGE_OUT_OF_BOUNDS = "The index passed to any of "
            + "the methods is invalid";
    private static final String MESSAGE_NO_ELEMENT = "There is no such element";

    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T value) {
        int indexOfValue = indexOf(value);
        validateIndexForAdd(indexOfValue);
        increaseCapacityIfFull();
        elements[size++] = (T) value;
        return true;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        increaseCapacityIfFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(MyList<T> list) {
        while (size + list.size() > elements.length) {
            grow();
        }
        for (int i = 0; i < size; i++) {
            add(get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T oldValue = (T) elements[index];
        System.arraycopy(elements,index + 1, elements, index, size - index - 1);
        elements[size--] = null;
        return oldValue;
    }

    @Override
    public T remove(Object element) {
        int indexOfElement = indexOf(element);
        if (indexOfElement == -1) {
            throw new NoSuchElementException(MESSAGE_NO_ELEMENT);
        } else {
            System.arraycopy(elements, indexOfElement + 1, elements, indexOfElement,
                    size - indexOfElement - 1);
            elements[size--] = null;
            return (T) element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_OUT_OF_BOUNDS + index);
        }
    }

    public void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_OUT_OF_BOUNDS + index);
        }
    }

    public void increaseCapacityIfFull() {
        if (size >= elements.length) {
            grow();
        }
    }

    public void grow() {
        int newCapasity = (int) (elements.length * MULTIPLIER);
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[newCapasity];
        System.arraycopy(elements, 0, newArray, 0, size());
        elements = newArray;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        throw new ArrayListIndexOutOfBoundsException(MESSAGE_OUT_OF_BOUNDS);
    }
}
