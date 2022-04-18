package core.basesyntax;

import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] arrays = new Object[10];
    private int size;

    @Override
    public void add(T value) {
        if (size == arrays.length) {
            grow();
        }
        arrays[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        validate(index, 0, size + 1);
        Object[] firstCopyArray = Arrays.copyOfRange(arrays, 0, index + 1);
        firstCopyArray[index] = value;
        Object[] secondCopyArray = Arrays.copyOfRange(arrays, index, arrays.length);
        size++;
        arrayConcat(firstCopyArray,secondCopyArray);

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validate(index, 0, size - 1);
        return (T) arrays[index];
    }

    @Override
    public void set(T value, int index) {
        validate(index, 0, size - 1);
        Object[] firstCopyArray = Arrays.copyOfRange(arrays, 0, arrays.length);
        firstCopyArray[index] = value;
        arrays = firstCopyArray;
    }

    @Override
    public T remove(int index) {
        validate(index, 0, size - 1);
        T oldValue = (T) arrays[index];
        Object[] firstCopyArray = Arrays.copyOfRange(arrays, 0, index);
        Object[] secondCopyArray = Arrays.copyOfRange(arrays, index + 1, arrays.length);
        size--;
        arrayConcat(firstCopyArray, secondCopyArray);

        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equal(arrays[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newArrayLength = (int) (arrays.length * 1.5);
        arrays = Arrays.copyOf(arrays, newArrayLength);
    }

    private void validate(int index, int lowerBound, int upperBound)
            throws ArrayListIndexOutOfBoundsException {
        if (index < lowerBound || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Something went wrong...");
        }
    }

    private Object[] arrayConcat(Object[] firstCopyArray, Object[] secondCopyArray) {
        arrays = Arrays.copyOf(firstCopyArray, firstCopyArray.length + secondCopyArray.length);
        System.arraycopy(secondCopyArray, 0, arrays, firstCopyArray.length, secondCopyArray.length);
        return arrays;
    }
}
