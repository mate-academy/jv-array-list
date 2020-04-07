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
        T[] newArray = grow();
        System.arraycopy(arrayT, 0, newArray, 0, size);
        newArray[size] = value;
        arrayT = newArray;
        size = newArray.length;
    }

    @Override
    public void add(T value, int index) {
        T[] newArray = grow();
        if (index < size) {
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
        int newSize = size + list.size();
        T[] newArray = grow(newSize);
        if (size == 0) {
            for (int i = 0; i < list.size(); i++) {
                newArray[i] = list.get(i);
            }
        } else {
            System.arraycopy(arrayT, 0, newArray, 0, size - 1);
            for (int i = 0; i < list.size(); i++) {
                newArray[i + size] = list.get(i);
            }
        }
        arrayT = newArray;
        size = newSize;

    }

    @Override
    public T get(int index) {
        if (index < size) {
            return arrayT[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }

    }

    @Override
    public void set(T value, int index) {
        T[] newArray = grow(size);
        if (index < size) {
            System.arraycopy(arrayT, 0, newArray, 0, index);
            newArray[index] = value;
            index++;
            System.arraycopy(arrayT, index, newArray, index, size - index);
            arrayT = newArray;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            T res = arrayT[0];
            arrayT = grow(0);
            size--;
            return res;
        }
        T[] newArray = unGrow();
        T result;
        if (index < size) {
            System.arraycopy(arrayT, 0, newArray, 0, index);
            result = arrayT[index];
            System.arraycopy(arrayT, index + 1, newArray, index, size - 1 - index);
            arrayT = newArray;
            size = newArray.length;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
        return result;
    }

    @Override
    public T remove(T t) {
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
        T[] newArray = unGrow();
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

    public T[] grow() {
        return (T[]) new Object[size + 1];
    }

    public T[] grow(int capacity) {
        return (T[]) new Object[capacity];
    }

    public T[] unGrow() {
        return (T[]) new Object[size - 1];
    }
}
