package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;

    private Object[] internalStorage;
    private int size;

    @Override
    public void add(T value) {

    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (size > 0 && index <= size - 1) {
            return (T) internalStorage[index - 1];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        if (size > 0) {
            if (index == size - 1) {
                internalStorage[index] = null;
                size--;
            }
            if (index < size - 1) {
                System.arraycopy(internalStorage, index + 1, internalStorage, index, size - 1 - index);
                size--;
            }
            return (T) internalStorage[index];
        }
        return null;
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
//        if (internalStorage == null || internalStorage.length == 0) {
//            return true;
//        }
//        return false;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (internalStorage[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
