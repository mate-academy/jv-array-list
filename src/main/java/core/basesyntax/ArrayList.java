package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objArr;
    private int size;

    public ArrayList() {
        objArr = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        objArr = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        if (size == objArr.length) {
            objArr = grow();
        }
        objArr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForAdd(index);
        Object [] newObj = new Object[objArr.length + 1];
        System.arraycopy(objArr,0, newObj,0, index);
        newObj[index] = value;
        System.arraycopy(objArr,index, newObj,index + 1, objArr.length - index);
        objArr = newObj;
        size++;
    }

    private void checkForAdd(int index) {
        if (index > size || index > objArr.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        } else if (index == objArr.length || objArr.length == size + 1) {
            objArr = grow();
        }
    }

    private Object[] grow() {
        return objArr = Arrays.copyOf(objArr, newCapacity());
    }

    private int newCapacity() {
        int oldCapacity = objArr.length;
        return oldCapacity + (oldCapacity >> 1);
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > objArr.length - size) {
            objArr = grow();
        }
        int k = 0;
        for (int i = size; i < list.size() + size; i++) {
            objArr[i] = list.get(k);
            k++;
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        checkExist(index);
        return (T)objArr[index];
    }

    @Override
    public void set(T value, int index) {
        checkExist(index);
        objArr[index] = value;
    }

    @Override
    public T remove(int index) {
        checkExist(index);
        T removed = (T) objArr[index];
        Object[] newArr = Arrays.copyOfRange(objArr, index + 1, objArr.length + 1);
        System.arraycopy(newArr, 0, objArr, index, newArr.length);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int indexRemove = findValue(objArr, element);
        if (indexRemove == -1) {
            throw new NoSuchElementException("Cann`t find element" + element);
        }
        T removed = (T)objArr[indexRemove];
        remove(indexRemove);
        return removed;
    }

    private int findValue(Object[] objects, T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, objArr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkExist(int index) {
        if (index >= size || index >= objArr.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
