package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EMPTY_ELEMENT_DATA = 0;
    private static final int INDEX_ZERO = 0;
    private int size = 0;
    private T[] values;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int newCapacity) {
        if (newCapacity > 0) {
            this.values = (T[]) new Object[newCapacity];
        } else if (newCapacity == 0) {
            this.values = (T[]) new Object[EMPTY_ELEMENT_DATA];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't create array with capacity"
                    + " less than 0, but your" + newCapacity);
        }
    }

    @Override
    public void add(T value) {
        if (size + 1 == values.length) {
            values = growIfNecessary();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't add "
                    + "element by index less than 0. Your index = " + index);
        } else if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("You can't add "
                    + "element by index more than size of List. "
                    + "Your index = " + index + ". Capacity = " + size);
        }

        if ((size + 1) > values.length) {
            values = growIfNecessary();
        } else if (values[index] != null) {
            T tempValue = values[index];
            T tempValueNext = values[index + 1];
            add(values[size - 1]);
            set(value,index);
            for (int i = index + 1; i < size; i++) {
                set(tempValue,i);
                tempValue = tempValueNext;
                tempValueNext = values[i + 1];
            }
        } else {
            add(value);
        }
    }

    @Override
    public void addAll(List<T> list) {
        int length = list.size();
        for (int i = 0; i < length; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("You can't get "
                    + "element by this index = " + index + " Size is " + size);
        }

    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't set " + value
                    + " by index less than 0. Your index " + index);
        } else if (index < size) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You can't set " + value
                    + " by index more than size. Size = "
                    + size + ". Your index " + index);
        }

    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't remove element "
                    + "by index less than 0. Your index = " + index);
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("You can't remove element"
                    + " by index more than " + size + ". Your index = " + index);
        } else {
            T result = values[index];
            for (int i = index + 1; i < size; i++) {
                set(values[i],i - 1);
            }
            size--;
            return result;
        }
    }

    @Override
    public T remove(T element) {
        if (size == 0) {
            throw new NoSuchElementException("You cant remove "
                    + element + " by not exiting index");
        }
        for (int i = 0; i < size; i++) {
            if ((get(i) == element) || (get(i) != null && get(i).equals(element))) {
                T result = remove(i);
                return result;
            }
        }
        throw new NoSuchElementException("You can't delete non existent value = " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] growIfNecessary() {
        int newCapacity = values.length + (values.length >> 1);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(values, INDEX_ZERO, newArray, INDEX_ZERO, size);
        return newArray;
    }
}
