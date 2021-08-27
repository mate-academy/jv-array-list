package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private boolean arrayIsEmpty = true;
    private T[] arrayData;
    private int size;
    private int currentSizeOfArray = INITIAL_SIZE;

    @SuppressWarnings({"unchecked"})
    public ArrayList() {
        arrayData = (T[]) new Object[INITIAL_SIZE];
        currentSizeOfArray = INITIAL_SIZE;
    }

    @Override
    public void add(T value) {
        if (checkForEmpty(value)) {
            if (size < currentSizeOfArray - 1) {
                arrayData[size] = value;
            }
        }
    }

    private boolean checkForEmpty(T value) {
        if (arrayIsEmpty) {
            arrayData[0] = value;
            size++;
            arrayIsEmpty = false;
           return false;
        }
        return true;
    }

    @Override
    public void add(T value, int index) {
        if (checkForEmpty(value)) {
            if (index < currentSizeOfArray
            && index == size) {
                arrayData[index] = value;
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {

        }
    }

    @Override
    public T get(int index) {
        if(index < size) {
            return arrayData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("element are not exist, please input right index");
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
        return size <= 0;
    }
}
