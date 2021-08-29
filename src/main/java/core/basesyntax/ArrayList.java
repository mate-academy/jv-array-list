package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private Object[] arrayList;
    int size;

    public ArrayList() {
        arrayList = new Object[INITIAL_ARRAY_SIZE];
        size = 0;
    }


    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resize();
        }
        arrayList[size] = value;
        size++;
    }



    @Override
    public void add(T value, int index) {
        System.out.println(" size:" + size + " index: " + index + " value: " + value + " ArrayLength: " + arrayList.length);
        if (index < 0 || index + 1 > size) throw new ArrayListIndexOutOfBoundsException("Wrong index");
        if (index + 1 < size) {
            arrayShift(index, +1);
            arrayList[index] = value;
        }
        if (index == size) {
            size++;
        }
    }



    @Override
    public void addAll(List<T> list) {
        Object[] newArrayList = new Object[10];
        System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
        arrayList = newArrayList;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index + 1 > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index + 1 > size) throw new ArrayListIndexOutOfBoundsException("Wrong index");
        if (index + 1 <= size) {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T returnValue;
        if (index < 0 || index + 1 > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (index + 1 == size) {
            returnValue = (T) arrayList[index];
            arrayList[index] = null;
            size--;
            return returnValue;
        }
        //throw  new NoSuchElementException("Value wasn't found");
        return null;
    }

    @Override
    public T remove(T element) {
        T returnValue;
        for (int i = 0; i < size; i++) {
            if (element.equals((T) arrayList[i])) {
                returnValue = (T) arrayList[i];
                arrayList[i] = null;
                size--;
                return returnValue;
            }
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

    private void resize() {
        Object[] newArrayList = new Object[arrayList.length + (int) (arrayList.length * 1.5)];
        System.arraycopy(arrayList,0,newArrayList,0,arrayList.length);
        arrayList = newArrayList;
    }

    private void arrayShift(int index, int value) {
        Object[] newArrayList = new Object[(int) (arrayList.length * 1.5)];
        System.arraycopy(arrayList, 0, newArrayList, 0, index + 1);
        System.arraycopy(arrayList, index, newArrayList, index + value, size - index);
        arrayList = newArrayList;
        size += value;
    }
}
