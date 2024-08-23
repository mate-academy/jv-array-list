package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = new Object[]{};

    }

    public ArrayList(int customCapacity) {
        if (customCapacity > 0) {
            this.elementData = new Object[customCapacity];
        } else if (customCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new RuntimeException("Impossible capacity: "
                    + customCapacity);
        }
    }

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
