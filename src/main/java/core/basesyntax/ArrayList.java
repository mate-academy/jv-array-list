package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    T[] elementData = (T[]) new Object[10];
    private int size;

    @Override
    public void add(T value) {
        if (size + 1 < elementData.length) {
            elementData[size++] = value;
        } else {
            T[] newArray = (T[]) new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData,0,newArray,0,size);
            newArray[size++] = value;
            elementData = newArray;
        }
    }

    @Override
    public void add(T value, int index) {
        Object [] newElementData;
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (index < elementData.length) {
            System.arraycopy(elementData, index , elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            if (index >=  elementData.length) {
                newElementData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
                System.arraycopy(elementData,0,newElementData,0,index - 1);
                newElementData [index] = value;
                System.arraycopy(elementData,index,newElementData,index + 1,size - index);
                size++;
                elementData = (T[]) newElementData;
            }
        } else {
            elementData[size + 1] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        //1 2 3
        // oldArray = 1 2 3 4 5 6 7 8 9 []
        // create new array -- copy oldArray --- oldArray + newList
        //
        T[] listArray = (T[]) new Object[list.size()];
        Object [] addAll = new Object[listArray.length];
        for(int i = 0; i < listArray.length; i++){
            listArray[i] = list.get(i);
        }
        if (listArray.length + size > elementData.length) {
            addAll = (T[]) new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, addAll, 0, size);
            System.arraycopy(elementData, 0, addAll, 0, addAll.length);
            elementData = (T[]) addAll;
            size += listArray.length;
        } else {
            System.arraycopy(listArray, 0, elementData, size, listArray.length);
            size += listArray.length;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        Object removeElement;
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        removeElement = elementData[index];
        int needNumberCopy = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, needNumberCopy);
        elementData[--size] = null;
        return (T) removeElement;
    }

    @Override
    public T remove(T element) { /// null
        boolean elementFound = false;
        T remove = null;
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == null) {
                remove = remove(i);
                return  remove;
            }
            if (element.equals(elementData[i]) && elementData[i] != null) {
                remove = remove(i);
                elementFound = true;
            }
        }
            if (elementFound == false) {
                throw new NoSuchElementException("No such element");
            }
            return remove;
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
