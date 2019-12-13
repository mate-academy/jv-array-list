package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity != 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        if (elementData.length >= size + 1) {
            elementData[size] = value;
            size++;
            trimToSize();
        } else {
            grow();
            elementData[size] = value;
            size++;
            trimToSize();
        }
    }

    @Override
    public void add(T value, int index) throws ArrayIndexOutOfBoundsException {
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        }
        if (elementData.length >= size + 1) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            trimToSize();
        } else {
            grow();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            trimToSize();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) throws ArrayIndexOutOfBoundsException {
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayIndexOutOfBoundsException {
        T oldValue = get(index);
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[size - 1] = null;
            size--;
            trimToSize();
        }
        return oldValue;
    }

    @Override
    public T remove(T t) {
        int index = getIndex(t);
        if (index == -1) {
            throw new java.util.NoSuchElementException();
        }
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        trimToSize();
        return t;
    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] grew = new Object[elementData.length + (elementData.length >> 1) + 1];
        System.arraycopy(elementData, 0, grew, 0, size);
        elementData = grew;
    }

    private boolean checkIndex(int index) {
        return index < 0 || index > elementData.length;
    }

    private int getIndex(T t) {
        int result = -1;
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == t || elementData[i].equals(t))) {
                result = i;
                break;
            }
        }
        return result;
    }

    private void trimToSize() {
        Object[] newElementData = new Object[size];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }
}
