package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] arrayList;
    private int size = 0;
    private int emptyPlaces = 10;
    private final double ARRAY_ENLARGEMENT_PERCENTAGE = 0.5;

    public ArrayList() {
        arrayList = (T[]) new Object[emptyPlaces];
    }

    public void enlarge() {
        emptyPlaces = (int) (size * ARRAY_ENLARGEMENT_PERCENTAGE);
        T[] temporaryEnlargedArrayList = (T[]) new Object[size + emptyPlaces];
        for (int i = 0; i < size; i++) {
            temporaryEnlargedArrayList[i] = arrayList[i];
        }
        arrayList = (T[]) new Object[size + emptyPlaces];
        for (int i = 0; i < size; i++) {
            arrayList[i] = temporaryEnlargedArrayList[i];
        }
    }

    @Override
    public void add(T value) {
        if (emptyPlaces == 0) {
            enlarge();
        }
        arrayList[size] = value;
        emptyPlaces--;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            if (index == size) {
                add(value);
            } else {
                T[] temporaryArrayList = (T[]) new Object[size - index];
                for (int i = index; i < size; i++) {
                    temporaryArrayList[i - index] = arrayList[i];
                }

                set(value, index);

                for (int i = 0; i < temporaryArrayList.length - 1; i++) {
                    set(temporaryArrayList[i], index + i + 1);
                }

                add(temporaryArrayList[temporaryArrayList.length - 1]);
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            return arrayList[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            final T removedElement = arrayList[index];

            if (index != (size - 1)) {
                T[] temporaryArrayList = (T[]) new Object[size - index - 1];

                for (int i = index + 1; i < size; i++) {
                    temporaryArrayList[i - index - 1] = arrayList[i];
                }
                for (int i = 0; i < temporaryArrayList.length; i++) {
                    arrayList[index + i] = temporaryArrayList[i];
                }
            }
            arrayList[size - 1] = null;
            size--;
            emptyPlaces++;
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        T removedElementByEquals = null;
        for (int i = 0; i < size; i++) {
            if ((element != null && arrayList[i] != null && arrayList[i].equals(element))
                    || element == arrayList[i]) {
                index = i;
                removedElementByEquals = arrayList[i];
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("element does not exist");
        } else {
            remove(index);
        }
        return removedElementByEquals;
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
