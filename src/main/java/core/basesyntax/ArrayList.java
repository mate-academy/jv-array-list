package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] arrayList;
    private int minSize = 10;
    private int numberOfElement = 0;

    public ArrayList() {
        arrayList = (T[]) new Object[minSize];

    }

    @Override
    public void add(T value) {
        arrayList[numberOfElement] = value;
        numberOfElement++;
        if (numberOfElement == arrayList.length) {
            growArrayIfItIsFull();
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > numberOfElement || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "the index is not valid");
        }
        if (arrayList.length - numberOfElement == 0) {
            growArrayIfItIsFull();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, numberOfElement - index);
        arrayList[index] = value;
        numberOfElement++;
    }

    @Override
    public void addAll(List<T> list) {
        int count = 1;
        for (int j = 0; j < count; j++) {
            if (list.size() > (arrayList.length - numberOfElement)) {
                growArrayIfItIsFull();
                count++;
            } else {
                break;
            }
        }
        T[] tempArrayWithList = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempArrayWithList[i] = list.get(i);
        }
        System.arraycopy(tempArrayWithList, 0, arrayList,
                numberOfElement, tempArrayWithList.length);
        numberOfElement = numberOfElement + list.size();
    }

    @Override
    public T get(int index) {
        outOfBoundsCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsCheck(index);
        T result = arrayList[index];
        dataTransferWhenRemove(index + 1, index);
        return result;
    }

    @Override
    public T remove(T element) {
        T resultForElement = null;
        for (int i = 0; i < numberOfElement; i++) {
            if ((element == null && arrayList[i] == null)
                    || (element != null && element.equals(arrayList[i]))) {
                resultForElement = arrayList[i];
                dataTransferWhenRemove(i + 1, i);
                return resultForElement;
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return numberOfElement;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElement == 0 ? true : false;
    }

    private void growArrayIfItIsFull() {
        int incrementStep = arrayList.length / 2;
        T[] arrayListTemp = (T[]) new Object[arrayList.length + incrementStep];
        System.arraycopy(arrayList, 0, arrayListTemp, 0, numberOfElement);
        arrayList = (T[]) new Object[arrayListTemp.length];
        System.arraycopy(arrayListTemp, 0, arrayList, 0, arrayListTemp.length);
    }

    private void outOfBoundsCheck(int index) {
        if (index >= numberOfElement || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "the index is not valid");
        }
    }

    private void dataTransferWhenRemove(int index1, int index2) {
        System.arraycopy(arrayList, index1, arrayList, index2, numberOfElement - index2);
        arrayList[numberOfElement - 1] = null;
        numberOfElement--;
    }

}
