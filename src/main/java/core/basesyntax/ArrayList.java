package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + capacity
                    + " out of bounds for size " + size);
        } else {
            elementData = new Object[capacity];
        }

    }

    private void resizeCapacity() {
        if (size == elementData.length) {
            int newCapacity = (elementData.length == 0)
                    ? DEFAULT_CAPACITY : (int) (elementData.length * GROWTH_FACTOR);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    // get, set, remove
    private void checkElementAccessIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    // add (index, value)
    private void checkElementAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    @Override
    public void add(T value) {
        resizeCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkElementAddIndex(index);
        resizeCapacity();
        // Сдвиг елементов вправо
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
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
        checkElementAccessIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementAccessIndex(index);
        elementData[index] = value;

    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkElementAccessIndex(index);

        T removedValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
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
            throw new NoSuchElementException(String.valueOf(element));
        }

        // Сдвигаем элементы после найденного
        System.arraycopy(elementData, foundIndex + 1, elementData, foundIndex,
                size - foundIndex - 1);
        // Очищаем последний элемент
        elementData[size - 1] = null;

        size--;
        // Уменьшаем размер
        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Object[] actualElements = new Object[size];
        System.arraycopy(elementData, 0, actualElements, 0, size);
        sb.append("[");
        for (Object element : actualElements) {
            sb.append(element).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

}
