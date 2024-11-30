package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_CAPACITY]; // Створюємо масив з початковою ємністю
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[array.length + (array.length / 2)]; // Збільшуємо розмір на 50%
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i]; // Копіюємо елементи в новий масив
            }
            array = newArray; // Оновлюємо посилання на масив
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        if (size == array.length) {
            T[] newArray = (T[]) new Object[array.length + (array.length / 2)];
            for (int i = 0; i < index; i++) {
                newArray[i] = array[i]; // Копіюємо елементи до індексу
            }
            newArray[index] = value; // Вставляємо новий елемент
            for (int i = index; i < size; i++) {
                newArray[i + 1] = array[i]; // Переміщаємо всі елементи після індексу
            }
            array = newArray; // Оновлюємо посилання на масив
        } else {
            for (int i = size; i > index; i--) { // Переміщаємо елементи праворуч
                array[i] = array[i - 1];
            }
            array[index] = value; // Вставляємо новий елемент на вказаний індекс
        }
        size++; // Збільшуємо розмір списку
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > array.length) { // Якщо розмір нового списку більший за поточний масив
            T[] newArray = (T[]) new Object[size + list.size() + (array.length / 2)];  // Збільшуємо розмір масиву
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i]; // Копіюємо елементи в новий масив
            }
            array = newArray; // Оновлюємо посилання на масив
        }
        for (int i = 0; i < list.size(); i++) { // Додаємо всі елементи з іншого списку
            array[size] = list.get(i);
            size++; // Збільшуємо розмір списку
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) { // Перевірка на валідність індексу
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return array[index]; // Повертаємо елемент за індексом
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) { // Перевірка на валідність індексу
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        array[index] = value; // Замінюємо елемент на новий
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) { // Перевірка на валідність індексу
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        T removedValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // Очищаємо останній елемент
        size--; // Зменшуємо розмір списку
        return removedValue; // Повертаємо видалений елемент
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element); // Знаходимо індекс елемента
        if (index == -1) { // Якщо елемент не знайдений
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(index); // Викликаємо метод remove по індексу
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)) { // Перевірка на рівність елементів
                return i; // Повертаємо індекс знайденого елемента
            }
        }
        return -1; // Якщо елемент не знайдений
    }

    @Override
    public int size() {
        return size; // Повертаємо поточний розмір списку
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // Повертаємо true, якщо список порожній
    }
}
