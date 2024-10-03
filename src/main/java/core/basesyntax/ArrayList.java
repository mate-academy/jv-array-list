package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] arr;

    public ArrayList() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size < arr.length) {
            arr[size] = value;
            size++;
        } else {
            resize();
            arr[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        if (index == arr.length || size >= arr.length) {
            resize();
        }

        T[] newArr = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, newArr, 0, size);
        newArr[index] = value;
        System.arraycopy(arr, index, newArr, index + 1, size - index);
        System.arraycopy(newArr, 0, arr, 0, newArr.length);
        if (index >= 0 && index <= size) {
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        T[] newArr;
        T result = null;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        result = arr[index];
        newArr = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);
        System.arraycopy(newArr, 0, arr, 0, arr.length);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element || arr[i] != null && arr[i].equals(element)) {
                remove(i);
                break;
            }
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void resize() {
        int size = this.size + (this.size / 2); // or indexOfLastCharacter >> 1
        T[] newArr = (T[]) new Object[size];
        System.arraycopy(arr, 0, newArr, 0, this.size);
        arr = (T[]) new Object[size];
        System.arraycopy(newArr, 0, arr, 0, size);
    }
}
