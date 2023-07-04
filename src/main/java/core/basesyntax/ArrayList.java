package core.basesyntax;



public class ArrayList<T> implements List<T> {
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size == 0 ? 0 : size - 1] = value;
        size++;
    }

    private void expandArray() {
        Object[] newArray = new Object[array.length + array.length >> 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void add(T value, int index) {

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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
