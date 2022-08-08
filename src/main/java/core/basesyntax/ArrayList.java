package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {

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

    public int checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bound index " + index);
        }
        return index;
    }

    private void ensureCapacity() {
        Object[] newArrayList = new Object[arrayList.length + arrayList.length / 2];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        arrayList = newArrayList;
    }
}
