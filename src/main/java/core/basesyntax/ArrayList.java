package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size;

    ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        values[size] = value;
        increaseSize();
    }

    @Override
    public void add(T value, int index) {
        if (index <= values.length && index >= 0) {
            T[] temp = (T[]) new Object[values.length];
            int amountElementSecondPartOfArray = size - index;
            System.arraycopy(values, 0, temp, 0, values.length);
            System.arraycopy(temp, 0, values, 0, index);
            values[index] = value;
            System.arraycopy(temp, index, values, index + 1, amountElementSecondPartOfArray);
            increaseSize();
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > values.length - size) {
            resize((int) Math.ceil((values.length + list.size()) * 1.5));
        }
        for (int i =0; i < list.size(); i++) {
            values[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        values[index] = value;
    }

    // С какого м, С какого э, В какой м, С какого э, сколько элементов.
    @Override
    public T remove(int index) {
        if (size > 0) {
            T[] temp = (T[]) new Object[values.length];
            System.arraycopy(values, 0, temp, 0, values.length);
            values = (T[]) new Object[values.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            int amountElementAfterIndex = size - index;
            System.arraycopy(temp, index + 1, values, index, amountElementAfterIndex);
            size--;
        }

        return (T) values;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(element)) {
                remove(i);
            }
        }
        return (T) values;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newSize) {
        T[] temp = (T[]) new Object[values.length];
        System.arraycopy(values, 0, temp, 0, values.length);
        values = (T[]) new Object[(int) (newSize)];
        System.arraycopy(temp, 0, values, 0, temp.length);
    }

    private void increaseSize() {
        if (size == values.length - 1) {
            resize((int) Math.ceil(values.length * 1.5));
        }
        size++;
    }
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(values[i]);
            string.append(", ");
        }
        return string.toString();
    }
}