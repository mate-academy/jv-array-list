package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private int size;
    private Object[] data;

    public ArrayList() {
        data = new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) { // allow index == size
            throw new ArrayListIndexOutOfBoundsException("Нет места");
        }
        if (size == data.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
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
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldIndex = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size - 1] = null;
        return oldIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || (element != null && element.equals(data[i]))) {
                return remove(i);
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Нет места");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        Object[] newData = new Object[(int) (data.length + data.length / 2)];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException();
        }
    }
}
