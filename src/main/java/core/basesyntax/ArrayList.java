package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] defaultNewCapacity;
    private T[] elementArray;
    private int size = 0;
    private int activationFlag = 0;

    public ArrayList() {
        elementArray = (T[]) new Object[DEFAULT_SIZE];
    }

    public ArrayList(int capacity) {
        elementArray = (T[]) new Object[capacity];
    }


    @Override
    public void add(T value) {
        if (size == elementArray.length) {
            regenerationSizeArray();
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(" no index in array " + index);
        }
        defaultNewCapacity = new Object[size + 1];
        activationFlag = 0;
        for (int i = 0; i < defaultNewCapacity.length; i++) {
            if (i < index) {
                defaultNewCapacity[i] = elementArray[i];
            }
            if (i == index) {
                defaultNewCapacity[i] = value;
            }
            if (i > index) {
                defaultNewCapacity[i] = elementArray[i - 1];
            }
        }
        size++;
        elementArray = (T[]) defaultNewCapacity;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > size || list.size() < 0) {
            throw new ArrayListIndexOutOfBoundsException("no index in array " + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elementArray[size + i] = list.get(i);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        defaultNewCapacity = new Object[size - 1];
        T result = get(index);
        activationFlag = 0;
        for (int i = 0; i < defaultNewCapacity.length; i++) {
            if (i < index) {
                defaultNewCapacity[i] = elementArray[i];
            }
            if (i == index) {
                defaultNewCapacity[i] = elementArray[i + 1];
            }
            if (i > index) {
                defaultNewCapacity[i] = elementArray[i + 1];
            }
        }
        size--;
        elementArray = (T[]) defaultNewCapacity;
        return result;
    }

    @Override
    public T remove(T element) {

        defaultNewCapacity = new Object[size];
        T result = null;
        activationFlag = 0;
        for (int i = 0; i < size; ++i) {
            if (element == null) {
                size--;
                return null;
            } else if (element.equals(elementArray[i])) {
                result = elementArray[i];
                defaultNewCapacity[i] = elementArray[i + 1];
                activationFlag = 1;
                size--;
            }
            defaultNewCapacity[i] = elementArray[i + activationFlag];
        }
        elementArray = (T[]) defaultNewCapacity;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void regenerationSizeArray() {
        if (size == elementArray.length) {
            defaultNewCapacity = new Object[size * 2];
            for (int i = 0; i < elementArray.length; i++) {
                defaultNewCapacity[i] = elementArray[i];
            }
            elementArray = (T[]) defaultNewCapacity;
        }
    }
}
