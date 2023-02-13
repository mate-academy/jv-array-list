package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private int newCapacity = 10;
    private T[] elementData;
    private int size = 0;

    @Override
    public void add(T value) {
        if (isEmpty()) {
            elementData = (T[]) new Object[newCapacity];
            elementData[size] = value;
            size++;
        } else if (chekArraySize()) {
            elementData[size] = value;
            size++;
        } else {
            newCapacity = (int) (size * 1.5) + size;
            updateLengthArray(newCapacity);
            elementData[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        validIndex(index);
        if (isEmpty()) {
            elementData = (T[]) new Object[newCapacity];
            addValueToIndex(value, index);
        } else if (!chekArraySize()) {
            setNewCapacity();
            updateLengthArray(newCapacity);
            addValueToIndex(value, index);
        } else {
            addValueToIndex(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size - list.size() >= 0) {
            copyValueInArray(list);
        } else {
            newCapacity = (int) (size * 1.5) + size + list.size();
            updateLengthArray(newCapacity);
            copyValueInArray(list);
        }
    }

    @Override
    public T get(int index) {
        validIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, index);
        final T removeElement = elementData[index];
        if (index + 1 != size) {
            System.arraycopy(elementData, index + 1, list, index, size);
        }
        size--;
        elementData = (T[]) new Object[size];
        System.arraycopy(list, 0, elementData, 0, size);
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                removeValue(element, i);
                return element;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0) ? true : false;
    }

    private boolean chekArraySize() {
        if (size + 1 == newCapacity) {
            return false;
        } else {
            return true;
        }
    }

    private void validIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Not valid index");
        }
    }

    private void updateLengthArray(int capacity) {
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, size);
        elementData = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, elementData, 0, size);
    }

    private void addValueToIndex(T value, int index) {
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, index);
        list[index] = value;
        size++;
        System.arraycopy(elementData, index, list, index + 1, size);
        System.arraycopy(list, 0, elementData, 0, size);
    }

    private void removeValue(T value, int index) {
        T[] list = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, list, 0, index);
        size--;
        System.arraycopy(elementData, index + 1, list, index, size);
        System.arraycopy(list, 0, elementData, 0, size);
    }

    private void copyValueInArray(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }
}
