package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] internalArray;
    private int size;

    public ArrayList() {
        this.internalArray = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.internalArray = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size != internalArray.length) {
            for (int i = 0; i < internalArray.length; i++) {
                if (i == size) {
                    internalArray[i] = value;
                    size++;
                    break;
                }
            }
        } else {
            grow();
            internalArray[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            growByCell(index);
            internalArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!!!");
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
        if (index < size && index >= 0) {
            return (T) this.internalArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("The such index doesn't exist");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            internalArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong array index!");
        }
    }

    @Override
    public T remove(int index) {
        T oldelement = null;
        if (index < this.size && index >= 0) {
            int subsiquentIndex = 0;
            oldelement = get(index);
            Object[] newFormedArray = new Object[internalArray.length];
            for (int i = 0; i < size; i++) {
                if (i != index) {
                    newFormedArray[subsiquentIndex] = internalArray[i];
                    subsiquentIndex++;
                }
            }
            internalArray = newFormedArray;
            size--;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return oldelement;
    }

    @Override
    public T remove(T element) {
        T oldElement = null;
        for (int i = 0; i < size; i++) {
            if (internalArray[i] != null && internalArray[i].equals(element)) {
                oldElement = element;
                remove(i);
                break;
            } else if (internalArray[i] == null && internalArray[i] == element) {
                remove(i);
                break;
            }
        }
        if (oldElement == null && element != null) {
            throw new NoSuchElementException("This element hasn't found");
        }
        return oldElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] bufferArray = new Object[size + (size >> 1)];
        System.arraycopy(internalArray, 0, bufferArray, 0, size);
        internalArray = bufferArray;
    }

    private void growByCell(int index) {
        Object[] arrayBeforeIndex = new Object[index];
        Object[] arrayAfterIndex = new Object[size - index];
        System.arraycopy(internalArray, 0, arrayBeforeIndex, 0, index);
        System.arraycopy(internalArray, index, arrayAfterIndex, 0, size - index);
        if (index < size && (size + 1) <= internalArray.length) {
            System.arraycopy(arrayBeforeIndex, 0, internalArray, 0, arrayBeforeIndex.length);
            System.arraycopy(arrayAfterIndex, 0, internalArray, index + 1, arrayAfterIndex.length);
        } else if (index < size && (size + 1) > internalArray.length) {
            grow();
            System.arraycopy(arrayBeforeIndex, 0, internalArray, 0, arrayBeforeIndex.length);
            System.arraycopy(arrayAfterIndex, 0, internalArray, index + 1, arrayAfterIndex.length);
        }
    }
}
