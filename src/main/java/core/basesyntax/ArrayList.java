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
        if (size < DEFAULT_CAPACITY) {
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
        if (size >= arr.length) {
            resize();
        }
        if(index >= 0 && index <= size) {
            size++;
        }
        T[] newArr = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, newArr, 0, size);
        newArr[index] = value;
        System.arraycopy(arr, index, newArr, index + 1, size - index);
        System.arraycopy(newArr, 0, arr, 0, newArr.length);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index <= size) {
            return arr[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no a such index");
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            if (index >= 0 && index < size) {
                arr[index] = value;
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + e);
        }

    }

    @Override
    public T remove(int index) {
        T[] newArr;
        T[] newArr2;
        T result = null;
        try {
            if (index >= 0 && index < size) {
                result = arr[index];
                newArr = (T[]) new Object[arr.length];
                newArr2 = (T[]) new Object[arr.length];
                System.arraycopy(arr, 0, newArr, 0, index);
                System.arraycopy(arr, index + 1, newArr, index, DEFAULT_CAPACITY - index + 1);
                System.arraycopy(newArr, 0, arr, 0, index);
                System.arraycopy(arr, 0, newArr2, index, DEFAULT_CAPACITY - index + 1);
                size--;
                return result;
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + e);
        }
        return result;
    }

    @Override
    public T remove(T element) {
        T[] newArr;
        T[] newArr2;
        try {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == element || arr[i].equals(element)) {
                    newArr = (T[]) new Object[DEFAULT_CAPACITY];
                    newArr2 = (T[]) new Object[DEFAULT_CAPACITY];
                    System.arraycopy(arr, 0, newArr, 0, i);
                    System.arraycopy(arr, i + 1, newArr, i, DEFAULT_CAPACITY - i + 1);
                    System.arraycopy(newArr, 0, arr, 0, i);
                    System.arraycopy(arr, 0, newArr2, i, DEFAULT_CAPACITY - i + 1);
                    size--;
                }
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + e);
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (arr[0] == null) {
            return true;
        }
        return false;
    }

    public void resize() {
        int size = this.size + (this.size / 2); // or indexOfLastCharacter >> 1
        T[] newArr = (T[]) new Object[size];
        System.arraycopy(arr, 0, newArr, 0, this.size);
        T[] arr = (T[]) new Object[size];
        System.arraycopy(newArr, 0, arr, 0, size);
    }
}
