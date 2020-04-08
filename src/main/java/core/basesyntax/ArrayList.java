package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final Integer MIN_CAPACITY = 10;
    private T[] array;
    private int elementDat;

    public ArrayList() {
        this.array = (T[]) new Object[MIN_CAPACITY];
        this.elementDat = 0;
    }

    public ArrayList(int opacity) {
        this.array = (T[]) new Object[opacity];
        this.elementDat = 0;
    }

    @Override
    public void add(T value) {
        grow();
        this.array[this.elementDat] = value;
        this.elementDat += 1;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index < this.elementDat) {
            T[] cloneOfArray = this.array.clone();
            this.array[index] = value;
            this.elementDat += 1;
            System.arraycopy(cloneOfArray, index, this.array, index + 1,
                    this.elementDat - index - 1);
        } else if (index == this.elementDat) {
            this.add(value);
            this.elementDat += 1;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index: " + index
                    + " Size: " + this.elementDat);
        }
    }

    private void grow() {
        if (this.elementDat + 1 > this.array.length) {
            T[] cloneOfArray = this.array.clone();
            this.array = (T[]) new Object[this.array.length + (this.array.length >> 1)];
            System.arraycopy(cloneOfArray, 0, this.array, 0, cloneOfArray.length);
        }
    }

    @Override
    public void addAll(List<T> list) {
        T[] bufferedArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < this.elementDat) {
            return this.array[index];
        }
        throw new ArrayIndexOutOfBoundsException("Index: " + index + " Size: " + this.elementDat);
    }

    @Override
    public void set(T value, int index) {
        if (index >= this.elementDat) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index
                    + " Size: " + this.elementDat);
        }
        this.array[index] = value;
    }

    @Override
    public T remove(int index) {
        return this.array[cutting(index)];
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < this.elementDat; i++) {
            if ((t == null && this.array[i] == null)
                    || this.array[i].equals(t)) {
                T result = array[i];
                cutting(i);
                return result;
            }
        }
        throw new NoSuchElementException("No such element in array.");
    }

    private Integer cutting(int index) {
        if (index < this.elementDat) {
            T[] bufferedMassive = this.array.clone();
            this.array = (T[]) new Object[this.array.length - 1];
            this.elementDat -= 1;
            System.arraycopy(bufferedMassive, 0, this.array, 0, index);
            System.arraycopy(bufferedMassive, index + 1, this.array, index,
                    this.array.length - index - 1);
            return index;
        }
        throw new ArrayIndexOutOfBoundsException("Index: " + index + " Size: " + this.elementDat);
    }

    @Override
    public int size() {
        return this.elementDat;
    }

    @Override
    public boolean isEmpty() {
        return this.elementDat == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < this.elementDat; i++) {
            result.append(" " + this.array[i] + ",");
        }
        result.append("]");
        return result.toString();
    }
}
