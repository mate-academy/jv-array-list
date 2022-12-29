package core.basesyntax;

public class ArrayList<T> implements List<T> {
    static final int ARRAY_MIN_SIZE = 10;

    T[] array = (T[]) new Object[ARRAY_MIN_SIZE];
    private int size;

    @Override
    public void add(T value) {
            if (size == array.length) {
                array = (T[]) grow();
                array[size] = value;
                size = size + 1;
            }
        }

    private Object[] grow() {
        int newCapacity = array.length + (array.length >> 1);
        Object[] arrayGrow = new Object[newCapacity];
        System.arraycopy(array, 0, arrayGrow, 0, size);
        return arrayGrow;
    }

    private boolean isIndexValid(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("This index is missing");
    }

    @Override
    public void add(T value, int index) {
        if (size < array.length) {
            if (isIndexValid(index)) {
                System.arraycopy(array, index, array, size - 1, array.length - index);
                array[index] = value;
                size++;
            }
        }else array = (T[]) grow();
    }

    @Override
    public void addAll(List<T> list) {
        if(list.size() <= array.length) {
            for (int i = 0; i < array.length; i++) {
                add(list.get(i), i);
                size++;
            }
        }
        array = (T[]) grow();
    }

    @Override
    public T get(int index) {
        if (isIndexValid(index)) {
                    return array[index];
                }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index)) {
                    array[index] = value;
                }
    }

    @Override
    public T remove(int index) {
        if (isIndexValid(index)) {
            System.arraycopy(array, index + 1, array, size - 1, array.length - index - 1);
                    size--;
                }
        return null;
    }


    @Override
    public T remove(T element) {
        int i = 0;
        do {
            i++;
        }while (array[i].equals(element) && i < array.length);
                if (array[i].equals(element)) {
                    System.arraycopy(array, i + 1, array, size - 1, array.length - i - 1);
                    size--;
                }
                if (i == array.length -1) {
                    throw new NoSuchElementException("This element is missing");
                }
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
}
