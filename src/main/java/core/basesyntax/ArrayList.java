package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] values;
    private int amountOfElements;

    public ArrayList() {
        values = new Object[ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        values[amountOfElements] = value;
        amountOfElements++;
    }

    @Override
    public void add(T value, int index) {
        if (index > amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        grow();
        System.arraycopy(values, index, values, index + 1, amountOfElements - index);
        values[index] = value;
        amountOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= amountOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct");
        }
        values[index] = value;
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
        return amountOfElements;
    }

    @Override
    public boolean isEmpty() {
        return amountOfElements == 0;
    }

    private void grow() {
        if (amountOfElements == values.length) {
            Object[] valuesTemp = new Object[values.length + values.length / 2];
            System.arraycopy(values, 0, valuesTemp, 0, amountOfElements);
            values = valuesTemp;
        }
    }
}