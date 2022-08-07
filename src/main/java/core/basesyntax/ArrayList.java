package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String NULL_MARKER = "__Null_Value__";
    private Object[] objects = new Object[0];

    @Override
    public void add(T value) {
        Object[] temp = new Object[objects.length];
        System.arraycopy(objects, 0, temp, 0, objects.length);
        objects = new Object[temp.length + 1];
        System.arraycopy(temp, 0, objects, 0, temp.length);
        objects[temp.length] = value == null ? (T) NULL_MARKER : value;
        trimToSize();
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist: " + index);
        }
        if (index == size()) {
            add(value);
            return;
        }
        trimToSize();
        Object[] res = new Object[size() + 1];
        if (index == 0) {
            res[0] = value == null ? (T) NULL_MARKER : value;
        } else {
            System.arraycopy(objects, 0, res, index - 1, index);
            res[index] = value == null ? (T) NULL_MARKER : value;
        }
        System.arraycopy(objects, index, res, index + 1, objects.length - index);
        objects = new Object[size() + 1];
        System.arraycopy(res, 0, objects, 0, res.length);
    }

    private void trimToSize() {
        int endOfArrayIndex = 0;
        boolean trimNeeded = false;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                endOfArrayIndex = i;
                trimNeeded = true;
                break;
            }
        }
        if (trimNeeded) {
            Object[] shortArray = new Object[endOfArrayIndex];
            System.arraycopy(objects, 0, shortArray, 0, endOfArrayIndex);
            objects = new Object[endOfArrayIndex];
            System.arraycopy(shortArray, 0, objects, 0, endOfArrayIndex);
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
            add((T) arr[i]);
        }
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
        return (T) (objects[index] == NULL_MARKER ? null : objects[index]);
    }

    @Override
    public void set(T value, int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
        objects[index] = value == null ? (T) NULL_MARKER : value;
    }

    @Override
    public T remove(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
        T deletedObject = (T) objects[index];
        if (index == objects.length - 1) {
            objects[index] = null;
        } else {
            if (objects.length - (index + 1) >= 0) {
                System.arraycopy(objects, index + 1, objects,
                        index + 1 - 1, objects.length - (index + 1));
            }
            objects[objects.length - 1] = null;
        }
        trimToSize();
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        boolean elementFound = false;
        T removedObject = null;
        if (element == null) {
            element = (T) NULL_MARKER;
        }
        for (int i = 0; i < objects.length; i++) {
            if (Objects.equals(objects[i], element)) {
                removedObject = remove(i);
                elementFound = true;
                break;
            }
        }
        if (elementFound) {
            return removedObject == NULL_MARKER ? null : removedObject;
        } else {
            throw new NoSuchElementException("No such element in list: "
                    + (element == NULL_MARKER ? null : element));
        }
    }

    @Override
    public int size() {
        trimToSize();
        return objects.length;
    }

    @Override
    public boolean isEmpty() {
        return objects.length == 0 || objects[0] == null;
    }
}
