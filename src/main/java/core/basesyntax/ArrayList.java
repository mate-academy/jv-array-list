package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private static final int ARRAY_DIRECTION_RIGHT = 1;
    private static final int ARRAY_DIRECTION_LEFT = -1;
    private Object[] content;
    private int size;

    public ArrayList() {
        content = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resizeIfNecessary();
        content[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        resizeIfNecessary();
        shiftArray(index, ARRAY_DIRECTION_RIGHT);
        content[index] = value;
        size++;
    }

    private void shiftArray(int indexFrom, int direction) {
        int srcPos = direction > 0 ? indexFrom : indexFrom + 1;
        int destPost = direction > 0 ? indexFrom + 1 : indexFrom;
        System.arraycopy(content, srcPos, content, destPost, content.length - (indexFrom + 1));
    }

    private void resizeIfNecessary() {
        if (content.length == size) {
            Object[] newContent = new Object[(int) (size * RESIZE_FACTOR)];
            System.arraycopy(content, 0, newContent, 0, content.length);
            content = newContent;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
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
        checkIndex(index);
        return (T) content[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        content[index] = value;
    }

    @Override
    public T remove(int index) {
        final T toRemove = get(index);
        shiftArray(index, ARRAY_DIRECTION_LEFT);
        size--;
        content[size] = null;
        return toRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (content[i] == null && element == null) {
                size--;
                return null;
            }
            if (content[i] != null && content[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There's no such element in list");
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
