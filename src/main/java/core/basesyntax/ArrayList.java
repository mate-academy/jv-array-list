package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    //size показывает сколько элементов заполнено
    private int size;


    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Argument to create array");
        }
        elements = new Object[initCapacity];
    }


    @Override
    public void add(T value) {
        resizeIfFull();
        elements[size] = value;
        size++;
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

    private void resizeIfFull() {
        //если массив полон
        //создай новый массив x1,5 и положи в него старый
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + (elements.length >> 2)];
            //arraycopy берет кусок памяти и вставляет куда мы скажем
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

}
