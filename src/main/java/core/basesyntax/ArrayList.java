package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private boolean arrayIsEmpty = true;
    private T[] arrayData;
    private int size;
    private int currentSizeOfArray = INITIAL_SIZE;
    private static final int INITIAL_SRC_POS = 0;
    private static final double GROW_SIZE = 1.5;

    @SuppressWarnings({"unchecked"})
    public ArrayList() {
        arrayData = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        if (checkForEmpty(value)) {
            if (!ensureCapacity()) {
                grow();
            }
            arrayData[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (checkForEmpty(value)) {
            if (index < currentSizeOfArray
            && index < size && ensureCapacity()) {
                copyValues(index);
                arrayData[index] = value;
                size++;
            } else if (index > currentSizeOfArray || index > size){
                throw new ArrayListIndexOutOfBoundsException("Index are not exist," +
                        " please input right index");
            } else if(index == size && ensureCapacity()) {
                arrayData[index] = value;
                size++;
            }
            grow();
            copyValues(index);
            arrayData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() >= currentSizeOfArray) {
            grow();
            }
        for (int i = 0; i < list.size(); i++) {
            arrayData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if(index < size) {
            return arrayData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index are not exist, please input right index");
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index are not exist, please input right index");
        }
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < size) {

        }
        throw new ArrayListIndexOutOfBoundsException("index are not exist, please input right index");
    }

    @Override
    public T remove(T element) {
        if (index >= size) {
            throw new NoSuchElementException("Element are not exist");
        }
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

    @SuppressWarnings({"unchecked"})
    private void grow() {
        currentSizeOfArray *= GROW_SIZE;
        T[] arrayDataTemp = (T[]) new Object[currentSizeOfArray];
        System.arraycopy(arrayData, INITIAL_SRC_POS,
                arrayDataTemp, INITIAL_SRC_POS, arrayData.length);
        arrayData = arrayDataTemp;
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

    private void copyValues(int index) {
        System.arraycopy(arrayData, index, arrayData, index++, size - index);
    }

    private boolean ensureCapacity() {
        return (size + 1) < currentSizeOfArray;
    }
}
