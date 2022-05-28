package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private static final int FIRST_INDEX_OF_ARRAY = 0;
    private static final int NEXT_ELEMENT = 1;
    private Object [] arrayList;
    private Object [] newArrayList;
    private int size;

    {
        arrayList = new Object[DEFAULT_SIZE_OF_ARRAY];
        size = 0;
    }

    @Override
    public void add(T value) {
        capacityCheck();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        capacityCheck();
        addingIndexCheck(index);
        if (index == size) {
            arrayList[index] = value;
            size++;
        } else {
            int el = size - index;
            newArrayList = new Object[arrayList.length];
            System.arraycopy(arrayList, FIRST_INDEX_OF_ARRAY, newArrayList,
                    FIRST_INDEX_OF_ARRAY, index);
            System.arraycopy(arrayList, index, newArrayList, index + NEXT_ELEMENT, el);
            newArrayList[index] = value;
            size++;
            arrayList = newArrayList;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList [index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object deletedObject = arrayList[index];
        int countEl = size - (index + NEXT_ELEMENT);
        System.arraycopy(arrayList,index + NEXT_ELEMENT,arrayList,
                index,countEl);
        size--;
        return (T)deletedObject;
    }

    @Override
    public T remove(T element) {
        int index = findByElement(arrayList,element);
        int countEl = size - (index + NEXT_ELEMENT);
        System.arraycopy(arrayList,index + NEXT_ELEMENT,arrayList,
                index,countEl);
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int findByElement(Object [] objects, T element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == element || objects[i] != null && objects[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private void checkIndex(int index) {
        if (index >= size || index < FIRST_INDEX_OF_ARRAY) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void capacityCheck() {
        if (size == arrayList.length) {
            arrayList = biggerCapacity(arrayList);
        }
    }

    private Object[] biggerCapacity(Object [] arrayList) {
        newArrayList = new Object[arrayList.length + arrayList.length / 2];
        System.arraycopy(arrayList,FIRST_INDEX_OF_ARRAY, newArrayList, FIRST_INDEX_OF_ARRAY, size);
        arrayList = newArrayList;
        return arrayList;
    }

    private void addingIndexCheck(int index) {
        if (index < 0 || index > size || index > size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
