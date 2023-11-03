package core.basesyntax;


import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int MINIMAL_CAPACITY;
    private Object [] array;
    int arraySize = 0;
    int index = -1;

    public ArrayList(){
        MINIMAL_CAPACITY = 10;
        array = new Object[MINIMAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (arraySize == MINIMAL_CAPACITY) {
            resize(MINIMAL_CAPACITY);
        }
        array[arraySize] = value;
        arraySize++;
    }

        public void resize (int CAPACITY) {
            MINIMAL_CAPACITY = CAPACITY + 5;
        Object[]newArray = new Object[MINIMAL_CAPACITY];
            System.arraycopy(array, 0, newArray, 0, arraySize);
        array = newArray;
    }

    @Override
    public void add(T value, int index) {
        if (index > arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        Object[]newArray = new Object[MINIMAL_CAPACITY];
        System.arraycopy(array, 0, newArray, 0, arraySize-index);
        System.arraycopy(array, index, newArray, index+1, arraySize-index);
        System.out.println(newArray[0]);
        newArray[index] = value;
        array = newArray;
        arraySize++;
        if (arraySize == MINIMAL_CAPACITY) {
            resize(MINIMAL_CAPACITY);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if ((list.size() + arraySize) > MINIMAL_CAPACITY){
            resize(arraySize + list.size());
        }
        for (int i1 = 0; i1 < list.size(); i1++){
            array[arraySize] = list.get(i1);
            arraySize++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("jjjjjjjjjjjj");
        }
        array[index] = value;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= arraySize){
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        T a = (T) array[index];
        Object[]newArray = new Object[MINIMAL_CAPACITY];
        System.arraycopy(array, 0, newArray, 0, arraySize);
        System.arraycopy(array, index+1, newArray, index, arraySize-(index+1));
        array = newArray;
        arraySize--;
        System.out.println(" RETURNS " + a);
        return a;
    }


    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (equals(element, (T) array[i])) {
                index = i;
            }
        }
            if (index !=-1) {
                System.out.println("if index != -1");
                Object[] newArray = new Object[MINIMAL_CAPACITY];
                System.arraycopy(array, index, newArray, index-1, arraySize - index);
                System.out.println("newArray[i] =   " + newArray[index]);
                array = newArray;
                arraySize--;
            }

            if (index == -1){
                throw new NoSuchElementException("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        return element;
        }

    public boolean equals(T firstElement, T secondElement) {
        return firstElement == null && secondElement == null
                || firstElement != null && firstElement.equals(secondElement);
    }


    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }
}
