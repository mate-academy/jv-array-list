package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value; // Додаємо новий елемент
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i)); // Додаємо кожен елемент із переданого списку
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value; // Замінюємо елемент за індексом
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // Очищаємо останній елемент
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
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

    private void resize() {
        int newCapacity = elements.length + elements.length / 2; // Збільшуємо ємність у 1.5 рази
        @SuppressWarnings("unchecked")
        T[] newElements = (T[]) new Object[newCapacity]; // Створюємо новий масив
        for (int i = 0; i < size; i++) { // Копіюємо елементи вручну
            newElements[i] = elements[i];
        }
        elements = newElements; // Змінюємо посилання на новий масив
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for add operation");
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) { // Якщо масив заповнений
            resize(); // Збільшуємо його ємність
        }
    }
}
