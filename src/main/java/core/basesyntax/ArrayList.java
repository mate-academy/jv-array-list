package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkArrayCapacityandGrow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        checkArrayCapacityandGrow();
        System.arraycopy(elementData, index, elementData,
                (index + 1), elementData.length - index - 1);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        IntStream.range(0, list.size())
                .mapToObj(list::get)
                .forEach(this::add);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex((index));
        T temp = elementData[index];
        System.arraycopy(elementData,(index + 1),
                elementData, index, elementData.length - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size;i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in current array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkArrayCapacityandGrow() {
        if (size >= elementData.length) {
            T[] temp = elementData;
            elementData = (T[]) new Object[elementData.length + (elementData.length / 2)];
            System.arraycopy(temp, 0, elementData, 0, temp.length);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value(Out of bound). "
                    + "Index should be not less than 0 and not more than " + (size - 1) + ".");
        }
    }
}
