package core.basesyntax;


import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int arrayLength;
    private Object [] array;
    private int arraySize;
    private int arrayIndex;
    private final int MINIMAL_ARRAY_SIZE;

    public ArrayList(){
        MINIMAL_ARRAY_SIZE = 10;
        arrayLength = MINIMAL_ARRAY_SIZE;
        array = new Object [arrayLength];
        arrayIndex = 0;
    }

    @Override
    public void add(T value) {
        if (arraySize == arrayLength) {
            resize(arrayLength);
        }
        array[arraySize] = value;
        arraySize++;
    }

    public void resize (int actualLength) {
        arrayLength = actualLength + MINIMAL_ARRAY_SIZE/2;
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, arrayIndex, newArray, arrayIndex, arraySize);
        array = newArray;
    }

    @Override
    public void add(T value, int index) {
        if (index > arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, arrayIndex, newArray, arrayIndex, arraySize-index);
        System.arraycopy(array, index, newArray, index+1, arraySize-index);
        newArray[index] = value;
        array = newArray;
        arraySize++;
        if (arraySize == arrayLength) {
            resize(arrayLength);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if ((list.size() + arraySize) > arrayLength){
            resize(arraySize + list.size());
        }
        for (int i1 = 0; i1 < list.size(); i1++){
            array[arraySize] = list.get(i1);
            arraySize++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= arraySize){
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        T a = (T) array[index];
        Object[]newArray = new Object[arrayLength];
        System.arraycopy(array, arrayIndex, newArray, arrayIndex, arraySize);
        System.arraycopy(array, index+1, newArray, index, arraySize-(index+1));
        array = newArray;
        arraySize--;
        return a;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (equals(element, (T) array[i])) {
                arrayIndex++;
            }
        }
        if (arrayIndex !=0) {
            Object[] newArray = new Object[arrayLength];
            System.arraycopy(array, arrayIndex, newArray, arrayIndex-1, arraySize - arrayIndex);
            array = newArray;
            arraySize--;
        }
        if (arrayIndex == 0){
            throw new NoSuchElementException("Can`t find such element");
        }
        return element;
    }

    public boolean equals(T firstElement, T secondElement) {
        return firstElement == null && secondElement == null
                || firstElement != null && firstElement.equals(secondElement);
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }
}
