package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ARRAY_LENGTH = 10;
    private int size;
    private Node<T>[] items;

    public ArrayList() {
        items = new Node[MAX_ARRAY_LENGTH];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size < items.length) {
            items[size] = new Node<>(value);
            size++;
        } else {
            growArray(size);
            items[size] = new Node<>(value);
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else if (index == size) {
            add(value);
        } else {
            if (size + 1 <= items.length) {
                addInside(index, value);
            } else {
                growArray(size);
                addInside(index, value);
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (getIndexInRange(index)) {
            return items[index].getValue();
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (getIndexInRange(index)) {
            items[index] = new Node<>(value);
        }
    }

    @Override
    public T remove(int index) {
        if (getIndexInRange(index)) {
            Node<T> removedElement = items[index];
            if (index + 1 != size) {
                System.arraycopy(items,index + 1,items, index, size - index);
                size--;
            } else {
                size--;
            }
            return removedElement.getValue();
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == items[i].getValue())
                    || (element != null && element.equals(items[i].getValue()))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
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

    private void growArray(int countOfElements) {
        int newArrayLength = getNewLength(countOfElements);
        Node<T> [] tempArray = new Node[newArrayLength];
        for (int i = 0; i < countOfElements; i++) {
            tempArray[i] = items[i];
        }
        items = new Node[newArrayLength];
        for (int i = 0; i < countOfElements; i++) {
            items[i] = tempArray[i];
        }
    }

    private int getNewLength(int oldLength) {
        if (oldLength == 1) {
            return 2;
        }
        return oldLength * 3 / 2;
    }

    private void addInside(int index, T value) {
        Node<T>[] tempArray = new Node[items.length - index - 1];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = items[index + i];
        }
        items[index] = new Node<>(value);
        for (int i = 0; i < tempArray.length; i++) {
            items[index + 1 + i] = tempArray[i];
        }
        size++;
    }

    private boolean getIndexInRange(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return true;
    }

    private class Node<T> {
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}
