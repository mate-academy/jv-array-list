package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = new Object[]{};

    }

    public ArrayList(int customCapacity) {
        if (customCapacity > 0) {
            this.elementData = new Object[customCapacity];
        } else if (customCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new RuntimeException("Impossible capacity: "
                    + customCapacity);
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] a = list.toArray();
        int addLength = a.length;
        if (addLength == 0) {
            return;
        }
        final int s = size;
        if (addLength > (this.elementData.length - s)) {
            this.elementData = grow(s + addLength);
        }
        System.arraycopy(a, 0, this.elementData, s, addLength);
        size = s + addLength;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        rangeCheckForGet(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGet(index);
        elementData[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        rangeCheckForGet(index);
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        final int index = findElement(element);
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (Arrays.equals(elementData, DEFAULTCAPACITY_EMPTY_ELEMENTDATA)) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            return minCapacity;
        }
        return (newCapacity - Integer.MAX_VALUE <= 0)
                ? newCapacity : Integer.MAX_VALUE;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add inappropriate index: "
                    + index);
        }
    }

    private void rangeCheckForGet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't get inappropriate index: "
                    + index);
        }
    }

    private void fastRemove(int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData, index + 1,
                    elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;
    }

    private int findElement(T o) {
        for (int i = 0; i < elementData.length; i++) {
            if (o == elementData[i] || o != null && o.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't find element: " + o);
    }
}
