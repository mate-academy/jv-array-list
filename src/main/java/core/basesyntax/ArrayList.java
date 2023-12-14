package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int size;
    private T[] myList;

    @Override
    public void add(T value) {
        if (this.isEmpty()){
            myList = (T[]) new Object[CAPACITY];
        }
        size++;
        myList[size] = value;
    }

    @Override
    public void add(T value, int index) {
        if ((index >= size)||(index < 0)){
              // throw ERROR
        }
        for(int i = size; i > index;i--){
            myList[i] = myList[i - 1];
        }
        myList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int j = 0;
        for(int i = size; i < list.size() + size - 1; i++){
            myList[i] = list[j];
            j++;
            size++;
        }
    }

    @Override
    public T get(int index) {
        if ((index > size)||(index < 0)){
            return null;   // throw ERROR
        }
        return myList[index];
    }

    @Override
    public void set(T value, int index) {
        if ((index > size)||(index < 0)){
            return null;   // throw ERROR
        }
        myList[index] = value;
    }

    @Override
    public T remove(int index) {
        if ((index >= size)||(index < 0)){
            return null;   // throw ERROR
        }
        T element = myList[index];
        for(int i = index; i < size - 1;i++){
            myList[i] = myList[i + 1];
        }
        myList[size - 1] = null;
        size--;
        return  element;
    }

    @Override
    public T remove(T element) {
        for(int i = 0; i < size; i++){
            if (myList[i] == element){
                for(int j = i; j < size - 1; j++){
                    myList[i] = myList[i + 1];
                }
                myList[size - 1] = null;
                size--;
                return  element;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }
}
