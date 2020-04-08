package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private T[] arrayT;
    private int size;

    public ArrayList() {
        arrayT = grow(0);
        size = arrayT.length;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        T[] newArray = grow(size + 1);
        if (index <= size) {
            System.arraycopy(arrayT, 0, newArray, 0, index);
            newArray[index] = value;
            index++;
            System.arraycopy(arrayT, index - 1, newArray, index, size - index + 1);
            arrayT = newArray;
            size = newArray.length;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
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
        return arrayT[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayT[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            T res = arrayT[0];
            arrayT = grow(0);
            size--;
            return res;
        }
        T[] newArray = grow(size - 1);
        T result;
        System.arraycopy(arrayT, 0, newArray, 0, index);
        result = arrayT[index];
        System.arraycopy(arrayT, index + 1, newArray, index, size - 1 - index);
        arrayT = newArray;
        size = newArray.length;

        return result;
    }

    @Override
    public T remove(T t) {
        int index = indexOf(t);
        T[] newArray = grow(size - 1);
        T result;
        if (index < size) {
            System.arraycopy(arrayT, 0, newArray, 0, index);
            result = arrayT[index];
            System.arraycopy(arrayT, index + 1, newArray, index, size - 1 - index);
            arrayT = newArray;
            size--;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(int capacity) {
        return (T[]) new Object[capacity];
    }

    private int indexOf(T t) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayT[i] == t || arrayT[i] != null && arrayT[i].equals(t)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new java.util.NoSuchElementException();
        }
        return index;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
    }
}
