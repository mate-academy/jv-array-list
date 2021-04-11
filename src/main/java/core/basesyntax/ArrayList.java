package core.basesyntax;

public class ArrayList<T> implements List<T> {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("Mate");
        arrayList.add("Academy", 1);
        System.out.println("for " + arrayList.get(2));
    }

    //    it should have the default capacity, the internal array should grow 1.5 times when it is full, etc.
    int capacity;
    T[] array;
    int size;

    public ArrayList() {
        this.capacity = 10;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (this.size == this.capacity) {
            this.capacity = this.capacity + this.capacity / 2;
            T[] copyArray = (T[]) new Object[this.capacity];
            for (int i = 0; i < this.array.length; i++) {
                copyArray[i] = this.array[i];
            }
            this.array = (T[]) new Object[this.capacity];
            for (int i = 0; i < copyArray.length; i++) {
                this.array[i] = copyArray[i];
            }
        }
        this.array[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }

        if (this.size == this.capacity) {
            this.capacity = this.capacity + this.capacity / 2;
        }

        T[] copyArray = (T[]) new Object[capacity];
        for (int i = 0; i < copyArray.length; i++) {
            if (i < index) {
                copyArray[i] = this.array[i];
            } else if (i == index) {
                copyArray[i] = value;
            } else {
                copyArray[i] = this.array[i - 1];
            }
        }
        this.array = copyArray;
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (this.array.length + list.size() < this.capacity) {
            this.capacity = this.capacity + this.capacity / 2;
            for (int i = 0; i < list.size(); i++) {
                this.array[this.size] = list.get(i);
                this.size++;
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                this.array[this.size] = list.get(i);
                this.size++;
            }
        }
    }

    @Override
    public T get(int index) {
        return this.array[index];
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
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
