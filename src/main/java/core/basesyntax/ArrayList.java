package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final String ERROR_MESSAGE = "Array list index out of bounds";
    private int size = 0;
    private int capacity = 10;
    private Object[] listArr;

    public ArrayList() {
        listArr = new Object[capacity];
    }


    @Override
    public void add(T value) {
        if (size >= capacity) {
            extendCapacity();
        }
        listArr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
        if (size >= capacity) {
            extendCapacity();
        }
        Object[] tempArr = new Object[++size];
        for (int i = 0; i < index; i++) {
            tempArr[i] = listArr[i];
        }
        tempArr[index] = value;
        for (int i = index + 1; i < size; i++) {
            tempArr[i] = listArr[i - 1];
        }
        for (int i = 0; i < size; i++) {
            listArr[i] = tempArr[i];
        }
    }

    @Override
    public void addAll(List<T> list) {
        if ((list.size() + size) >= capacity) {
            extendCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private void extendCapacity() {
        capacity = capacity + 1 + (capacity >> 1);
        Object[] tempArray = new Object[capacity];
        System.arraycopy(listArr, 0, tempArray, 0, size);
        listArr = new Object[capacity];
        System.arraycopy(tempArray, 0, listArr, 0, size);
    }

    private void checkRange(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_MESSAGE);
        }
    }

    @Override
    public T get(int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return (T) listArr[i];
            }
        }
        throw new ArrayListIndexOutOfBoundsException("bound");
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
        return size == 0;
    }
}
