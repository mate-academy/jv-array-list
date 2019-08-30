package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 16;
    private Object[] elementData = new Object[CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        resize();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        resize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add((T) list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[size--] = null;
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == t) {
                T temp = (T) elementData[i];
                remove(i);
                return temp;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size >= elementData.length) {
            Object[] temp = new Object[elementData.length * 3 / 2];
            System.arraycopy(elementData, 0, temp, 0, elementData.length);
            elementData = temp;
        }
    }
}
