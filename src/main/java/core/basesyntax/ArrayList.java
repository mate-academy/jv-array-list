package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private Object[] arrayList = new Object[10];

    private void resizeArray(int deltaSize) {
        Object[] newArrayList = Arrays.copyOf(arrayList, size() + deltaSize);
        arrayList = newArrayList;
    }

    @Override
    public void add(T value) {
        resizeArray(1);
        arrayList[size()] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException("You cannot add an item to this position! ("
                    + index + ")");
        }
        resizeArray(1);
        if (index == size()) {
            arrayList[size()] = value;
        } else {
            for (int i = size(); i > index; i--) {
                arrayList[i] = arrayList[i - 1];
            }
            arrayList[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int arraySize = size();
        resizeArray(list.size());
        for (int i = 0; i <  list.size(); i++) {
            arrayList[arraySize + i] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Item with this number does not exist!");
        }
        return  (T)arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException("You cannot set an item to this position!");
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Item with this number does not exist!");
        }
        T result = (T) arrayList[index];
        if (index < size()) {
            for (int i = index; i < size() - 1; i++) {
                arrayList[i] = arrayList[i + 1];
            }
            arrayList[size() - 1] = null;
        }
        return result;
    }

    @Override
    public T remove(T t) {
        T result = null;
        for (int i = 0; i < size(); i++) {
            if (arrayList[i] == t) {
                result = (T) arrayList[i];
                for (int j = i; j < size() - 1; j++) {
                    arrayList[j] = arrayList[j + 1];
                }
                arrayList[size() - 1] = null;
            }
        }
        if (result == null) {
            throw new ArrayIndexOutOfBoundsException("Item with this number does not exist!");
        }
        return result;
    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null) {
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() > 0 ? false : true;
    }
}

