package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    public int arraySize;
    public Object[] values;
    public int flag;

    private ArrayList() {
        arraySize = 0;
        values = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        if (isEnoughSpace() == false) {
            resize();
        }
        values[arraySize] = value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
      if(isEnoughSpace() == false){
          resize();
      }
        System.arraycopy(values, index, values, index + 1, arraySize - index);
        values[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
   if(isEnoughSpaceForList(list.size())==false){
        resize();
        continue;
   }
      for(int i=0; i<list.size(); i++){
          values[arraySize] = get(i);
          arraySize++;
      }
    }

    @Override
    public T get(int index) {
        if (index >= values.length) {
            return null;
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
         if(index<values.length){
             values[index] = value;
         }
    }

    @Override
    public T remove(int index) {

        return null;
    }

    @Override
    public T remove(T t) {
        return null;
    }

    @Override
    public int size() {

        return values.length;
    }

    @Override
    public boolean isEmpty() {

        return values.length == 0;
    }


    public boolean isEnoughSpace() {
       return  (arraySize + 1 < values.length);
    }

    public boolean isEnoughSpaceForList(int listLength) {
        return (listLength <= values.length - arraySize);

    }


    public void resize() {
        Object[] oldValues = values;
        int oldCapacity = values.length;
        int newCapacity = (oldCapacity * 3) / 2 + 1;
        values = (T[]) new Object[newCapacity];
        arraySize = newCapacity;
        // oldData - временное хранилище текущего массива с данными
        System.arraycopy(oldValues, 0, values, 0, arraySize);
    }
}
