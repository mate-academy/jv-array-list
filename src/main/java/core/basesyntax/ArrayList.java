package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int E_ARRAY_LENGTH_DEFAULT = 10;
    private static final double E_ARRAY_LENGTH_MULTIPLIER = 1.5;
    private Object[] elementsArray;
    private int size;

    ArrayList() {
        this.elementsArray = new Object[E_ARRAY_LENGTH_DEFAULT];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementsArray.length) {
            increaseEArrayLength();
        }
        elementsArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == elementsArray.length) {
            increaseEArrayLength();
        }
        System.arraycopy(elementsArray, index, elementsArray, index + 1, size - index);
        elementsArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((list != null) && (list.size() > 0)) {
            for (int i = 0; i < list.size(); i++) {
                this.add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndexForGetSetRemove(index);
        return (T) elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGetSetRemove(index);
        elementsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForGetSetRemove(index);
        final T removedValue = (T) elementsArray[index];
        if (index != (size - 1)) {
            System.arraycopy(elementsArray, index + 1, elementsArray, index, size - 1 - index);
        }
        elementsArray[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if ((elementsArray[i] == element)
                        || ((elementsArray[i] != null)
                            && elementsArray[i].equals(element))) {
                    return this.remove(i);
                }
            }
        }
        throw new NoSuchElementException(
                "Element for removing not found: " + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void increaseEArrayLength() {
        Object[] newElementsArr = Arrays.copyOf(elementsArray, calcNewLength());
        this.elementsArray = newElementsArr;
    }

    private int calcNewLength() {
        return (int) (elementsArray.length * E_ARRAY_LENGTH_MULTIPLIER);
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "IndexOutOfBounds: index: " + index
                    + ", Bounds: [0 - " + size + "(exclusively)"
            );
        }
    }

    private void checkIndexForGetSetRemove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "IndexOutOfBounds: index: " + index
                    + ", Bounds: [0 - " + size + "[inclusively]"
            );
        }
    }
}
