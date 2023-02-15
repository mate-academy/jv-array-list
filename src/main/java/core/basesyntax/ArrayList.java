package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASIC_LENGTH = 10;
    private static final int LENGTH_STEP = 5;
    private T[] list;
    private int index;

    public ArrayList() {
        list = (T[]) new Object[BASIC_LENGTH];
    }

    @Override
    public void add(T value) {
        if (index == list.length) {
            addSpase();
        }
        list[index] = value;
        index++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0) {
            if (index > this.index) {
                /* If index of element doesn`t fall into the array */
                throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
            } else if (index == this.index) {
                /* If index of element fall into the end of array */
                add(value);
            } else {
                if (list.length == this.index) {
                    /* If array is overflowing, we need increase the array */
                    addSpase();
                    addByIndex(value, index);
                } else {
                    addByIndex(value, index);
                }
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is negative!");
        }
    }

    private void addSpase() {
        T[] array = (T[]) new Object[list.length + LENGTH_STEP];
        for (int i = 0; i < list.length; i++) {
            array[i] = list[i];
        }
        this.list = array;
    }

    private void addByIndex(T value, int index) {
        T[] array = (T[]) new Object[list.length];
        for (int i = 0; i < index; i++) {
            array[i] = list[i];
        }
        array[index] = value;
        this.index++;
        for (int i = index + 1; i < this.index; i++) {
            array[i] = list[i - 1];
        }
        for (int i = 0; i < this.index; i++) {
            list[i] = array[i];
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
        if (index < this.index && index >= 0) {
            return list[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < this.index && index >= 0) {
            list[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
    }

    @Override
    public T remove(int index) {
        if (index < this.index && index >= 0) {
            T temp = list[index];
            for (int i = index; i < list.length - 1; i++) {
                list[i] = list[i + 1];
            }
            this.index--;
            return temp;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index of list");
        }
    }

    @Override
    public T remove(T element) {
        int elementIndex = foundIndexByElement(element);
        /* -1 is a flag, that we don`t have a same objects */
        if (elementIndex != -1) {
            return remove(elementIndex);
        } else {
            throw new NoSuchElementException("ArrayList don`t have this element!");
        }
    }

    private int foundIndexByElement(T element) {
        int index = -1;
        for (int i = 0; i < this.index; i++) {
            if (compare(list[i], element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean compare(T element1, T element2) {
        if (element1 == null || element2 == null) {
            return element1 == element2 ? true : false;
        } else {
            return element1.equals(element2);
        }
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0 ? true : false;
    }
}
