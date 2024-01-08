package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STARTING_CAPACITY = 10;
    private static final double INCREASE_FACTOR = 1.5;

    private T[] frame;
    private int size;

    public ArrayList() {
        frame = (T[]) new Object[STARTING_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        frame[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        checkCapacity();

        System.arraycopy(frame, index, frame, index + 1, size - index);

        frame[index] = value;
        size++;
    }

    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

        @Override
        public T get( int index){
            checkIndex(index);
            return frame[index];
        }

        @Override
        public void set(T value,int index){
            checkIndex(index);
            frame[index] = value;
        }

        @Override
        public T remove( int index){
            checkIndex(index);
            T removedElement = frame[index];
            System.arraycopy(frame, index + 1, frame, index, size - index - 1);
            frame[--size] = null;
            return removedElement;
        }

        @Override
        public T remove(T element){
        int index = findindexOf(element);
            if (index == -1) {
                throw new NoSuchElementException(element + " not found");
            }
            return remove(index);
        }

        @Override
        public int size () {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

    private void checkCapacity() {
        if (size == frame.length) {
            int newCapacity = (int) (frame.length * INCREASE_FACTOR);
            T[] newArray = (T[]) new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newArray[i] = frame[i];
            }
            frame = newArray;
        }
    }

    private void checkIndex( int index){
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private int findindexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? frame[i] == null : element.equals(frame[i])) {
                return i;
            }
        }
        return -1;
    }
}
