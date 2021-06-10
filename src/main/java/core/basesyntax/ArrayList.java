package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private E[] objects;
    private int size;
    private boolean growBlock;

    public ArrayList() {
        objects = (E[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public ArrayList(int userCapacity) {
        objects = (E[]) new Object[DEFAULT_CAPACITY];
        capacity = userCapacity;
        growBlock = true;
    }

    @Override
    public void add(E value) {
        add(value, size);
    }

    @Override
    public void add(E value, int index) {
        if (size == capacity) {
            if (growBlock) {
                throw new RuntimeException("Out of ArrayList capacity bound!");
            } else {
                grow();
            }
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no index like that!");
        }
        for (int t = size; t > index; t--) {
            objects[t] = objects[t - 1];
        }
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<E> list) {
        for (int t = 0; t < list.size(); t++) {
            this.add(list.get(t));
        }
    }

    @Override
    public E get(int index) {
        if (size == 0 || index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no index like that!");
        }
        return objects[index];
    }

    public int indexOf(E element) {
        for (int t = 0;t < size;t++) {
            if (element == null) {
                if (objects[t] == null) {
                    return t;
                }
            } else {
                if (element.equals(objects[t])) {
                    return t;
                }
            }
        }
        return -1;
    }

    @Override
    public void set(E value, int index) {
        if (size == 0 || index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no index like that!");
        }
        objects[index] = value;
    }

    private void grow() {
        if (capacity > 1400000000) {
            throw new RuntimeException("So big capacity!");
        }
        E[] valuesBuffer = objects;
        capacity = capacity + (capacity >> 1);
        objects = (E[]) new Object[capacity];
        for (int t = 0; t < size; t++) {
            objects[t] = valuesBuffer[t];
        }
    }

    @Override
    public E remove(int index) {
        if (size == 0 || index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no index like that!");
        }
        E whatIsRemoved;
        if (index == size - 1) {
            whatIsRemoved = get(index);
            size--;
            return whatIsRemoved;
        }
        whatIsRemoved = objects[index];
        for (int t = index; t <= size - 1; t++) {
            if (t == size) {
                break;
            }
            objects[t] = objects[t + 1];
        }
        size--;
        return whatIsRemoved;
    }

    @Override
    public E remove(E element) {
        if (indexOf(element) >= 0) {
            return remove(indexOf(element));
        }
        throw new NoSuchElementException("No such element in ArrayList!");
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
