package core.basesyntax;

public class ArrayList<T> implements List<T> {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add(null);
        arrayList.add("for");
        arrayList.add("Mate");

        System.out.println(arrayList.size());
    }

    //    it should have the default capacity, the internal array should grow 1.5 times when it is full, etc.
    int capacity = 10;
    T[] array = (T[]) new Object[capacity];
    int size = 0;

    @Override
    public void add(T value) {
        if (array.length < size) {
            capacity = capacity + capacity / 2;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (array.length < size) {
            capacity = capacity + capacity / 2;
        }
        T[] copyArray = (T[]) new Object[capacity];
        for (int i = 0; i < copyArray.length; i++) {
            if (i < index) {
                copyArray[i] = array[i];
            } else if (i == index) {
                copyArray[i] = value;
            } else {
                copyArray[i] = array[i - 1];
            }
        }
        array = copyArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return array[index];
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
        return false;
    }
}
