package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] arrayList;
    private int size;
    private final int defaultSize = 10;

    public ArrayList() {
        arrayList = new Object[defaultSize];
        size = 0;
    }

    public void ensureCapacity() {
        if (size == arrayList.length) {
            //arrayList = Arrays.copyOf(arrayList, (int) Math.ceil(arrayList.length * 1.5));
            arrayList = Arrays.copyOf(arrayList, arrayList.length + (arrayList.length >> 1));
        }
    }

    public void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > arrayList.length) {
            //arrayList = Arrays.copyOf(arrayList, arrayList.length + sizeOfList);
            //arrayList = Arrays.copyOf(arrayList, arrayList.length + (arrayList.length >> 1));

            int newCapacity = Math.max((int) (arrayList.length * 1.5), requiredCapacity);
            arrayList = Arrays.copyOf(arrayList, newCapacity);
        }
    }

    public void ensureIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    public void ensureIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();

        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureIndexForAdd(index);
        ensureCapacity();

        Object[] newArray = new Object[arrayList.length + 1];
        System.arraycopy(arrayList, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(arrayList, index, newArray, index + 1, arrayList.length - index);

        arrayList = newArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            arrayList[size + i] = list.get(i);
        }

        size += list.size();
    }

    @Override
    public T get(int index) {
        ensureIndex(index);

        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        ensureIndex(index);

        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        ensureIndex(index);
        final T removedElement = (T) arrayList[index];

        Object[] newArray = new Object[arrayList.length - 1];
        System.arraycopy(arrayList, 0, newArray, 0, index);
        System.arraycopy(arrayList, index + 1, newArray, index, newArray.length - index);

        arrayList = newArray;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        boolean isExist = false;
        int indexOfElement = 0;
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] == null ? element == null : arrayList[i].equals(element)) {
                isExist = true;
                indexOfElement = i;
                break;
            }
        }
        if (!isExist) {
            throw new NoSuchElementException("There is no such element");
        }

        final T removedElement = (T) arrayList[indexOfElement];
        Object[] newArray = new Object[arrayList.length - 1];

        for (int i = 0; i < arrayList.length; i++) {
            if (i != indexOfElement) {
                int newIndex = i < indexOfElement ? i : i - 1;
                newArray[newIndex] = arrayList[i];
            }
        }
        arrayList = newArray;
        size--;
        return removedElement;
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
