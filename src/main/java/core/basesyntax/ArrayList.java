package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double STEP_UPHILL = 1.5d;
    private static final int BASE_INDEX_FROM = 0;
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private Object[] dataCollection;
    private int size;

    public ArrayList() {
        this.dataCollection = EMPTY_ARRAY;
    }

    public ArrayList(int number) {
        if (number > 0) {
            this.dataCollection = new Object[number];
        } else {
            if (number != 0) {
                throw new IllegalArgumentException("Illegal argument: " + number);
            } else {
                this.dataCollection = EMPTY_ARRAY;
            }
        }
    }

    @Override
    public void add(T value) {
        if (this.size == this.dataCollection.length) {
            this.dataCollection = grow(this.size + 1);
        }
        this.dataCollection[this.size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > this.size || index < 0) {
            sendException(index);
        } else {
            if (index == this.size) {
                add(value);
            } else {
                int afterIndex = this.size - index;
                Object[] afterArray;
                afterArray = copyOf(this.dataCollection, afterIndex, index);
                this.dataCollection = grow(this.size + 1);
                this.dataCollection[index] = value;
                for (Object o : afterArray) {
                    index++;
                    this.dataCollection[index] = o;
                }
                this.size++;
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newCapacity = list.size() + this.size;
        int index = this.size;
        this.dataCollection = grow(newCapacity);
        for (int i = 0; i < list.size(); i++) {
            this.dataCollection[index] = list.get(i);
            index++;
        }
        this.size = newCapacity;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.size - 1) {
            sendException(index);
        }
        return (T) this.dataCollection[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > this.size - 1) {
            sendException(index);
        } else {
            this.dataCollection[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > this.size - 1) {
            sendException(index);
        }
        T data = (T) this.dataCollection[index];
        removeExtraIndex(this.dataCollection, index);
        this.size--;
        return data;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if ((element == null && this.dataCollection[i] == null)
                    || (this.dataCollection[i] != null && this.dataCollection[i].equals(element))) {
                removeExtraIndex(this.dataCollection, i);
                this.size--;
                return element;
            }
        }
        throw new NoSuchElementException("The element " + element + " is absent");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private Object[] grow(int needSize) {
        int oldSize = this.dataCollection.length;
        if (oldSize == 0) {
            return new Object[Math.max(DEFAULT_CAPACITY, needSize)];
        } else {
            int newCapacity = needSize <= oldSize
                    ? oldSize : growOnSomeSteps(oldSize, needSize);
            return this.dataCollection
                    = copyOf(this.dataCollection, newCapacity, BASE_INDEX_FROM);
        }
    }

    private int growOnSomeSteps(int oldCapacity, int needCapacity) {
        int newCapacity = (int) (oldCapacity * STEP_UPHILL);
        while (newCapacity < needCapacity) {
            newCapacity = (int) (newCapacity * STEP_UPHILL);
        }
        return newCapacity;
    }

    private Object[] copyOf(Object[] old, int renewedSize, int from) {
        Object[] renewed = new Object[renewedSize];
        int indexRenewed = 0;
        for (int i = from; i < this.size; i++) {
            renewed[indexRenewed] = old[i];
            indexRenewed++;
        }
        return renewed;
    }

    private void removeExtraIndex(Object[] array, int index) {
        for (int i = index + 1; i < this.size; i++) {
            array[index] = array[i];
            index++;
        }
        array[this.size - 1] = null;
    }

    private void sendException(int index) {
        throw new ArrayListIndexOutOfBoundsException("Index out of array bounds: " + index);
    }
}
