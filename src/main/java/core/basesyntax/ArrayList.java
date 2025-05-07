package core.basesyntax;

import edu.emory.mathcs.backport.java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elementData;
    private int size;
    private int maxSize = 10;

    @Override
    public void add(T value) {
        if (elementData == null) {
            elementData = new Object[DEFAULT_SIZE];
            elementData[0] = value;
            size++;
        } else {
            size++;
            if (size > maxSize) {
                grow();
            }
            elementData[size - 1] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        } else if (index + 1 == size) {
            if (size >= maxSize) {
                grow();
            }
            Object[] secondPartOfArray = new Object[size - index];
            System.arraycopy(elementData, index, secondPartOfArray, 0, size - index);
            elementData[index] = value;
            System.arraycopy(secondPartOfArray, 0, elementData, index + 1, size - index);
            size++;
        } else {
            if (size == maxSize) {
                grow();
            }
            if (size != 0) {
                Object[] secondPartOfArray = new Object[size - index];
                System.arraycopy(elementData, index, secondPartOfArray, 0, size - index);
                elementData[index] = value;
                System.arraycopy(secondPartOfArray, 0, elementData, index + 1, size - index);
                size++;
            } else {
                add(value);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound" + index);
        } else {
            return (T) elementData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T t;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        } else {
            t = (T) elementData[index];
            Object[] secondPartOfArray = new Object[size - 1 - index];
            System.arraycopy(elementData, index + 1, secondPartOfArray, 0, size - 1 - index);
            System.arraycopy(secondPartOfArray, 0, elementData, index, secondPartOfArray.length);
            size--;
            return t;
        }
    }

    @Override
    public T remove(T element) {
        T t;
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(element, elementData[i])) {
                t = (T) elementData[i];
                Object[] secondPartOfArray = new Object[size - 1 - i]; // зверху index???
                System.arraycopy(elementData, i + 1, secondPartOfArray, 0, size - 1 - i);
                System.arraycopy(secondPartOfArray, 0, elementData, i, secondPartOfArray.length);
                size--;
                return t;
            }
        }
        throw new NoSuchElementException("No such element");
        //return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        maxSize = (int) (size * 1.5);
        elementData = Arrays.copyOf(elementData, maxSize);
    }
}
