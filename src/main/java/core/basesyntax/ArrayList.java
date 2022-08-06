package core.basesyntax;

import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    //size показывает сколько элементов заполнено
    private int size;


    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Argument to create array");
        }
        elements = new Object[initCapacity];
    }


    @Override
    public void add(T value) {
        resizeIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        //проверяем индекс на валидность
        checkIndex(index, size);
        //если массив полный и нам нужно добавить еще один елемент то нам нужно сделать ресайз
        resizeIfFull();
        //если на переданном индексе уже будет елемент тогда нужно кусок массива отодвинуть вправо,
        //и на нужный индекс засетить значение
        //c массива elements, начиная с элемента index, скопируй в этот же массив начиная с элемента index+1
        //и нужно скопировать size - index елементов
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {

    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void resizeIfFull() {
        //если массив полон
        //создай новый массив x1,5 и положи в него старый
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + (elements.length >> 2)];
            //arraycopy берет кусок памяти и вставляет куда мы скажем
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid");
        }
    }

}
