package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    static final Integer MIN_CAPACITY = 10;
    private T[] array;
    private Integer elementData = 0;

    public ArrayList() {
        this.array = (T[]) new Object[MIN_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow(1, this.array.length);
        this.array[elementData] = value;
        elementData += 1;
    }

    @Override
    public void add(T value, int index) {
        grow(1, this.array.length);
        if (index < elementData) {
            T[] cloneOfArray = this.array.clone();
            this.array[index] = value;
            elementData += 1;
            System.arraycopy(cloneOfArray, index, this.array, index + 1, elementData - index - 1);
        } else if (index == elementData) {
            this.add(value);
            elementData += 1;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + " Size: " + elementData);
        }
    }

    private void grow(int lengthOfElements, Integer length) {
        if (elementData + lengthOfElements > this.array.length) {
            T[] cloneOfArray = this.array.clone();
            this.array = (T[]) new Object[length + (length >> 1)];
            System.arraycopy(cloneOfArray, 0, this.array, 0, cloneOfArray.length);
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (this.array.length < elementData + list.size()) {
            grow(list.size(), this.array.length);
        }
        T[] bufferedArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bufferedArray[i] = list.get(i);
        }
        System.arraycopy(bufferedArray, 0, this.array, elementData, bufferedArray.length);
        elementData += bufferedArray.length;
    }

    @Override
    public T get(int index) {
        if (index < elementData) {
            return this.array[index];
        }
        throw new ArrayIndexOutOfBoundsException("Index: " + index + " Size: " + elementData);
    }

    @Override
    public void set(T value, int index) {
        if (index < elementData) {
            this.array[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        return this.array[cutting(index)];
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < elementData; i++) {
            if ((t == null && this.array[i] == null)
                    || this.array[i].equals(t)) {
                T result = array[i];
                cutting(i);
                return result;
            }
        }
        throw new NoSuchElementException();
    }

    private Integer cutting(int index) {
        if (index < elementData) {
            T[] bufferedMassive = this.array.clone();
            this.array = (T[]) new Object[this.array.length - 1];
            elementData -= 1;
            System.arraycopy(bufferedMassive, 0, this.array, 0, index);
            System.arraycopy(bufferedMassive, index + 1, this.array, index,
                    this.array.length - index - 1);
            return index;
        }
        throw new ArrayIndexOutOfBoundsException("Index: " + index + " Size: " + elementData);
    }

    @Override
    public int size() {
        return elementData;
    }

    @Override
    public boolean isEmpty() {
        return elementData == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < elementData; i++) {
            result.append(" " + this.array[i] + ",");
        }
        result.append("]");
        return result.toString();
    }
}
