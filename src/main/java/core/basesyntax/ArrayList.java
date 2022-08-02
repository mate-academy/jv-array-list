package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VALUE = 10;
    private int elementsInArray = 0;
    private Object[] array = new Object[DEFAULT_VALUE];

    public ArrayList(T value) {
    }

    public ArrayList() {
    }

    @Override
    public void add(T value) {
        int countOfElements = size();
        if (countOfElements < array.length) {
            array[countOfElements] = value;
            elementsInArray++;
        } else {
            growArray(countOfElements);
            array[countOfElements] = value;
            elementsInArray++;
        }
    }

    @Override
    public void add(T value, int index) {
        int countOfElements = size();
        if (index < 0 || index > countOfElements) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else if (index == countOfElements) {
            add(value);
        } else {
            if (countOfElements + 1 <= array.length) {
                addInside(index, value);
            } else {
                growArray(countOfElements);
                addInside(index, value);
            }
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
        if (indexInRange(index)) {
            return (T) array[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (indexInRange(index)) {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (indexInRange(index)) {
            Object removedElement = array[index];
            if (index + 1 != size()) {
                System.arraycopy(array,index + 1,array,index, size() - index);
                elementsInArray--;
            } else {
                elementsInArray--;
            }
            return (T) removedElement;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if ((element == array[i]) || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return elementsInArray;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    private void growArray(int countOfElements) {
        int newArrayLength = newLength(countOfElements);
        Object [] tempArray = new Object[newArrayLength];
        for (int i = 0; i < countOfElements; i++) {
            tempArray[i] = array[i];
        }
        array = new Object[newArrayLength];
        for (int i = 0; i < countOfElements; i++) {
            array[i] = tempArray[i];
        }
    }

    private int newLength(int oldLength) {
        if (oldLength == 1) {
            return 2;
        }
        return oldLength * 3 / 2;
    }

    private void addInside(int index, T value) {
        Object[] tempArray = new Object[array.length - index - 1];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = array[index + i];
        }
        array[index] = value;
        for (int i = 0; i < tempArray.length; i++) {
            array[index + 1 + i] = tempArray[i];
        }
        elementsInArray++;
    }

    private boolean indexInRange(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return true;
    }
}
