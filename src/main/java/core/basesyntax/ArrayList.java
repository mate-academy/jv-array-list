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
        elements[index] = value;
        index++;
    }

    @Override
    public void add(T value, int index) {
        if (index > this.index) {
            throw new ArrayListIndexOutOfBoundsException("we cant initialise current index "
                    + index + " while index " + this.index + " is null");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("we cant add value on index "
                    + index + "because this index is negative");
        }
        if (elements[index] != null) {
            final Object[] newElems = new Object[elements.length];
            System.arraycopy(elements, 0, newElems, 0, index);
            newElems[index] = value;
            System.arraycopy(elements, index, newElems, index + 1, elements.length - index - 1);
            elements = newElems;
            this.index++;;
            return;
        }
        add(value);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > this.index - 1) {
            throw new ArrayListIndexOutOfBoundsException("we cant get value on index "
                    + index + " while index " + (this.index - 1)+ " is null");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("we cant get value on index "
                    + index + "because this index is negative");
        }
        if (index + 1 == elements.length) {
            resize();
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > this.index - 1) {
            throw new ArrayListIndexOutOfBoundsException("we cant set value on current index "
                    + index + " while index " + this.index + " is null");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("we cant set value on index "
                    + index + " because current index is negative");
        }
        if (elements[index] != null) {
            final Object[] newElems = new Object[elements.length];
            System.arraycopy(elements, 0, newElems, 0, index);
            newElems[index] = value;
            System.arraycopy(elements, index  + 1, newElems, index + 1, elements.length - index - 1);
            elements = newElems;
            return;
        }

    }

    @Override
    public T remove(int index) {
        if (index > this.index - 1) {
            throw new ArrayListIndexOutOfBoundsException("we cant remove value on current index "
                    + index + " while index " + (this.index - 1)+ " is null");
        }
        if (index < 0) {
            throw  new ArrayListIndexOutOfBoundsException("we cant remove value on index "
                    + index + "because current index is negative");
        }
            final Object[] es = elements;
            T oldValue = (T) es[index];
            fastRemove(es, index);

        return oldValue;
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
        }
        fastRemove(es, i);
        return element;
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
