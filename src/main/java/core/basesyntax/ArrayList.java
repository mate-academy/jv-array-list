package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[10]; // Ініціалізація масиву з початковим розміром
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize(); // Якщо масив заповнений, збільшуємо розмір
        }
        elements[size++] = value; // Додаємо елемент і збільшуємо розмір
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == elements.length) {
            resize(); // Якщо масив заповнений, збільшуємо розмір
        }

        // Переміщаємо елементи вправо від індексу до кінця списку
        System.arraycopy(elements, index, elements, index + 1, size - index);

        // Додаємо новий елемент
        elements[index] = value;
        size++; // Збільшуємо розмір
    }

    @Override
    public void addAll(List<T> list) {
        // Перебираємо всі елементи переданого списку і додаємо їх до нашого
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i)); // Додаємо кожен елемент в наш список
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index
                    + ". Valid range: 0 to " + (size - 1));
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        // Перевірка, чи індекс знаходиться в межах
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }

        // Заміняємо елемент на новий
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index
                    + ". Valid range: 0 to " + (size - 1));
        }

        T element = (T) elements[index];

        // Зсуваємо елементи, щоб заповнити видалений елемент
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--; // Зменшуємо розмір

        return element;
    }

    @Override
    public T remove(T element) {
        // Перевірка на null
        if (element == null) {
            // Якщо елемент null, шукаємо перший null в масиві
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    T removedElement = (T) elements[i];

                    // Зсуваємо елементи на одну позицію вліво
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    size--;
                    return removedElement;
                }
            }
        } else {
            // Якщо елемент не null, шукаємо звичайним способом
            for (int i = 0; i < size; i++) {
                if (elements[i] != null && elements[i].equals(element)) {
                    T removedElement = (T) elements[i];

                    // Зсуваємо елементи на одну позицію вліво
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    size--;
                    return removedElement;
                }
            }
        }

        // Якщо елемент не знайдений
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // Якщо розмір списку 0, то список порожній
    }

    private void resize() {
        int newSize = elements.length * 2;
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
