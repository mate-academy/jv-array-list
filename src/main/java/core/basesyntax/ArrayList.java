package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final static int DEFAULT_CAPAСITY = 10;
    private final static String INDEX_ERROR_MESSAGE="Can not to find such element";
    private final static String INDEX_OUT_OF_THE_BROAD="Index out of the broad";
    Object [] primeryArray;
    public int size;
    Object remuvedObject;

    public ArrayList(){
        primeryArray=new Object[DEFAULT_CAPAСITY];
    }

    @Override
    public void add(T value) {
      if(primeryArray.length==size){
          grow();
      }
      primeryArray[size]=value;
      size++;
    }
    @Override
    public void add(T value, int index) {
        exeptionIndex(index);
     if(primeryArray.length==size){
         grow();
     }
        Object [] bufferArray=new Object[primeryArray.length+1];
        System.arraycopy(primeryArray,0,bufferArray,0,index);
        bufferArray[index]=value;
        System.arraycopy(primeryArray,index,bufferArray,index+1,primeryArray.length-index);
        primeryArray=bufferArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
           this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
       exeptionIndex(index);
       if(index>=size){
           throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_THE_BROAD);
       }
        return (T) primeryArray[index];
    }

    @Override
    public void set(T value, int index) {
        exeptionIndex(index);
        if(index>=size){
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_THE_BROAD);
        }
        primeryArray[index]=value;
    }

    @Override
    public T remove(int index) {
        exeptionIndex(index);
        if(index>=size){
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_THE_BROAD);
        }
        remuvedObject=primeryArray[index];
        remuveIndex(index);
        return (T) remuvedObject;
    }
    public void remuveIndex(int index){
        primeryArray[index]=null;
        if(index!=size){
            System.arraycopy(primeryArray, index + 1, primeryArray, index, size - index - 1);
        }
        size--;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == primeryArray[i] || element != null && element.equals(primeryArray[i])) {
              return  remove(i);
            }
        }
        throw new NoSuchElementException("Can not to find such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }
    public void grow(){
        Object[] bufferArray= new Object[primeryArray.length+primeryArray.length/2];
        for (int i = 0; i < primeryArray.length; i++) {
            bufferArray[i]=primeryArray[i];
        }
        primeryArray=bufferArray;
    }
    public void exeptionIndex(int index ){
        if(index>size || index<0){
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
    }
}
