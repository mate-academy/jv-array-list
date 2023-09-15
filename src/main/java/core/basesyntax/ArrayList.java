package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataBase;
    private int size;

    public ArrayList() {
        this.dataBase = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        dataBase[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Entered invalid index, try again !");
        }
        grow();
        System.arraycopy(dataBase, index, dataBase, index + 1, size - index);
        dataBase[index] = value;
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
        indexCheck(index);
        return (T) dataBase[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        dataBase[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T removedValue = (T) dataBase[index];
        System.arraycopy(dataBase, index + 1, dataBase, index, size - index - 1);
        dataBase[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == dataBase[i]) || (element != null && element.equals(dataBase[i]))) {
                return remove(i);
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

    private void grow() {
        if (size >= dataBase.length) {
            int newCapacity = (int) (dataBase.length * 1.5);
            Object[] newDataBase = new Object[newCapacity];
            System.arraycopy(dataBase, 0, newDataBase, 0, size);
            dataBase = newDataBase;
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Entered invalid index, try again !");
        }
    }
}
