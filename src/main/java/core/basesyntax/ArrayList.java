package core.basesyntax;

import java.util.NoSuchElementException;
/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private int elementNum;
    private Object[] list;

    public ArrayList() {
        list = new Object[INITIAL_CAPACITY];
        elementNum = 0;
    }

    public ArrayList(int capacity) {
        list = new Object[capacity];
        elementNum = 0;
    }

    private void capacityCheck() {
        if (elementNum >= list.length) {
            Object[] enlargedList = new Object[list.length << 1];
            System.arraycopy(list, 0,enlargedList, 0, elementNum);
            list = enlargedList;
        }
    }

    private void indexCheck(int index) {
        if (index >= elementNum || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(T value) {
        capacityCheck();
        list[elementNum] = value;
        elementNum++;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        capacityCheck();
        System.arraycopy(list, index, list, index + 1, elementNum - index);
        list[index] = value;
        elementNum++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        Object[] trimmed = new Object[elementNum - 1];
        T removedElement = (T) list[index];
        if (index == elementNum - 1) {
            System.arraycopy(list, 0, trimmed, 0, elementNum - 1);
        } else {
            System.arraycopy(list, 0, trimmed, 0, index);
            System.arraycopy(list, index + 1, trimmed, index, elementNum - index - 1);
        }
        removedElement = removedElement;
        list = trimmed;
        elementNum--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        int position = 0;

        for (int i = 0; i < elementNum; i++) {
            if (list[i] == t || list[i].equals(t)) {
                return remove(i);
            }
        }
        for (Object o : list) {
            if (o.equals(t)) {
                break;
            }
            position = position + 1;
            if (position == elementNum) {
                throw new NoSuchElementException();
            }
        }
        T removedElement = (T) list[position];
        remove(position);
        return removedElement;
    }

    @Override
    public int size() {
        return elementNum;
    }

    @Override
    public boolean isEmpty() {
        return elementNum == 0;
    }
}
