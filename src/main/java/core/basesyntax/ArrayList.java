package core.basesyntax;


public class ArrayList<T> implements List<T> {
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData;
    private static final int DEFAULT_CAPACITY = 10;


    public ArrayList() {this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;}

    //private Object[] grow() {
    //    return grow(size + 1);
    //}
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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
