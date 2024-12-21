package core.basesyntax;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] arrayList;
    private int size;
    private final int defaultSize = 10;

    public ArrayList() {
        arrayList = new Object[defaultSize];
        size = 0;
    }

    public void ensureCapacity() {
        if (size == arrayList.length) {
            arrayList = Arrays.copyOf(arrayList, arrayList.length + (arrayList.length >> 1));
        }
    }

    public void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > arrayList.length) {
            int newCapacity = Math.max((int) (arrayList.length * 1.5), requiredCapacity);
            arrayList = Arrays.copyOf(arrayList, newCapacity);
        }
    }

    public void ensureIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    public void ensureIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }
    @Override
    public void add(T value) {
        ensureCapacity();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureIndexForAdd(index);
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            arrayList[i + 1] = arrayList[i];
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        ensureIndex(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        ensureIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        ensureIndex(index);
        final T removedElement = (T) arrayList[index];
        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        boolean isExist = false;
        int indexOfElement = 0;
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] == null ? element == null : arrayList[i].equals(element)) {
                isExist = true;
                indexOfElement = i;
                break;
            }
        }
        if (!isExist) {
            throw new NoSuchElementException("There is no such element");
        }
        final T removedElement = (T) arrayList[indexOfElement];
        for (int i = indexOfElement; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[size - 1] = null;
        size--;
        return removedElement;
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
