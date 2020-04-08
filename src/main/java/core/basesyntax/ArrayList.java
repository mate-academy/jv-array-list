package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    private void ensureCapacity() {
        if (elementData.length == size) {
            T[] add = (T[]) new Object[size + (size / 2)];
            System.arraycopy(elementData, 0,
                    add, 0, elementData.length);
            elementData = add;
        }
    }

    private int valueIndex(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null ? elementData[i].equals(value) : elementData[i] == value) {
                return i;
            }
        }
        throw new java.util.NoSuchElementException();
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
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
        if (index < size) {
            return elementData[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = get(index);
        size--;
        System.arraycopy(elementData, index + 1,
                elementData, index, size);
        return result;
    }

    @Override
    public T remove(T t) {
        return remove(valueIndex(t));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
