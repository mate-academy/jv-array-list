package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 * Resize the array in a separate method.
 *array growth should happen when it is full, and it should grow for 1,5 from the current size).
 * Use System.arraycopy to move your array elements.
 *Default capacity of the array should be imaged as a constant field.
 */
public class ArrayList<T> implements List<T> {
    transient Object[] elementData;
    private int size;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private static final int DEFAULT_CAPACITY = 10;


    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Please enter positive value");
        } else if (initialCapacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            elementData = new Object[initialCapacity];
        }
    }

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        if(size >= MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError("out of memory");
        }
        if(size == elementData.length) {
            int newCapacity = 0;
            if (size == elementData.length) {
                newCapacity = elementData.length + (elementData.length >> 1 +1);
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void rangeCheck (int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + "out of bound");
        }
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        //add elem on the last place - ne perezatiraet
        if (index == size) {
            add(value);
        }
        rangeCheck(index);
        resize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        if(index > elementData.length) {
            throw new IndexOutOfBoundsException("Put correct index");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        //petezatiraet
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T value = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        size--;
        return value;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], t)) {
                remove(i);
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
