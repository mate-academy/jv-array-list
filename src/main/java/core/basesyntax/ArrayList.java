package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private int size;
    private static final int CAPACITY = 10;
    private Object[] objects = new Object[CAPACITY];

    private void resize() {
        if (size >= objects.length) {
            Object[] resObjects = new Object[objects.length * 3 / 2];
            System.arraycopy(objects, 0, resObjects, 0, size);
            objects = resObjects;
        }
    }

    @Override
    public void add(T value) {
        resize();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || size < 0) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        resize();
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= objects.length) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            objects[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || size < 0) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        Object[] tempArray = new Object[objects.length];
        for (int i = 0; i < objects.length; i++) {
            tempArray[i] = objects[i];
        }
        for (int i = 0; i < objects.length - 1; i++) {
            if (i < index) {
                objects[i] = tempArray[i];
            } else {
                objects[i] = tempArray[i + 1];
            }
        }
        size--;
        return (T) tempArray[index];
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        Object[] tempArray = new Object[objects.length];
        int count = 0;
        int index = 0;
        for (int i = 0; i < objects.length; i++) {
            tempArray[i] = objects[i];
            if (t.equals(objects[i])) {
                count++;
            }
        }
        if (count == 0) {
            throw new ArrayIndexOutOfBoundsException("java.lang.ArrayIndexOutOfBoundsException");
        }
        for (int i = 0; i < tempArray.length; i++) {
            if (t.equals(objects[i])) {
                index = i;
            }
        }
        for (int i = 0; i < tempArray.length - 1; i++) {
            if (i < index) {
                objects[i] = tempArray[i];
            } else {
                objects[i] = tempArray[i + 1];
            }
        }
        size--;
        return (T) tempArray[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 0;
    }
}
