package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int currentCapacity;
    private int currentIndex;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        addElementToArray(value, currentIndex);
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        sizeCheck();
        if (index < currentIndex) {
            arrayCopy(index, arrayList, index + 1, currentIndex + 1);
        }
        addElementToArray(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index + 1);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index + 1);
        sizeCheck();
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        arrayCopy(index + 1, arrayList, index, currentIndex - 1);
        currentIndex--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentIndex; i++) {
            if (t == arrayList[i] || (t != null && t.equals(arrayList[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element does not exist.");
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    public void addElementToArray(T value, int index) {
        sizeCheck();
        arrayList[index] = value;
        currentIndex++;
    }

    public void indexCheck(int index) {
        if (index > currentIndex) {
            throw new ArrayIndexOutOfBoundsException("Index above the size of Array.");
        }
    }

    public void sizeCheck() {
        if (currentIndex == currentCapacity) {
            currentCapacity = currentCapacity + (currentCapacity >> 1);
            Object[] arrayListTemporary = (T[]) new Object[currentCapacity];
            arrayCopy(0, (T[]) arrayListTemporary, 0, currentIndex);
            arrayList = (T[]) arrayListTemporary;
        }
    }

    public void arrayCopy(int indexFrom, T[] arrayDest, int indexTo, int indexLength) {
        System.arraycopy(arrayList, indexFrom, arrayDest, indexTo, indexLength);
    }
}

