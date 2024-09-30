package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int indexOfLastCharacter = 0;
    private T[] arr;

    public ArrayList() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (indexOfLastCharacter < DEFAULT_CAPACITY) {
            arr[indexOfLastCharacter] = value;
            indexOfLastCharacter++;
        } else{
           resize();
            arr[indexOfLastCharacter] = value;
            indexOfLastCharacter++;
        }
    }


    @Override
    public void add(T value, int index) {
        T[] newArr;
        T[] newArr2;
        try {
            newArr = (T[]) new Object[DEFAULT_CAPACITY];
            newArr2 = (T[]) new Object[DEFAULT_CAPACITY];
            System.arraycopy(arr, 0, newArr, 0, index);
            if (index >= 0 && index < indexOfLastCharacter) {
                newArr[index] = value;
                System.arraycopy(arr, 0, newArr, index + 1, DEFAULT_CAPACITY - index + 1);
                System.arraycopy(newArr, 0, arr, 0, DEFAULT_CAPACITY);
                indexOfLastCharacter++;
            }
            if (index == indexOfLastCharacter) {
                arr[indexOfLastCharacter] = value;
                indexOfLastCharacter++;
            } else {
                resize();
                arr[index] = value;
                indexOfLastCharacter++;
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + e);
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
        if (index >= 0 && index < indexOfLastCharacter) {
            return arr[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no a such index");
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            if (index >= 0 && index < indexOfLastCharacter) {
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
            if (index >= 0 && index < indexOfLastCharacter) {
                result = arr[index];
                newArr = (T[]) new Object[DEFAULT_CAPACITY];
                newArr2 = (T[]) new Object[DEFAULT_CAPACITY];
                System.arraycopy(arr, 0, newArr, 0, index);
                System.arraycopy(arr, index + 1, newArr, index, DEFAULT_CAPACITY - index + 1);
                System.arraycopy(newArr, 0, arr, 0, index);
                System.arraycopy(arr, 0, newArr2, index, DEFAULT_CAPACITY - index + 1);
                indexOfLastCharacter--;
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
                    indexOfLastCharacter--;
                }
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + e);
        }
        return element;
    }

    @Override
    public int size() {
        return indexOfLastCharacter;
    }

    @Override
    public boolean isEmpty() {
        if (arr[0] == null) {
            return true;
        }
        return false;
    }

    public void resize (){
        int size = indexOfLastCharacter + (indexOfLastCharacter / 2); // or indexOfLastCharacter >> 1
        T[] newArr  = (T[]) new Object[size];
        System.arraycopy(arr, 0, newArr, 0, indexOfLastCharacter);
        T[] arr  = (T[]) new Object[size];
        System.arraycopy(newArr, 0, arr, 0, size);
    }
}
