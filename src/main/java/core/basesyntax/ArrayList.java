package core.basesyntax;


import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int BEGINNER_CAPACITY = 10;
    private Object[] list;
    private int realCapacity;
    private int size;

    public ArrayList() {
        this.list = new Object[BEGINNER_CAPACITY];
        this.realCapacity = BEGINNER_CAPACITY;
    }

    @Override
    public void add(T value) {
        newSize();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < list.length-1){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        newSize();
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return (T)list[index];
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T t) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    private void newSize(){
        if (size() == list.length){
            realCapacity = realCapacity * 3 /2;
        }
        list = Arrays.copyOf(list, realCapacity);
    }
}
