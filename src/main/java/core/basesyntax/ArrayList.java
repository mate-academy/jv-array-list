package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    private int maxCapacity;
    private int size;
    private Object[] inner;

    public ArrayList() {
        this.inner = new Object[DEFAULT_CAPACITY];
        this.maxCapacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        checkIfGrowingIsNeed();

        inner[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throwExceptionIfInvalidIndex("Can't add element to index "
                    + index);
        }

        if (index == size) {
            add(value);
            return;
        }

        Object[][] separated = splitInnerArrayByIndex(index);
        Object[] prePart = separated[0];
        Object[] postPart = separated[1];

        Object[] result = new Object[prePart.length + postPart.length + 1];
        System.arraycopy(prePart, 0, result, 0, prePart.length);
        result[index] = value;
        System.arraycopy(postPart, 0, result, index + 1, postPart.length);

        inner = result;
        size++;
        maxCapacity = inner.length;
    }

    @Override
    public void addAll(List<T> addList) {
        for (int i = 0; i < addList.size(); i++) {
            add(addList.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return (T) inner[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        inner[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        final T deletedElement = (T) inner[index];

        Object[][] separated = splitInnerArrayByIndex(index);
        Object[] prePart = separated[0];
        Object[] postPart = separated[1];

        Object[] result = new Object[prePart.length + postPart.length - 1];
        System.arraycopy(prePart, 0, result, 0, prePart.length);
        System.arraycopy(postPart, 1, result, index, postPart.length - 1);

        inner = result;
        size--;
        maxCapacity = inner.length;

        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int deletedElementIndex = ELEMENT_NOT_FOUND;

        for (int i = 0; i < size; i++) {
            if (element == inner[i]
                    || (element != null && element.equals(inner[i]))) {
                deletedElementIndex = i;
                break;
            }
        }

        if (deletedElementIndex == ELEMENT_NOT_FOUND) {
            throw new NoSuchElementException("Can't find"
                    + element
                    + " element");
        }

        T deletedElement = get(deletedElementIndex);

        remove(deletedElementIndex);

        return deletedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIfGrowingIsNeed() {
        if (maxCapacity == size) {
            grow();
        }
    }

    private void grow() {
        maxCapacity = maxCapacity + (maxCapacity >> 1);
        Object[] newInner = new Object[maxCapacity];
        System.arraycopy(inner, 0, newInner, 0, size);

        inner = newInner;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throwExceptionIfInvalidIndex("Can't reach element with index "
                    + index
                    + ". You need to add it first, or change the index "
                    + "to correct one.");
        }
    }

    private void throwExceptionIfInvalidIndex(String message) {
        throw new ArrayListIndexOutOfBoundsException(message);
    }

    private Object[][] splitInnerArrayByIndex(int index) {
        Object[] prePart = new Object[index];
        Object[] postPart = new Object[size - index];

        System.arraycopy(inner, 0, prePart, 0, index);
        System.arraycopy(inner, index, postPart, 0, size - index);

        return new Object[][]{
                prePart, postPart
        };
    }
}
