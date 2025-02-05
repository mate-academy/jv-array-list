package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for length " + size);
        }
        if (size == array.length) {
            increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T element = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                T removedElement = (T) array[i];
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[--size] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for length " + size);
        }
    }

    private void increaseCapacity() {
        int newCapacity = (int) (array.length * 1.5);
        array = Arrays.copyOf(array, newCapacity);
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        // Test add method
        list.add("Hello");
        list.add("World");
        list.add("!");

        // Test size method
        System.out.println("Size: " + list.size()); // Should print 3

        // Test get method
        System.out.println("Element at index 1: " + list.get(1)); // Should print "World"

        // Test remove method
        String removed = list.remove(0);
        System.out.println("Removed element: " + removed); // Should print "Hello"
        System.out.println("New size: " + list.size()); // Should print 2

        // Test isEmpty method
        System.out.println("Is empty? " + list.isEmpty()); // Should print false

        // Test add at index method
        list.add("Java", 1);
        System.out.println("Element at index 1 after insertion: "
                + list.get(1)); // Should print "Java"

        // Print all elements
        System.out.print("All elements: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        try {
            String removedElement = list.remove("Java");
            System.out.println("Removed element: " + removedElement);
            System.out.println("Size after removal: " + list.size());
        } catch (NoSuchElementException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        MyArrayList<String> newList = new MyArrayList<>();
        newList.add("Test");
        newList.add("AddAll");
        list.addAll(newList);
        System.out.print("After addAll: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}
