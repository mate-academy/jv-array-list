package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static int INITIAL_SIZE = 10;

    private Object[] array = new Object[INITIAL_SIZE];
    private int numberOfElements = 0;

    @Override
    public void add(T value) {
        checkWhetherResize();
        array[numberOfElements++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > numberOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        checkWhetherResize();
        System.arraycopy(array, index, array, index + 1, numberOfElements - 1);
        numberOfElements++;
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > array.length - numberOfElements) {
            resize(array.length * 3 / 2);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        exceptionCheck(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        exceptionCheck(index);
        Object removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, numberOfElements - 1);
        numberOfElements--;
        return (T) removedElement;
    }

    @Override
    public T remove(T t) {
        Object removedElement;
        for (int i = 0; i < numberOfElements; i++) {
            if (array[i] == null ? array[i] == t : array[i].equals(t)) {
                removedElement = array[i];
                System.arraycopy(array, i + 1, array, i, numberOfElements - 1);
                numberOfElements--;
                return (T) removedElement;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < numberOfElements; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, numberOfElements);
        array = newArray;
    }

    private void exceptionCheck(int index) {
        if (index < 0 || index >= numberOfElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkWhetherResize() {
        if (numberOfElements == array.length - 1) {
            resize(array.length * 3 / 2);
        }
    }
}
