package core.basesyntax;



public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size] = value;
        size++;
    }

    private void expandArray() {
        Object[] newArray = new Object[array.length + array.length >> 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == array.length) {
            expandArray();
        }
        for (int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = value;
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
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedItem = (T) array[index];
        for (int i = size; i > index; i--) {
            array[i-1] = array[i];
        }
        return removedItem;
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no element at index " + index);
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't input a negative index. "
                    + index + " is below zero!");
        }
    }

    @Override
    public T remove(T element) {
        T removedItem = element;
        for (int i = 0; i < array.length; i++) {
            T castedObject = (T) array[i];
            if (castedObject.equals(element)) {
                removedItem = remove(i);
            }
        }
        return removedItem;
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
