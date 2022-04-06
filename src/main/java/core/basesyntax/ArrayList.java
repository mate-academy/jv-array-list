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
        for (int i = counterElement; i < list.length; i++) {
            list[i] = value;
            counterElement++;
            break;
        }
    }

    @Override
    public void add(T value, int index) {
        if (counterElement == list.length) {
            growList();
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index position is less then 0");
        } else if (index == counterElement && counterElement < list.length) {
            list[index] = value;
            counterElement++;
        } else if (index > counterElement) {
            throw new ArrayListIndexOutOfBoundsException("can't find the index");
        } else if (index < counterElement && counterElement < list.length) {
            for (int i = 0; i < counterElement; i++) {
                if (index == i) {
                    Object temp = list[i];
                    list[i] = value;
                    for (int j = index + 1; j < list.length; j++) {
                        Object temp2 = list[j];
                        list[j] = temp;
                        temp = temp2;
                    }
                    counterElement++;
                    break;
                }
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (this.list.length < this.list.length + list.size()) {
            Object[] temp = new Object[this.list.length + list.size()];
            for (int i = 0; i < counterElement; i++) {
                temp[i] = this.list[i];
            }
            for (int i = 0; i < list.size(); i++) {
                temp[counterElement] = list.get(i);
                counterElement++;
            }
            this.list = temp;
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
            } else if (element != null) {
                continue;
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
