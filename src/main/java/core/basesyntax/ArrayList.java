package core.basesyntax;

public class ArrayList<T> implements List<T> {

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
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
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
}
