package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private T[] arrayList;
    private int filledWithElements = 0;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        arrayList = (T[]) new Object[initialCapacity];
    }

    public void resize(int newLength) {
        T[] newArrayList = (T[]) new Object[newLength];
        System.arraycopy(arrayList, 0, newArrayList, 0, filledWithElements);
        arrayList = newArrayList;
    }

    @Override
    public void add(T value) {
        if (filledWithElements == arrayList.length) {
            resize((int) (arrayList.length * 1.5));
        }
        arrayList[filledWithElements++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > filledWithElements) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (filledWithElements == arrayList.length) {
            resize((int) (arrayList.length * 1.5));
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, filledWithElements - index);
        arrayList[index] = value;
        filledWithElements++;
    }

    @Override
    public void addAll(List<T> list) {
        while (arrayList.length < filledWithElements + list.size()) {
            resize((int) (arrayList.length * 1.5));
        }
        for (int i = 0; i < list.size(); i++) {
            arrayList[filledWithElements] = list.get(i);
            filledWithElements++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= filledWithElements) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return arrayList[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= filledWithElements) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= filledWithElements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T elementToRemove = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, filledWithElements - index - 1);
        filledWithElements--;
        return elementToRemove;
    }

    @Override
    public T remove(T t) {
        int i;
        boolean flag = false;
        for (i = 0; i < filledWithElements; i++) {
            if (flag == true) {
                arrayList[i - 1] = arrayList[i];
            } else if (arrayList[i] == t || (arrayList[i] != null && arrayList[i].equals(t))) {
                flag = true;
            }
        }
        if (flag == true) {
            arrayList[filledWithElements] = null;
            filledWithElements--;
            return t;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return filledWithElements;
    }

    @Override
    public boolean isEmpty() {
        return filledWithElements == 0;
    }
}
