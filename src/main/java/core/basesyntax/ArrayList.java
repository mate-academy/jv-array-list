package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() { // done
        array = (T[]) new Object[DEFAULT_CAPACITY];
        elementNumber = 0;
    }

    public ArrayList(int capacity) { //done
        array = (T[]) new Object[capacity];
        elementNumber = 0;
    }

    @Override
    public void add(T value) { //done
        arrayResize();
        array[elementNumber++] = value;
    }

    @Override //done
    public void add(T value, int index) {
        checkIndex(index);
        arrayResize();
        System.arraycopy(array, index, array, index + 1, elementNumber - index);
        elementNumber++;
    }

    @Override //done
    public void addAll(List<T> list) {
        arrayResize();
        for (int i = 0; i < list.size(); i++) {
            array[elementNumber++] = list.get(i);
        }
    }

    @Override //done
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override //done
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > elementNumber - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T[] secondPartArray = Arrays.copyOfRange(array, index + 1, array.length - 1);
        final T returning = array[index];
        array[index] = null;
        System.arraycopy(secondPartArray, 0, array, index, elementNumber - index);
        array[elementNumber--] = null;
        return returning;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < elementNumber; i++) {
            if ((t == array[i]) || t != null && t.equals(array[i])) {
                T returning = array[i];
                return remove(i);
                return returning;
            }
        }
        throw new NoSuchElementException();
    }

    @Override //done
    public int size() {
        return elementNumber;
    }

    @Override //done
    public boolean isEmpty() {
        return elementNumber == 0;
    }

    private void arrayResize() {
        if (elementNumber == array.length ) {
            array = Arrays.copyOf(array, array.length * 3 / 2 + 1);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= elementNumber) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
