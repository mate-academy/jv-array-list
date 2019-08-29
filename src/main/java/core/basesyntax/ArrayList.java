package core.basesyntax;

/**
 * Реалізувати свій ArrayList, який імплементує інтерфейс List
 */
public class ArrayList<T> implements List<T> {
    private Object[] arrayList = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        checkSize();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        checkSize();
        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < size; i++) {
            set((T) arrayList[i + 1], i);
        }
        return (T) arrayList[size--];
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    private void checkSize() {
        if (size == arrayList.length) {
            Object[] newArray = new Object[size + (size >> 1)];
            System.arraycopy(arrayList, 0, newArray, 0, size);
            arrayList = newArray;
        }
    }
}
