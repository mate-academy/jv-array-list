package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] arrayOfObjects;
    private int size;

    public ArrayList() {
        arrayOfObjects = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == arrayOfObjects.length) {
            Object[] tempArray = new Object[size + size / 2];
            System.arraycopy(arrayOfObjects,0,tempArray,0,arrayOfObjects.length);
            arrayOfObjects = null;
            arrayOfObjects = new Object[tempArray.length];
            System.arraycopy(tempArray,0,arrayOfObjects,0,tempArray.length);
        }
        
        this.arrayOfObjects[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }

        int arrayLength = arrayOfObjects.length;
        Object[] tempArrayFirstPart = new Object[index];
        Object[] tempArraySecondPart = new Object[arrayLength - index];
        System.arraycopy(arrayOfObjects, 0, tempArrayFirstPart, 0, index);
        System.arraycopy(arrayOfObjects, index, tempArraySecondPart, 0, size - index);
        arrayOfObjects = null;
        arrayOfObjects = new Object[arrayLength + 1];
        arrayOfObjects[index] = value;

        System.arraycopy(tempArrayFirstPart, 0, arrayOfObjects, 0, tempArrayFirstPart.length);
        System.arraycopy(tempArraySecondPart, 0, arrayOfObjects, 
                index + 1, tempArraySecondPart.length);

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listArray = toArray(list);
        Object[] tempArray = new Object[size];
        System.arraycopy(arrayOfObjects, 0, tempArray, 0, size);
        arrayOfObjects = null;
        arrayOfObjects = new Object[tempArray.length + listArray.length];

        System.arraycopy(tempArray, 0, arrayOfObjects, 0, tempArray.length);
        System.arraycopy(listArray, 0, arrayOfObjects, tempArray.length, listArray.length);

        size = tempArray.length + listArray.length;
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) arrayOfObjects[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            arrayOfObjects[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public T remove(int index) {
        for (int i = 0; i < size; i++) {
            String element = "";

            if (index >= 0) {
                element = (String) arrayOfObjects[index];
            }
            if (i == index) {
                Object[] tempArrayFirstPart = new Object[index];
                Object[] tempArraySecondPart = new Object[size - index - 1];
                System.arraycopy(arrayOfObjects, 0, tempArrayFirstPart, 0, index);
                System.arraycopy(arrayOfObjects, index + 1,
                        tempArraySecondPart, 0, size - index - 1);
                resizeForRemove();
                System.arraycopy(tempArrayFirstPart, 0, arrayOfObjects,
                        0, tempArrayFirstPart.length);
                System.arraycopy(tempArraySecondPart, 0, arrayOfObjects,
                        tempArrayFirstPart.length, tempArraySecondPart.length);
                return (T) element;
            }
        }
        throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayOfObjects.length; i++) {
            if (arrayOfObjects[i] == element
                    || element != null && element.equals(arrayOfObjects[i])) {
                Object[] tempArrayFirstPart = new Object[i];
                Object[] tempArraySecondPart = new Object[size - i - 1];

                System.arraycopy(arrayOfObjects, 0, tempArrayFirstPart, 0, i);
                System.arraycopy(arrayOfObjects, i + 1, tempArraySecondPart, 0, size - i - 1);
                resizeForRemove();
                System.arraycopy(tempArrayFirstPart, 0, arrayOfObjects,
                        0, tempArrayFirstPart.length);
                System.arraycopy(tempArraySecondPart, 0, arrayOfObjects,
                        tempArrayFirstPart.length, tempArraySecondPart.length);

                return element;
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
    }

    public void resizeForRemove() {
        arrayOfObjects = null;
        arrayOfObjects = new Object[size - 1];
        size -= 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] toArray(List<T> list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
