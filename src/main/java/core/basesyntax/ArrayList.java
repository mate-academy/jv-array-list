package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] list;
    private final int defSize = 10;
    private int size;

    public ArrayList() {
        this.list = (T[]) new Object[defSize];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            this.list = grow();
        }
        this.list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Bounds");
        }
        if (size == list.length) {
            this.list = grow();
        }
        System.arraycopy(this.list, index, this.list, index + 1, size - index);
        this.list[index] = value;
        size++;
    }

    private boolean checkSize() {
        return size + 1 > this.list.length;
    }

    private boolean checkSize(int num) {
        return size + num > this.list.length;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        while (checkSize(list.size())) {
            this.list = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            this.list[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Bounds");
        }
        return this.list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Bounds");
        }
        this.list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Bounds");
        }
        T element = this.list[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.list,index + 1,this.list,index,numMoved);
        }
        this.list[--size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (this.list[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(this.list[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        int oldCapacity = list.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // Increase by 50%
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        }
        if (newCapacity < oldCapacity) {
            throw new OutOfMemoryError("Cannot grow array beyond maximum capacity");
        }
        T[] result = (T[]) new Object[newCapacity];
        System.arraycopy(this.list, 0, result, 0, size);
        return result;
    }

}
