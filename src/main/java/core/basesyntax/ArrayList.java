package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = EMPTY_ELEMENTDATA;
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Please enter the "
                    + "positive length of an array");
        }
        elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            int newCapacity = (elementData.length == 0)
                    ? DEFAULT_CAPACITY : (int) (elementData.length * 1.5);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (size == elementData.length) {
            int newCapacity = (elementData.length == 0)
                    ? DEFAULT_CAPACITY : (int) (elementData.length * 1.5);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

        if (index >= 0 && index <= size) {
            for (int i = size; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Please enter the positive "
                    + "index of the element in the array");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {

        if (index >= 0 && index < size) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Please enter the "
                    + "index of the element in the array");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        T removedValue = null;
        if (index >= 0 && index < size) {
            size--;
            removedValue = (T) elementData[index];
            for (int j = index; j < size(); j++) {
                elementData[j] = elementData[j + 1];
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }

        return removedValue;

    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        T removedValue = null;
        int foundIndex = -1;

        // Проверка на null
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    removedValue = null;
                    foundIndex = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    removedValue = (T) elementData[i];
                    foundIndex = i;
                    break;
                }
            }
        }

        // Если элемент не найден — выбрасываем исключение
        if (foundIndex == -1) {
            throw new NoSuchElementException("Element not found");
        }

        // Сдвигаем элементы после найденного
        for (int j = foundIndex; j < size - 1; j++) {
            elementData[j] = elementData[j + 1];
        }

        // Очищаем последний элемент
        elementData[size - 1] = null;

        // Уменьшаем размер
        size--;

        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public String toString() {
        Object[] actualElements = Arrays.copyOf(elementData, size);
        return Arrays.toString(actualElements);
    }

}
