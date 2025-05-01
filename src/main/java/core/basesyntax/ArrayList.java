package core.basesyntax;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity = 0;
    private int maxCapacity = DEFAULT_CAPACITY;
    private Object[] listArray = new Object[DEFAULT_CAPACITY];

    public void increaseArray() {
        maxCapacity = (int) (maxCapacity * 1.5);
        Object[] newListArray = new Object[maxCapacity];
        System.arraycopy(listArray, 0, newListArray, 0, currentCapacity);
        listArray = newListArray;
    }

    @Override
    public void add(T value) {
        if (currentCapacity == maxCapacity) {
            increaseArray();
        }
        listArray[currentCapacity] = value;
        currentCapacity++;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentCapacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Array list index out of bounds");
        }
        if (currentCapacity == maxCapacity) {
            increaseArray();
        }
        for (int i = currentCapacity; i > index; i--) {
            listArray[i] = listArray[(i - 1)];
        }
        listArray[index] = value;
        currentCapacity++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (currentCapacity == maxCapacity) {
                increaseArray();
            }
            listArray[currentCapacity] = list.get(i);
            currentCapacity++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= currentCapacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Array list index out of bounds");
        }
        return (T) listArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= currentCapacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Array list index out of bounds");
        }
        listArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= currentCapacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Array list index out of bounds");
        }

        final T removedElement = (T) listArray[index];
        for (int i = index; i < currentCapacity - 1; i++) {
            listArray[i] = listArray[i + 1];
        }
        listArray[currentCapacity - 1] = null;
        currentCapacity--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int neededIndex = -1;
        for (int i = 0; i < currentCapacity; i++) {
            if (element == null ? listArray[i] == null : element.equals(listArray[i])) {
                neededIndex = i;
                break;
            }
        }
        if (neededIndex != -1) {
            remove(neededIndex);
        } else {
            throw new java.util.NoSuchElementException("There is no such element present");
        }
        return element;
    }

    @Override
    public int size() {
        return currentCapacity;
    }

    @Override
    public boolean isEmpty() {
        return currentCapacity == 0;
    }
}
