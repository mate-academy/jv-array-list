package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final Object[] EMPTY_ELEMENTDATA = {};
    public Object [] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity"
                    + initialCapacity);
        }
    }
    public ArrayList() {
        this.elementData = new Object[10];
    }

    private Object[] grow(int minCapacity) {
        Object[] biggerArray = new Object[minCapacity];
        for(int i = 0; i < biggerArray.length; i ++) {
            biggerArray[i] = elementData[i];
        }
        return biggerArray;
    }


    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        int potentialSize = size + 1;
        if(!(indexInBoundsCheck(potentialSize, elementData.length ))){
            elementData = grow((int)(potentialSize * 1.5));
        }
        Object[] temporaryArray = new Object[elementData.length];
        if(index == size) {
            elementData[index] = value;
            size ++;
        } else if (index < size && index >= 0) {
            for(int i = 0; i< size; i++){
                if(i< index){
                    temporaryArray[i] = elementData[i];
                }if(i == index) {
                    temporaryArray[i] = value;
                } else {
                    temporaryArray[i] = elementData[i-1];
                }
            }
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("invalid index passed");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if((elementData.length - size) <= list.size()) {
            grow((int)(list.size() + elementData.length * 1.5));
        }
        int j = 0;
        for (int i = size; j < list.size(); i++) {
            elementData[i] = list.get(j);
            j++;
            size++;
        }

    }

    @Override
    public T get(int index) {
        if(indexInBoundsCheck(index,size)) {
            return (T) elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Can't get element with this index, try again");
    }

    @Override
    public void set(T value, int index) {
        if(indexInBoundsCheck(index,size)) {
            elementData[index] = value;
        }
        throw new ArrayListIndexOutOfBoundsException("Can't set element into this index, try again");
    }

    public boolean indexInBoundsCheck(int index, int bounds) {
        if( index < 0 || index >= bounds || bounds < 0) {
          return false;
        }
        return  true;
    }

    @Override
    public T remove(int index) {
        Object [] fixedArray = new Object[elementData.length];
        if(indexInBoundsCheck(index, size)) {
            for( int i = 0; i < fixedArray.length - 1; i++) {
                if (i < index) {
                    fixedArray[i] = elementData[i];
                }
               /* else if ( index = fixedArray.length - 1) {
                    fixedArray[i] =
                } */
                else {
                        fixedArray[i] = elementData[i + 1];
                }
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element with this index, try again");
        }
        Object OldObject = elementData[index];
        size = size - 1;
        elementData = fixedArray;
        return (T) OldObject;
    }

    @Override
    public T remove(T element) {
        int indexOfElement;
            for( int i = 0; i < elementData.length; i++) {
                if(elementData[i] == element || elementData[i] != null && elementData[i].equals(element)) {
                    indexOfElement = i;
                    remove(indexOfElement);
                }
            }
        throw new NoSuchElementException("There is no such element in list, check again");
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
