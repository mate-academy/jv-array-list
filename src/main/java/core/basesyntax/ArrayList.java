package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int index;

    @Override
    public void add(T value) {
        willResizeIfNeed();
        elements[index] = value;
        index++;
    }

    @Override
    public void add(T value, int index) {
        exceptions(index, new Throwable().getStackTrace()[0].getMethodName());
        if (elements[index] != null) {
            final Object[] newElems = new Object[elements.length];
            System.arraycopy(elements, 0, newElems, 0, index);
            newElems[index] = value;
            System.arraycopy(elements, index, newElems, index + 1, elements.length - index - 1);
            elements = newElems;
            this.index++;
            willResizeIfNeed();
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
        exceptions(index, new Throwable().getStackTrace()[0].getMethodName());
        if (index + 1 == elements.length) {
            willResizeIfNeed();
        }
        return (T) elements[index];
    }

    private void willResizeIfNeed() {
        if (this.index + 1 > elements.length) {
            Object[] moreSizeArray = new Object[elements.length + elements.length / 2];
            System.arraycopy(elements, 0, moreSizeArray, 0, elements.length);
            elements = moreSizeArray;
        }
    }

    @Override
    public void set(T value, int index) {
        exceptions(index, new Throwable().getStackTrace()[0].getMethodName());
        if (elements[index] != null) {
            final Object[] newElems = new Object[elements.length];
            System.arraycopy(elements, 0, newElems, 0, index);
            newElems[index] = value;
            System.arraycopy(elements, index + 1, newElems, index + 1, elements.length - index - 1);
            elements = newElems;
        }
    }

    @Override
    public T remove(int index) {
        exceptions(index, new Throwable().getStackTrace()[0].getMethodName());
        final Object[] es = elements;
        T oldValue = (T) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < index; i++) {
            if (element != null) {
                if (element.equals(elements[i])) {
                    remove(i);
                    return element;
                }
            }
        }
        if (element == null) {
            index--;
            return element;
        }
        throw new NoSuchElementException("We cant find this element " + element);
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = index - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[index = newSize] = null;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return elements[0] == null;
    }

    private void exceptions(int index, String methodName) {
        if (methodName.equals("add")) {
            if (index > this.index) {
                throw new ArrayListIndexOutOfBoundsException("we cant initialise current index "
                        + index + " while index " + this.index + " is null");
            }
            if (index < 0) {
                throw new ArrayListIndexOutOfBoundsException("we cant add value on index "
                        + index + "because this index is negative");
            }
            return;
        }
        if (index > this.index - 1) {
            throw new ArrayListIndexOutOfBoundsException("we cant "
                    + methodName + " value on current index "
                    + index + " while index " + this.index + " is null");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("we cant "
                    + methodName + " value on index "
                    + index + " because current index is negative");
        }
    }
}
