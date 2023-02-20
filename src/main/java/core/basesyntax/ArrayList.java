package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arr = new Object[DEFAULT_CAPACITY];
    private int marker = 0;

    @Override
    public void add(T value) {
        if (marker == arr.length - 1) {
            resizeOldArr(arr.length * 2);
        }
        for (int i = 0; i < marker; i++) {
            if (equals(arr[i], value)) {
                arr[marker] = value;
            }
        }
        arr[marker++] = value;
    }

    private void resizeOldArr(int newSizeOfArr) {
        Object[] newArr = new Object[newSizeOfArr];
        System.arraycopy(arr, 0, newArr, 0, marker);
        arr = newArr;
    }

    @Override
    public void add(T value, int index) {
        if (marker == arr.length - 1) {
            resizeOldArr(arr.length * 2);
        }
        if (index < marker) {
            for (int i = index; i < marker; i++) {
                arr[i] = arr[i++];
                arr[index] = null;
                arr[index] = value;
            }

        }

    }



    @Override
    public void addAll(List<T> list) {
        if (marker == arr.length - 1) {
            resizeOldArr(arr.length * 2);
        }
        for (int i = 0; i < list.size(); i++) {
            arr[marker++] = get(i);
            if (marker == arr.length - 1) {
                resizeOldArr(arr.length * 2);
            }
        }
    }

    @Override
    public T get(int index) {
        try {
            return (T) arr[index];
        } catch (ArrayListIndexOutOfBoundsException exception) {
            throw new ArrayListIndexOutOfBoundsException("Insert index is not valid. Please try again");
        }
    }

    @Override
    public void set(T value, int index) {
        if (marker == arr.length - 1) {
            resizeOldArr(arr.length * 2);
        }
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        Object oldValue = arr[index];
        if (index == 0) {
            return (T) arr[0];
        } else if (index == marker -1) {
            return (T) arr[marker - 1];
        }
        for (int i = 1; i < marker - 1; i++) {
                arr[i] = arr[i--];
            }
            return (T) oldValue;
        }

    @Override
    public T remove(T element) {
        for (int i = 0; i < marker; i++) {
            if (arr[i] == element) {
                return element;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return marker;
    }

    @Override
    public boolean isEmpty() {
        if (marker == 0) {
            return true;
        }
        return false;
    }
    private boolean equals(Object value1, Object value2) {
        return (value1 == value2) || (value1 != null && value1.equals(value2));
    }
}
