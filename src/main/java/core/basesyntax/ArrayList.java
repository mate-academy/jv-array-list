package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataBase;
    private int size;

    public ArrayList() {
        this.dataBase = new Object[DEFAULT_CAPACITY];
    }

    private void grow(int minCapacity) {
        if (size >= dataBase.length) {
            int newCapacity = dataBase.length + (dataBase.length >> 1);
            Object[] newDataBase = new Object[newCapacity];
            System.arraycopy(dataBase, 0, newDataBase, 0, size);
            dataBase = newDataBase;
        }
    }

    @Override
    public void add(T value) {
        grow(size + 1);
        dataBase[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Entered invalid index, try again !");
        }
        grow(size + 1);
        for (int i = size; i > index; i--) {
            dataBase[i] = dataBase[i - 1];
        }
        dataBase[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        grow(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Entered invalid index, try again !");
        }
        return (T) dataBase[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Entered invalid index, try again !");
        }
        dataBase[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Entered invalid index, try again !");
        }
        T removedValue = get(index);
        System.arraycopy(dataBase, index + 1, dataBase, index, size - index - 1);
        dataBase[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == dataBase[i]) || (element != null && element.equals(dataBase[i]))) {
                T removedValue = (T) dataBase[i];
                System.arraycopy(dataBase, i + 1, dataBase, i, size - i - 1);
                dataBase[size - 1] = null;
                size--;
                return removedValue;
            }
        }
        throw new NoSuchElementException("Such element doesn't exist");
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
