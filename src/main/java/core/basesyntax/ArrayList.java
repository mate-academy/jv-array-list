package core.basesyntax;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private T[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        elementData = (T[]) new Object[initialCapacity];

    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            size++;
            elementData = Arrays.copyOf(elementData, size*2);
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0){ throw new ArrayListIndexOutOfBoundsException("index out of bounds"); }
        if (index == size) {
            size++;
        elementData[index] = value;}
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) { throw new NullPointerException(); }
        if (list.isEmpty()) { return; }
        if (list.size() > elementData.length) {
            size = size + list.size();
        }
        for (int i = elementData.length; i < list.size(); i++) {
            elementData[i] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new ArrayListIndexOutOfBoundsException("Index not exists" + index);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size){
            throw new ArrayListIndexOutOfBoundsException("index out of bounds");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size){
            throw new ArrayListIndexOutOfBoundsException("index out of bounds");
        }

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        return this.get(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element) {
                elementData[i] = element;
            }
        }
        return this.get(elementData.length);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return elementData.length == 0 && elementData.equals(null);
    }
}
