package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    ArrayList() {
        elements = (T[]) new Object[MAX_ITEMS_NUMBER];
        size = 0;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("such index doesn't exist");
        }
    }

    @SuppressWarnings("unchecked")
    public void grow() {
        int newSizeArray = (int) (elements.length * 1.5);
        T[] newElements = (T[]) new Object[newSizeArray];
        System.arraycopy(elements,0,newElements,0,size);
        elements = newElements;
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (elements.length == size) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(elements, index, elements,index + 1,size - index);
            elements[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("such index doesn't exist");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size();i++) {
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
        T element = null;
        element = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        elements[size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element == null
                    || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new java.util.NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }
}
