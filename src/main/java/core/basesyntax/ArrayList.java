package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] arrayList;
    private int size;
    private int actualSize = DEFAULT_SIZE;

    public ArrayList() {
        this.arrayList = new Object[actualSize];
    }

    public void actualSize() {
        if (this.size <= DEFAULT_SIZE) {
            this.actualSize = DEFAULT_SIZE;
        }
        if (this.size <= actualSize && this.size > DEFAULT_SIZE) {
            this.actualSize = (int) (this.size * 1.5);
        }
    }

    @Override
    public void add(T value) {
        add(value, this.size);
    }

    @Override
    public void add(T value, int index) {
        if (index > this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("illegal index");
        }
        int newSize = this.size;
        Object[] arrayList = this.arrayList;
        if (newSize == arrayList.length) {
            //System.arraycopy(arrayList, arrayList.length, );
            arrayList = Arrays.copyOf(arrayList, (int) (newSize * 1.5));
        }
        System.arraycopy(arrayList, index,
                arrayList, index + 1, newSize - index);
        arrayList[index] = value;
        this.size = newSize + 1;
        this.arrayList = arrayList;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = this.size;
        Object[] arrayList = this.arrayList;
        if (list.size() > arrayList.length - newSize) {
            arrayList = new Object[newSize + list.size()];
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        this.arrayList = arrayList;
    }

    @Override
    public T get(int index) {
        if (index > this.size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("illegal index");
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > this.size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("illegal index");
        }
        this.arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > this.size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("illegal index");
        }
        T removed = (T) arrayList[index];
        int newSize = this.size - 1;
        Object[] arrayList = this.arrayList;
        if (newSize > index && newSize != 0 && index != 0) {
            System.arraycopy(arrayList, index + 1, arrayList, index, newSize - 1);
            this.size = newSize;
            this.arrayList = arrayList;
            return removed;
        }
        if (newSize > index && index == 0) {
            System.arraycopy(arrayList, 1,
                    arrayList, 0, newSize);
            arrayList[newSize] = null;
            this.size = newSize;
            this.arrayList = arrayList;
            return removed;
        }
        if (newSize == 0 && index == 0) {
            this.arrayList = new Object[this.actualSize];
            this.size = newSize;
            return removed;
        }
        if (newSize == index) {
            //arrayList[index - 1] = arrayList[index];
            arrayList[index] = null;
            this.size = newSize;
            this.arrayList = arrayList;
            return removed;
        } else {
            throw new ArrayListIndexOutOfBoundsException("illegal index");
        }
    }

    @Override
    public T remove(T element) {
        T removed;
        int s = size;
        Object[] arrayList = this.arrayList;
        for (int i = 0; i < arrayList.length; i++) {
            if (element == null && arrayList[i] == null) {
                s = this.size - 1;
                removed = (T)arrayList[i];
                System.arraycopy(arrayList, i + 1, arrayList, i, s - 1);
                arrayList[s] = null;
                this.size = s;
                this.arrayList = arrayList;
                return removed;
            }
            if (element != null && arrayList[i] != null && arrayList[i].equals(element)) {
                s = this.size - 1;
                removed = (T) arrayList[i];
                System.arraycopy(arrayList, i + 1, arrayList, i, s - 1);
                arrayList[s] = null;
                this.size = s;
                this.arrayList = arrayList;
                return removed;
            }
        }
        throw new NoSuchElementException("error");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
