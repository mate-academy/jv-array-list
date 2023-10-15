package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_MULTIPLIER = 1.5;
    private T[] arrayList;
    private int size;
    private final String message = "Index should be in the range 0 to ";
    private final String message2 = "Elements do not exist";
    private final String message3 = "Capacity can't be less than 0";

    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new ArrayListIndexOutOfBoundsException(message3);
        }
        this.arrayList = (T[]) new Object [capacity];
    }

    @Override
    public void add(T value) {
        grow();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        isCorrectIndex(index, size);
        grow();
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isCorrectIndex(index, size - 1);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        isCorrectIndex(index, size - 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        isCorrectIndex(index, size - 1);
        T removedObject = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i] || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(message2 + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        if (size == arrayList.length) {
            arrayList = Arrays.copyOf(arrayList,
                    (int) Math.round(arrayList.length * RESIZE_MULTIPLIER));
        }
        return arrayList;
    }

    private void isCorrectIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(message + size);
        }
    }
}
