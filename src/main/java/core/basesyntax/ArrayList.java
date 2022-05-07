package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayList = new Object[DEFAULT_ARRAY_SIZE];
    private int size;

    public void checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void resize() {
        if (arrayList.length == size) {
            arrayList = Arrays.copyOf(arrayList, (int) (size * 1.5));
        }
    }

    @Override
    public void add(T value) {
        resize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
        for (int i = size; i > index; i--) {
            resize();
            arrayList[i] = arrayList[i - 1];
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            arrayList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexRemoveSet(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRemoveSet(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRemoveSet(index);
        T popped = (T) arrayList[index];
        if (index == size - 1) {
            arrayList[index] = null;
            size--;
            return popped;
        }
        for (int i = index; i < size; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[size] = null;
        size--;
        return popped;
    }

    @Override
    public T remove(T element) {
        T popped = null;
        for (int i = 0; i < size; i++) {
            T currentObject = (T) arrayList[i];
            if (currentObject == element
                    || (currentObject != null
                    && element != null
                    && currentObject.equals(element))) {
                return remove(i);
            }
        }
        if (popped == null) {
            throw new NoSuchElementException("Element is not in the List");
        }
        return popped;
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
