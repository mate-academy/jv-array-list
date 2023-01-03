package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        arrayCapacityCheck();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        indexCheck(index);
        arrayCapacityCheck();
        System.arraycopy(elementData, index, elementData,
                (index + 1), elementData.length - index - 1);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T temp = elementData[index];
        System.arraycopy(elementData,(index + 1),
                elementData, index, (elementData.length - index - 1));
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        T temp = null;
        int count = 0;
        for (int i = 0; i < size;i++) {
            if ((element == null && elementData[i] == null)
                    || (elementData[i] != null && (elementData[i].equals(element)))) {
                temp = elementData[i];
                remove(i);
                count++;
                break;
            }
        }
        noSuchElementCheck(count);
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void arrayCapacityCheck() {
        if (size >= elementData.length) {
            T[] temp = elementData;
            elementData = (T[]) new Object[elementData.length + (elementData.length / 2)];
            System.arraycopy(temp, 0, elementData, 0, temp.length);
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || (index != 0 && index >= size)) {
            throw new ArrayListIndexOutOfBoundsException();
        }
    }

    private void noSuchElementCheck(int count) {
        if (count == 0) {
            throw new NoSuchElementException("No such element in current array");
        }
    }
}
