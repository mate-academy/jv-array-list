package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_COPACITI = 10;
    private Object[] arrayList;
    private int indexSize;

    public ArrayList() {
        arrayList = new Object[DEFAULT_COPACITI];
    }

    @Override
    public void add(T value) {

        if (indexSize == arrayList.length) {
            resizeArray();
        }
        arrayList[indexSize] = value;
        indexSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be less than zero");
        }
        if (indexSize == arrayList.length) {
            resizeArray();
        }
        if (index < indexSize) {
            for (int i = arrayList.length - 1; i > index; i--) {
                arrayList[i] = arrayList[i - 1];
            }
            arrayList[index] = value;
        } else if (index == indexSize) {
            arrayList[indexSize] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index cannot be greater than size");
        }
        indexSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be less than zero");
        } else if (index >= indexSize) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be more size");
        } else {
            return (T) arrayList[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be less than zero");
        } else if (index >= indexSize) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be more size");
        } else if (index < indexSize) {
            arrayList[index] = value;
        } else if (index <= indexSize) {
            arrayList[indexSize] = value;
        }

    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be less than zero");
        } else if (index >= indexSize) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be more size");
        } else {
            final Object returnValue = arrayList[index];
            for (int i = index; i < indexSize - 1; i++) {
                arrayList[i] = arrayList[i + 1];
            }
            arrayList[indexSize - 1] = null;
            indexSize--;

            return (T) returnValue;
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < indexSize; i++) {
            if (arrayList[i] == null ? element == null : arrayList[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            remove(index);
            return element;
        } else {
            throw new NoSuchElementException("don't have this element");
        }
    }

    @Override
    public int size() {
        return indexSize;
    }

    @Override
    public boolean isEmpty() {
        return indexSize == 0;
    }

    private void resizeArray() {
        int newCapacity = arrayList.length + arrayList.length / 2;
        Object[] newArrayList = new Object[newCapacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, indexSize);
        arrayList = newArrayList;
    }
}
