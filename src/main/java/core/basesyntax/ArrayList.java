package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        if (size + 1 == elementData.length) {
            T[] newElementData = (T[]) new Object[elementData.length + elementData.length >> 1];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
        elementData[size] = value;
        size ++;
    }

    @Override
    public void add(T value, int index) {
        if (size + 1 == elementData.length) {
            T[] newElementData = (T[]) new Object[elementData.length + elementData.length >> 1];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index + 1, newElementData, index + 1, size - index - 1);
            elementData = newElementData;
        }
        elementData[index] = value;
        size ++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
