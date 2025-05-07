package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int counterElement = 0;
    private Object[] list = new Object[CAPACITY];

    @Override
    public void add(T value) {
        if (counterElement == list.length) {
            growList();
        }
        list[counterElement] = value;
        counterElement++;
    }

    @Override
    public void add(T value, int index) {
        if (counterElement == list.length) {
            growList();
        }
        if (index < 0 || index > counterElement) {
            throw new ArrayListIndexOutOfBoundsException("index position is wrong");
        } else if (index <= counterElement && counterElement < list.length) {
            System.arraycopy(list, index, list, index + 1, counterElement - index);
        }
        list[index] = value;
        counterElement++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        exceptionIndex(index);
        for (int i = 0; i < counterElement; i++) {
            if (i == index) {
                return (T) list[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        exceptionIndex(index);
        for (int i = 0; i < counterElement; i++) {
            if (i == index) {
                list[i] = value;
                break;
            }
        }
    }

    @Override
    public T remove(int index) {
        exceptionIndex(index);
        Object o = "";
        for (int i = 0; i < counterElement; i++) {
            if (i == index) {
                o = list[i];
                for (int j = i + 1; j < counterElement; j++) {
                    list[i] = list[j];
                    i++;
                }
            }
        }
        counterElement--;
        return (T) o;
    }

    @Override
    public T remove(T element) {
        int rmIndex = 0;
        for (int i = 0; i < counterElement; i++) {
            if (element == null) {
                counterElement--;
                return null;
            } else if (element.equals(list[i]) && element != null) {
                rmIndex = i;
                System.arraycopy(list, rmIndex + 1, list, rmIndex, counterElement - rmIndex + 1);
                counterElement--;
                return element;
            }
        }
        throw new NoSuchElementException("this element not found");
    }

    @Override
    public int size() {
        return counterElement;
    }

    @Override
    public boolean isEmpty() {
        if (counterElement == 0) {
            return true;
        }
        return false;
    }

    private void growList() {
        Object[] temp = new Object[list.length + list.length / 2];
        System.arraycopy(this.list, 0, temp, 0, counterElement);
        list = temp;
    }

    private void exceptionIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index position is less then 0");
        }
        if (index >= counterElement) {
            throw new ArrayListIndexOutOfBoundsException(" index position more size the List");
        }
    }

}
