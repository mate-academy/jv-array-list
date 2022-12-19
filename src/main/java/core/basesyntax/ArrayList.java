package core.basesyntax;

import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity <= 0");
        } else {
            elementData = (T[]) new Object[capacity];
        }
    }

    @Override
    public void add(T value) {
        // TODO проверка нра увеличение размера массива и копирование х1.5
        if (size == elementData.length) {
            elementData = grow(elementData);
        }

        // проверка на переполнение, если полный, то пересоздать массив и изменить capacity х1.5
        elementData[size++] = value;
    }

    private T[] grow(T[] oldList) {
        //todo (elementData.length * 1.5)
        T[] newList = (T[]) new Object[10];
        if (size >= 0) System.arraycopy(oldList, 0, newList, 0, size);
        return newList;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index must be equal to or greater than 0 and not greater than the size (actual size = " + size + " of the array.");
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newCapacity = size + list.size();
        T[] newList;
        if (elementData.length < newCapacity) {
            newList = (T[]) new Object[(int) (newCapacity * 1.5)];
        } else {
            newList = (T[]) new Object[elementData.length];
        }
        for (int i = 0; i < size; i++) {
            newList[i] = elementData[i];
        }
        for (int i = size; i < newCapacity; i++) {
            newList[i] = list.get(i - size);
        }
        elementData = (T[]) new Object[(int) (newCapacity * 1.5)];
        for (int i = 0; i < newCapacity; i++) {
            elementData[i] = newList[i];
        }
        size = newCapacity;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index must be equal to or greater than 0 and not greater than the size (actual size = + " + size + " of the array.");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index must be equal to or greater than 0 and not greater than the size (actual size = + " + size + " of the array.");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index must be equal to or greater than 0 and not greater than the size (actual size = + " + size + " of the array.");
        }
        // TODO заменить Objects
        Objects.checkIndex(index, size);
        T oldValue = (T) elementData[index];
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        // size = 8
        // i = 6
        // j = i - 1 = 5
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                for (int j = i - 1; i < size; i++) {
                    elementData[j] = elementData[j + 1];
                }
            }
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
