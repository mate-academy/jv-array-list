package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private Object[] elementData;
    private int ammountOfElements;

    public ArrayList() {
        ammountOfElements = 0;
        this.elementData = (T[]) new Object[DEFAULT_ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (ensureCapacity(ammountOfElements + 1)) {
            elementData[ammountOfElements++] = value;
        } else {
            Object[] oldData = elementData;
            newArrayCapasity(elementData.length);
            System.arraycopy(oldData, 0, elementData, 0, ammountOfElements + 1);
            elementData[ammountOfElements++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > ammountOfElements + 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
        if (ensureCapacity(ammountOfElements + 1)) {
            System.arraycopy(elementData, index, elementData,
                    index + 1, ammountOfElements + 1 - index);
            elementData[index] = value;
            ammountOfElements++;
        } else {
            Object[] oldData = elementData;
            newArrayCapasity(elementData.length);
            System.arraycopy(oldData, index, elementData,
                    index + 1, ammountOfElements + 1 - index);
            elementData[index] = value;
            ammountOfElements++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (ensureCapacity(ammountOfElements + list.size())) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        } else {
            Object[] oldData = elementData;
            newArrayCapasity(ammountOfElements + list.size());
            System.arraycopy(oldData, 0, elementData, 0, ammountOfElements + 1);
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= ammountOfElements) {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= ammountOfElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= ammountOfElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
        int numMoved = ammountOfElements - index - 1;
        T result = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--ammountOfElements] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < ammountOfElements; i++) {
            if ((t == null && elementData[i] == null)
                    || (elementData[i] != null && elementData[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElement");
    }

    @Override
    public int size() {
        return ammountOfElements;
    }

    @Override
    public boolean isEmpty() {
        return ammountOfElements == 0;
    }

    private boolean ensureCapacity(int size) {
        return this.elementData.length > size;
    }

    private void newArrayCapasity(int oldCapasity) {
        int arrayCapasity = (oldCapasity * 3) / 2 + 1;
        this.elementData = (T[]) new Object[arrayCapasity];
    }

    private void trimelEmentData() {
        Object[] oldData = elementData;
        elementData = (T[]) new Object[elementData.length - 1];
        System.arraycopy(oldData, 0, elementData, 0, ammountOfElements);
        ammountOfElements--;
    }
}
