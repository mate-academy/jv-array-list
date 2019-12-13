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
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            if (initialCapacity != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }
        }
    }

    @Override
    public void add(T value) {
        if (ensureCapacity()) {
            this.elementData[size] = value;
            size++;
            trimToSize();
        } else {
            Object[] array = grow();
            System.arraycopy(elementData, 0, array, 0, size);
            array[size] = value;
            elementData = array;
            size++;
            trimToSize();
        }
    }

    @Override
    public void add(T value, int index) throws ArrayIndexOutOfBoundsException {
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        }
        if (ensureCapacity()) {
            Object[] array = new Object[elementData.length];
            System.arraycopy(elementData, 0, array, 0, index - 1);
            array[index] = value;
            System.arraycopy(elementData, index + 1, array, index + 1, elementData.length - index);
            elementData = array;
            size++;
            trimToSize();
        } else {
            Object[] array = grow();
            System.arraycopy(elementData, 0, array, 0, index);
            array[index] = value;
            System.arraycopy(elementData, index, array, index + 1, elementData.length - index);
            elementData = array;
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
        return (T) this.elementData[index];
    }

    @Override
    public void set(T value, int index) throws ArrayIndexOutOfBoundsException {
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        }
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayIndexOutOfBoundsException {
        if (checkIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Wrong index " + index);
        }
        T oldValue = get(index);
        if (size - index - 1 >= 0) {
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
        if (size - index - 1 >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[size - 1] = null;
            size--;
            trimToSize();
        }
        return t;
    }

    @Override
    public int size() {
        return this.elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean ensureCapacity() {
        return this.elementData.length >= this.size + 1;
    }

    private Object[] grow() {
        return new Object[this.elementData.length + (this.elementData.length >> 1) + 1];
    }

    private boolean checkIndex(int index) {
        return index < 0 || index > this.elementData.length;
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
        System.arraycopy(this.elementData, 0, newElementData, 0, size);
        this.elementData = newElementData;
    }
}
