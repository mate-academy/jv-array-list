package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int indexOfLastAddedElement = 0;
    private Object[] array = new Object[10];

    @Override
    public void add(T value) {
        if (indexOfLastAddedElement >= array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5));
        }
        array[indexOfLastAddedElement] = value;
        indexOfLastAddedElement++;
    }

    @Override
    public void add(T value, int index) {
        if (index > indexOfLastAddedElement || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + indexOfLastAddedElement);
        } else {
            if (indexOfLastAddedElement == array.length) {
                array = Arrays.copyOf(array, (int) (array.length * 1.5));
            }
            Object[] secondPart = Arrays.copyOfRange(array, index, indexOfLastAddedElement);
            array[index] = value;
            indexOfLastAddedElement++;
            for (int i = index + 1, q = 0; i < indexOfLastAddedElement; i++, q++) {
                array[i] = secondPart[q];
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (indexOfLastAddedElement + list.size() >= array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5));
        }
        for (int i = indexOfLastAddedElement, q = 0; q < list.size(); i++, q++) {
            array[i] = list.get(q);
            indexOfLastAddedElement++;
        }
    }

    @Override
    public T get(int index) {
        if (indexOfLastAddedElement <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + indexOfLastAddedElement);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (indexOfLastAddedElement <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + indexOfLastAddedElement);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (indexOfLastAddedElement <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + indexOfLastAddedElement);
        }
        T oldValue = (T) array[index];
        Object[] secondPart = Arrays.copyOfRange(array, index + 1, indexOfLastAddedElement);
        indexOfLastAddedElement--;
        for (int i = index, q = 0; i < indexOfLastAddedElement; i++, q++) {
            array[i] = secondPart[q];
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue = null;
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                oldValue = (T) array[i];
                remove(i);
                counter++;
                break;
            }
        }
        if (counter == 0) {
            throw new NoSuchElementException("no such element in List");
        }
        return oldValue;
    }

    @Override
    public int size() {
        return indexOfLastAddedElement;
    }

    @Override
    public boolean isEmpty() {
        return indexOfLastAddedElement <= 0;
    }
}
