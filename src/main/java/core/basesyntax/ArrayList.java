package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity = DEFAULT_CAPACITY;
    private int size = 0;
    private int currentPosition = 0;

    private Object[] objArray;

    public ArrayList() {
        objArray = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow() {
        int oldCapacity = objArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        currentCapacity = newCapacity;
        Object[] growed = new Object[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            growed[i] = objArray[i];
        }
        return growed;
    }

    private Object[] toArray(List<T> list) {
        int size = list.size();
        Object[] listToArr = new Object[size];
        for (int i = 0; i < size; i++) {
            listToArr[i] = list.get(i);
        }
        return listToArr;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == currentCapacity) {
            objArray = grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index < size) {
            int sizeOfTempArr = size - index;
            int switchTemp = -1;
            Object[] temporary = new Object[sizeOfTempArr];
            for (int i = index; i < size; i++) {
                temporary[++switchTemp] = objArray[i];
            }
            objArray[index] = value;
            switchTemp = -1;
            for (int i = index + 1; i <= size; i++) {
                objArray[i] = temporary[++switchTemp];
            }
            size++;
            return;
        }
        objArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] fromList = toArray(list);
        int listLength = fromList.length;
        int freeSpaceInObjArr = currentCapacity - size;
        /*while (freeSpaceInObjArr < listLength) {
            grow();
            freeSpaceInObjArr = currentCapacity - size;
        }*/
        for (Object obj : fromList) {
            add((T) obj);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) objArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        objArray[index] = value;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
        final T removedItem = (T) objArray[index];
        int sizeOfTempArr = size - index - 1;
        int switchTemp = -1;
        Object[] temporary = new Object[sizeOfTempArr];
        for (int i = index + 1; i < size; i++) {
            temporary[++switchTemp] = objArray[i];
        }
        switchTemp = -1;
        for (int i = index; i <= size - 2; i++) {
            objArray[i] = temporary[++switchTemp];
        }
        size--;
        objArray[size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < objArray.length; i++) {
            if (element != null && objArray[i] == null
                    || element == null && objArray[i] != null) {
                continue;
            }
            if ((element == null && objArray[i] == null)
                    || objArray[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("There no such element in array");
        }
        return remove(index);
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
