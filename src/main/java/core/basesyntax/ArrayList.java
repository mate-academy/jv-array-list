package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;

    private T[] storedData;
    private int filledWithElements;

    public ArrayList() {
        storedData = (T[]) new Object[DEFAULT_CAPACITY];
        filledWithElements = 0;
    }

    public ArrayList(int initialCapacity) {
        storedData = (T[]) new Object[initialCapacity];
        filledWithElements = 0;
    }

    public void resizeIfNecessary() {
        if (filledWithElements == storedData.length) {
            T[] newArrayList = (T[]) new Object[(int) (storedData.length * 1.5)];
            System.arraycopy(storedData, 0, newArrayList, 0, filledWithElements);
            storedData = newArrayList;
        }
    }

    public void checkBounds(int index) {
        if (index < 0 || index >= filledWithElements) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bond oa an array");
        }
    }

    @Override
    public void add(T value) {
        resizeIfNecessary();
        storedData[filledWithElements++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == filledWithElements) {
            add(value);
            return;
        }
        checkBounds(index);
        resizeIfNecessary();
        System.arraycopy(storedData, index, storedData, index + 1, filledWithElements - index);
        storedData[index] = value;
        filledWithElements++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return storedData[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        storedData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T elementToRemove = storedData[index];
        System.arraycopy(storedData, index + 1, storedData, index, filledWithElements - index - 1);
        storedData[--filledWithElements] = null;
        return elementToRemove;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < filledWithElements; i++) {
            if (storedData[i] == t || (storedData[i] != null && storedData[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such an element is not present in array");
    }

    @Override
    public int size() {
        return filledWithElements;
    }

    @Override
    public boolean isEmpty() {
        return filledWithElements == 0;
    }
}
