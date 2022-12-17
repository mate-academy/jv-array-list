package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASE_ARRAY_LENGTH = 10;
    private static final double ARRAY_INCREMENT_VALUE = 1.5;
    private T[] array;
    private int currentSlot;

    public ArrayList() {
        array = (T[]) new Object[BASE_ARRAY_LENGTH];
        currentSlot = -1;
    }

    @Override
    public void add(T value) {
        checkAndIncreaseArrayLength();
        currentSlot++;
        array[currentSlot] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > currentSlot + 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds ");
        }
        checkAndIncreaseArrayLength();
        currentSlot++;
        for (int i = size() - 1; i >= (index == 0 ? index + 1 : index); i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        checkAndIncreaseArrayLength(list.size());
        for (int i = 0; i < list.size(); i++) {
            currentSlot++;
            array[currentSlot] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > currentSlot) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds ");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > currentSlot) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds ");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds");
        }
        T date = array[index];
        for (int i = index; i < size(); i++) {
            if (index == currentSlot) {
                array[index] = null;
                currentSlot--;
                return date;
            }
            array[i] = array[i + 1];
        }
        currentSlot--;
        array[size()] = null;
        return date;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException("Found element doesn't exist");
        }
        int indexFoundElement = -1;
        search:
        for (int i = 0; i < size(); i++) {
            if (array[i] == null || element == null) {
                indexFoundElement = i;
                break search;
            }
            if (array[i] == element || array[i].equals(element)) {
                indexFoundElement = i;
                break search;
            }
        }
        if (indexFoundElement == -1) {
            throw new NoSuchElementException("Found element doesn't exist");
        } else {
            for (int i = indexFoundElement; i < size(); i++) {
                if (indexFoundElement == size() - 1) {
                    array[indexFoundElement] = null;
                    currentSlot--;
                    return element;
                }
                array[i] = array[i + 1];
            }
            array[currentSlot] = null;
            currentSlot--;
        }
        return element;
    }

    @Override
    public int size() {
        if (array == null) {
            return 0;
        }
        return currentSlot + 1;
    }

    @Override
    public boolean isEmpty() {
        if (currentSlot > -1) {
            return false;
        }
        return true;
    }

    private void checkAndIncreaseArrayLength() {
        T[] tempArray = null;
        if (array[array.length - 1] != null) {
            tempArray = (T[]) new Object[(int) (array.length * ARRAY_INCREMENT_VALUE)];
            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }
    }

    private void checkAndIncreaseArrayLength(int newArrayLength) {
        if (array.length - (currentSlot + 1) - newArrayLength < 0) {
            T[] tempArray = null;
            int numberMissingSlots = (array.length - (currentSlot + 1) - newArrayLength) * (-1);
            int tempArrLength = (int) ((numberMissingSlots + array.length) * ARRAY_INCREMENT_VALUE);
            tempArray = (T[]) new Object[tempArrLength];
            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        if (isEmpty()) {
            return "[]";
        }
        for (int i = 0; i < size(); i++) {
            data.append(array[i]).append(", ");
        }
        return '[' + data.toString().substring(0, data.length() - 2) + ']';
    }
}
