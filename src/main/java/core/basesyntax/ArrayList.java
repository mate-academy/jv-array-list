package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE_ARRAY = 10;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE_ARRAY];
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = (T) value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index > size || index < 0 || array.length < index) {
            throw new ArrayListIndexOutOfBoundsException("such an index is unacceptable");
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        IntStream.range(0, list.size())
                .forEach(element -> add(list.get(element)));
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = findItIndex(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private int findItIndex(T element) {
        return IntStream.range(0, size)
                .filter(i -> element == get(i) || (get(i) != null && get(i).equals(element)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("not found element in array"));
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("such an index is unacceptable");
        }
    }
}
