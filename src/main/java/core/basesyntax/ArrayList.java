package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = DEFAULT_CAPACITY;
    private int currentSize = 0;
    T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[size];
    }

    private T[] resizeTheArray(T[] array) {
        int newSize = (size * 3) / 2 + 1;
        T[] resultArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, resultArray, 0, size);
        size = newSize;
        return resultArray;
    }

    @Override
    public void add(T value) {
        if (currentSize >= size) {
            elementData = resizeTheArray(elementData);
        }
        elementData[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (currentSize >= size) {
            elementData = resizeTheArray(elementData);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size);
        elementData[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        while(list.size() < size) {
            elementData = resizeTheArray(elementData);
        }

    }

    @Override
    public T get(int index) {
        for (int i = 0; i < currentSize; i++) {
            if (i == index) {
                return elementData[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        for (int i = 0; i < currentSize; i++) {
            if (index == i) {
                elementData[i] = value;
                return;
            }
        }
    }

    @Override
    public T remove(int index) {
        T cup = elementData[index];
        System.arraycopy(elementData, index, elementData, index - 1, size);
        currentSize--;
        elementData[currentSize] = null;
        return cup;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (elementData[i].equals(t)) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
