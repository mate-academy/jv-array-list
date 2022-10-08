package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int index;

    private void resize() {
        Object[] moreSizeArray = new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, moreSizeArray, 0, elements.length - 1);
        elements = moreSizeArray;
    }
    @Override
    public void add(T value) {
        if (index + 1 > elements.length) {
            resize();
        }
        try {
            elements[index] = value;
            index++;
        } catch (ArrayListIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Cant add");
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > 0 & index < elements.length) {
            this.index = index;
            add(value);
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
        if (index >= 0) {
            return (T) elements[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        if (IndexIsCorrect(index)) {
            final Object[] es = elements;
            T oldValue = (T) es[index];
            fastRemove(es, index);


        }
        return null;
    }
    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = index - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[index = newSize] = null;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elements;
        final int size = this.index;
        int i = 0;
        found: {
            if (element == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (element.equals(es[i]))
                        break found;
            }
            return null;
        }
        fastRemove(es, i);
        return null;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        if (elements[0] == null) {
            return true;
        }
        return false;
    }
    private boolean IndexIsCorrect(int index) {
        if (index < 0 | index > elements.length) {
            return false;
        }
        return true;
    }
}
