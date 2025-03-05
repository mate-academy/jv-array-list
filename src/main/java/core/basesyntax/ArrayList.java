package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] objects = new Object[10];
    private int countIndex = 0;

    @Override
    public void add(T value) {
        if (checkSize(countIndex + 1)) {
            objects[countIndex] = value;
            countIndex++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (!(index < 0) && (index <= countIndex)) {
            if (checkSize(countIndex + 1)) {
                Object[] tempArray = new Object[(objects.length - 1) - index];
                System.arraycopy(objects, index, tempArray, 0, tempArray.length);
                objects[index] = value;
                System.arraycopy(tempArray, 0, objects, index + 1, tempArray.length);
                countIndex++;
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't add a new element");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (checkSize(list.size())) {
            for (int i = 0; i < list.size(); i++) {
                objects[countIndex] = list.get(i);
                countIndex++;
            }
        }
    }

    @Override
    public T get(int index) {
        if (!(index < 0) && (index < countIndex)) {
            return (T) objects[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't get an element");
        }
    }

    @Override
    public void set(T value, int index) {
        if (!(index < 0) && (index < countIndex)) {
            objects[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't set an element");
        }
    }

    @Override
    public T remove(int index) {
        if (!(index < 0) && (index < countIndex)) {
            T t;
            try {
                t = (T) objects[index];
                for (int i = index; i < objects.length - 1; i++) {
                    objects[i] = objects[i + 1];
                }
                objects[objects.length - 1] = null;
                countIndex--;
                return t;
            } catch (Exception e) {
                throw new ArrayListIndexOutOfBoundsException("Can't remove an element by index");
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't remove an element by index");
        }
    }

    @Override
    public T remove(T element) {
        boolean isFound = false;
        for (int i = 0; i < objects.length; i++) {
            if ((element != null && element.equals(objects[i]))
                    || (element == null && objects[i] == null)) {
                remove(i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return countIndex;
    }

    @Override
    public boolean isEmpty() {
        return countIndex <= 0;
    }

    private int grow() {
        int oldCapacity = objects.length;
        objects = Arrays.copyOf(objects,(oldCapacity + (oldCapacity >> 1)));
        return objects.length;
    }

    private boolean checkSize(int size) {
        int newSize = objects.length;
        while ((newSize - countIndex) < size) {
            newSize = grow();
        }
        return true;
    }
}
