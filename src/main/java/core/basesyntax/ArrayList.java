package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10; // Початкова ємність
    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            // Створюємо новий масив з більшою ємністю
            T[] newArray = (T[]) new Object[array.length + (array.length / 2)];
            // Копіюємо елементи в новий масив
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size] = value;  // Додаємо новий елемент в кінець
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        if (size == array.length) {
            // Створюємо новий масив з більшою ємністю
            T[] newArray = (T[]) new Object[array.length + (array.length / 2)];
            // Копіюємо елементи в новий масив
            for (int i = 0; i < index; i++) {
                newArray[i] = array[i];
            }
            newArray[index] = value;
            // Копіюємо решту елементів
            for (int i = index; i < size; i++) {
                newArray[i + 1] = array[i];
            }
            array = newArray;
        } else {
            // Якщо місце в масиві є, зсуваємо елементи праворуч
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        // Перевіряємо, чи вистачить місця для всіх елементів
        if (size + list.size() > array.length) {
            // Створюємо новий масив з більшою ємністю
            T[] newArray = (T[]) new Object[size + list.size() + (array.length / 2)];
            // Копіюємо поточні елементи в новий масив
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        // Додаємо елементи з другого списку
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        T removedValue = array[index];
        // Зсуваємо елементи вліво
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // Очищаємо останній елемент
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(index); // Використовуємо метод remove(int index) для видалення
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return i;
            }
        }
        return -1;
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
