package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
  
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int newCapacity;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.newCapacity = DEFAULT_CAPACITY;
        this.elementData = new Object[newCapacity];
        this.size = 0;
    }

    public ArrayList(int size) {
        this.newCapacity = DEFAULT_CAPACITY;
        while (size >= DEFAULT_CAPACITY) {
            resize();
        }
        this.elementData = new Object[newCapacity];
        this.size = size;
    }

    @Override
    public void add(T value) {
        if (size == newCapacity) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        Object[] newArray = elementData;
        if (size == newCapacity) {
            resize();
        }
        System.arraycopy(newArray,index,elementData,index + 1,size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size >= newCapacity) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size) {
            return (T)elementData[index];
        }
        throw new ArrayIndexOutOfBoundsException("There is no such element with index " + index);
    }

    @Override
    public void set(T value, int index) {
        if (index < size) {
            elementData[index] = value;
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(int index) {
        T res = (T)elementData[index];
        System.arraycopy(elementData,index + 1,elementData,index,size - index);
        size--;
        return res;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == null && elementData[i] == null)
                    || (elementData[i] != null && ((T)elementData[i]).equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        newCapacity = newCapacity + (newCapacity >> 1);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData,0,newArray,0,size);
        elementData = newArray;
    }
}
