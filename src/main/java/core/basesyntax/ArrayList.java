package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPASITY = 10;
    private Object[] elementData;
    private int arrayCapasity;
    private int ammountOfElements;

    public ArrayList() {
        arrayCapasity = DEFAULT_ARRAY_CAPASITY;
        ammountOfElements = 0;
        this.elementData = (T[]) new Object[DEFAULT_ARRAY_CAPASITY];
    }

    @Override
    public void add(T value) {
        if (ensureCapacity(ammountOfElements + 1)) {
            elementData[ammountOfElements++] = value;
        } else {
            Object[] oldData = elementData;
            newArrayCapasity(arrayCapasity);
            System.arraycopy(oldData, 0, elementData, 0, ammountOfElements + 1);
            elementData[ammountOfElements++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < ammountOfElements + 1 || index < 0) {
            if (ensureCapacity(ammountOfElements + 1)) {
                System.arraycopy(elementData, index, elementData,
                        index + 1, ammountOfElements + 1 - index);
                elementData[index] = value;
                ammountOfElements++;
            } else {
                Object[] oldData = elementData;
                newArrayCapasity(arrayCapasity);
                System.arraycopy(oldData, index, elementData,
                        index + 1, ammountOfElements + 1 - index);
                elementData[index] = value;
                ammountOfElements++;
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (ensureCapacity(ammountOfElements + list.size())) {
            for (int i = 0; i < list.size(); i++) {
                elementData[ammountOfElements++] = list.get(i);
            }
        } else {
            Object[] oldData = elementData;
            newArrayCapasity(ammountOfElements + list.size());
            System.arraycopy(oldData, 0, elementData, 0, ammountOfElements + 1);
            for (int i = 0; i < list.size(); i++) {
                elementData[ammountOfElements++] = list.get(i);
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < ammountOfElements) {
            return (T) elementData[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < ammountOfElements || index < 0) {
            elementData[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index < ammountOfElements || index < 0) {
            int numMoved = ammountOfElements - index - 1;
            T result = (T) elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            elementData[--ammountOfElements] = null;
            return result;
        } else {
            throw new ArrayIndexOutOfBoundsException("ammountOfElements: "
                    + ammountOfElements + "<" + "index: " + index);
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < ammountOfElements; i++) {
            if ((t == null && elementData[i] == null)
                    || (elementData[i] != null && elementData[i].equals(t))) {
                int numMoved = ammountOfElements - i - 1;
                T result = (T) elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, numMoved);
                elementData[--ammountOfElements] = null;
                return result;
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
        return this.arrayCapasity > size;
    }

    private void newArrayCapasity(int oldCapasity) {
        this.arrayCapasity = (oldCapasity * 3) / 2 + 1;
        this.elementData = (T[]) new Object[arrayCapasity];
    }
}
