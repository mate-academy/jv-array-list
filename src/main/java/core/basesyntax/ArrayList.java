package core.basesyntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Illegal argument";
    private Object[] objectArray;
    private int size;

    public ArrayList() {
        this.objectArray = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int ownCapacity) {
        if (ownCapacity > 0) {
            this.objectArray = new Object[ownCapacity];
        } else if (ownCapacity == 0) {
            this.objectArray = new Object[]{};
        } else {
            throw new IllegalArgumentException(DEFAULT_EXCEPTION_MESSAGE
                    + ownCapacity);
        }
    }

    public ArrayList(Collection<? extends T> e) {
        objectArray = e.toArray();
    }

    @Override
    public void add(T value) {
        if (size == objectArray.length) {
            objectArray = grow(size);
        }
        objectArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(DEFAULT_EXCEPTION_MESSAGE
                    + index);
        } else if (index == objectArray.length) {
            objectArray = grow(index);
            objectArray[index] = value;
            size++;
        } else {
            System.arraycopy(objectArray, index,
                    objectArray, index + 1, size - index);
            objectArray[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newObject = ((ArrayList<T>) list).toArray();
        int collectionSize = newObject.length;
        if (collectionSize == 0) {
            return;
        } else if (collectionSize > objectArray.length - size) {
            objectArray = grow(collectionSize + size);
        }
        System.arraycopy(newObject, 0, objectArray, size, collectionSize);
        size += collectionSize;
    }

    private Object[] grow(int index) {
        if (index >= objectArray.length) {
            objectArray = Arrays.copyOf(objectArray, (int) (index * 1.5));
        }
        return objectArray;
    }

    public Object[] toArray() {
        return Arrays.copyOf(objectArray, size);
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException(DEFAULT_EXCEPTION_MESSAGE
                    + index);
        } else {
            return (T) objectArray[index];
        }
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index" + index);
        } else {
            objectArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        int newSize = size - 1;
        T removeObject;
        if (!checkIndex(index)) {
            removeObject = (T) objectArray[index];
            if (newSize > index) {
                System.arraycopy(objectArray, index + 1, objectArray, index, newSize);
                size--;
            } else if (newSize == index) {
                objectArray[index] = null;
                size--;
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Illegal index" + index);
        }
        return removeObject;
    }

    @Override
    public T remove(T element) {
        int newSize = size - 1;
        T removeObject;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, objectArray[i]) || objectArray == null) {
                removeObject = (T) objectArray[i];
                System.arraycopy(objectArray, i + 1, objectArray, i, newSize);
                size--;
                return removeObject;
            }
        }
        throw new NoSuchElementException("This element not found" + element);
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
