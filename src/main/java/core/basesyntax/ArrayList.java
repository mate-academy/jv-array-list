package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFOULT_SIZE = 10;
    private Object[] ownSet;
    private int currentLength;
    private int capacity;

    public ArrayList() {
        ownSet = (T[]) new Object[DEFOULT_SIZE];
        currentLength = 0;
        capacity = DEFOULT_SIZE;
    }

    public ArrayList(int size) {
        ownSet = (T[]) new Object[size];
        currentLength = 0;
        capacity = size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > currentLength) {
            throw new ArrayIndexOutOfBoundsException("wrong input idex");
        }
    }

    private void extendSpace() {
        if (size() >= capacity * 0.8d) {
            Object[] tempSet = Arrays.copyOf(ownSet, capacity * 2);
            ownSet = tempSet;
            capacity *= 2;
        }
    }

    @Override
    public void add(T value) {
        extendSpace();
        ownSet[currentLength] = value;
        currentLength++;
    }

    @Override
    public void add(T value, int index) {
        extendSpace();
        checkIndex(index);

        for (int i = size(); i > index; i--) {
            ownSet[i] = ownSet[i - 1];
        }
        ownSet[index] = value;
        currentLength++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) ownSet[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        ownSet[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        for (int i = index; i < size(); i++) {
            ownSet[i] = ownSet[i + 1];
        }
        currentLength--;
        return (T) ownSet[index];
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if (ownSet[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return currentLength;
    }

    @Override
    public boolean isEmpty() {
        return currentLength == 0;
    }
}
