package core.basesyntax;

public class ArrayList<T> implements List<T> {
    static final int DEFAULT_SIZE = 10;
    private Object[] objects;
    //private int newSize = objects.length + (objects.length / 2);
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_SIZE];
        size = 0;

    }

    public void copyArray(Object[] objects, Object[] copiedArray) {
        System.arraycopy(objects, 0, copiedArray, 0, objects.length);
    }

    public void resize() {
        Object[] copied;
        if (size >= objects.length) {
            copyArray(objects, copied = new Object[objects.length + (objects.length / 2)]);
        } else {
            copyArray(objects, copied = new Object[objects.length + 1]);
        }
        objects = copied;
    }

    public void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        try {
            resize();
            objects[size] = value;
            size++;
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element");
        }
    }

    @Override
    public void add(T value, int index) {
        try {
            checkIndex(index);
            if (size >= objects.length) {
                Object[] arrayCopy = new Object[objects.length + (objects.length / 2)];
                System.arraycopy(objects, 0, arrayCopy, 0, objects.length);
                objects = arrayCopy;
            }
            System.arraycopy(objects, index + 1, objects, index + 2, size - index - 1);
            objects[index] = value;
            size++;
        } catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public void addAll(List<T> list) {
        try {
            if (!list.isEmpty()) {
                if (size + list.size() >= objects.length) {
                    resize();
                }
                for (int i = 0; i <= objects.length; i++) {
                    objects[size] = list.get(i);
                }
                size += list.size();
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public T get(int index) {
        try {
            checkIndex(index);
            return (T) objects[index];
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            checkIndex(index);
            for (int i = 0; i < objects.length; i++) {
                if (i == index) {
                    objects[i] = value;
                }
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't find an element");
        }

    }

    @Override
    public T remove(int index) {
        try {
            checkIndex(index);
            int i = index;
            T removed = (T) objects[i];
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
            objects[size - 1] = null;
            size--;
            return removed;
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can 't find an element");
        }

    }

    @Override
    public T remove(T element) {
        /*if (size != 0) {
            try {

            }
        }*/
        return null;
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
