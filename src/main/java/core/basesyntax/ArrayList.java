package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final float MULTIPLIER = 1.5f;
    private int maxElements = 10;
    private int size = 0;
    private T[] arrayValue = (T[]) new Object[maxElements];

    @Override
    public void add(T value) {
        resizeLogic();
        arrayValue[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index, true);
        resizeLogic();
        System.arraycopy(arrayValue, index, arrayValue, index + 1, size - index);
        arrayValue[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index, false);
        return arrayValue[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index, false);
        arrayValue[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement;
        indexCheck(index, false);
        removedElement = arrayValue[index];
        System.arraycopy(arrayValue, index + 1, arrayValue, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && arrayValue[i] == null
                    || arrayValue[i] != null
                    && arrayValue[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeLogic() {
        if (size == arrayValue.length) {
            maxElements *= MULTIPLIER;
            T[] newArrayValue = (T[]) new Object[(int) maxElements];
            System.arraycopy(arrayValue, 0, newArrayValue, 0, arrayValue.length);
            arrayValue = newArrayValue;
        }
    }

    private void indexCheck(int index, boolean isAdd) {
        if (isAdd) {
            if (index > size || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Incorrect index");
            }
        } else {
            if (index >= size || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Incorrect index");
            }
        }
    }
}
