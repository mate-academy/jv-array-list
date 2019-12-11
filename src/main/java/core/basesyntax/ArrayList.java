package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int counter;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[counter] = value;
        counter++;
    }

    @Override
    public void add(T value, int index) {
        if (index > array.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object[] tempArr = new Object[array.length + 1];
        System.arraycopy(array, 0, tempArr, 0, index);
        tempArr[index] = value;
        System.arraycopy(array, index, tempArr, index + 1, array.length - index);
        array = tempArr;
        counter++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ensureCapacity();
            array[counter] = list.get(i);
            counter++;
        }
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > array.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object[] tempArr = new Object[array.length - 1];
        System.arraycopy(array, 0, tempArr, 0, index);
        System.arraycopy(array, index + 1, tempArr, index, array.length - index - 1);
        Object oldObj = array[index];
        array = tempArr;
        counter--;
        return (T) oldObj;
    }

    @Override
    public T remove(T t) {
        boolean contains = false;
        int index = 0;
        Object oldObj = null;
        for (int i = 0; i < array.length; i++) {
            if (t != null && array[i] != null && t.equals(array[i])
                    || t == array[i]) {
                index = i;
                oldObj = t;
                contains = true;
            }
        }
        if (contains) {
            Object[] tempArr = new Object[array.length - 1];
            System.arraycopy(array, 0, tempArr, 0, index);
            System.arraycopy(array, index + 1, tempArr, index, array.length - index - 1);
            array = tempArr;
            counter--;
        } else {
            throw new NoSuchElementException();
        }
        return (T) oldObj;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        for (Object element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }

    private void ensureCapacity() {
        if (counter > array.length - 1) {
            array = grow(counter);
        } else if (counter < array.length - 1) {
            array = trim(counter);
        }
    }

    private Object[] grow(int initialCapacity) {
        int newCapacity = (initialCapacity + 1);
        Object[] newArr = Arrays.copyOf(array, newCapacity);
        return newArr;
    }

    private Object[] trim(int initialCapacity) {
        int newCapacity = initialCapacity + 1;
        Object[] newArr = Arrays.copyOf(array, newCapacity);
        return newArr;
    }

}
