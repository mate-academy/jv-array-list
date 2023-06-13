package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;

    private Object[] internalStorage;
    private int size;

    public ArrayList() {
        this.internalStorage = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == internalStorage.length) {
            grow();
        }
        internalStorage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        addingIndexIsValid(index);
        if (index == internalStorage.length) {
            grow();
        }
        System.arraycopy(internalStorage, index, internalStorage, index + 1, size - index);
        internalStorage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
//        if (size + list.size() > internalStorage.length) {
//            grow();
//        }
//        System.arraycopy(list, 0, internalStorage, size, list.size() - 1);
//        size += list.size();
    }

    @Override
    public T get(int index) {
        indexIsValid(index);
        return (T) internalStorage[index];
    }

    @Override
    public void set(T value, int index) {
        indexIsValid(index);
        internalStorage[index] = value;
    }

    @Override
    public T remove(int index) {
        indexIsValid(index);
        Object oldValue = internalStorage[index];
        if (index < size - 1) {
            System.arraycopy(internalStorage, index + 1, internalStorage, index, size - 1 - index);
        }
        internalStorage[size - 1] = null;
        size--;
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void indexIsValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index);
        }
    }

    private void addingIndexIsValid(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index);
        }
    }

    //need to add all the checks, so it`s better to get a single method for that
    private int indexOf(T element) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (internalStorage[i].equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void grow() {
        int newSize = internalStorage.length + internalStorage.length >> 1;
        Object[] newStorage = new Object[newSize];
        System.arraycopy(internalStorage, 0, newStorage, 0, internalStorage.length);
        internalStorage = newStorage;
    }
}
