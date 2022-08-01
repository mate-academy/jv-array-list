package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private Object[] objects;
    private int currantLength;
    private int numberOfElements;

    public ArrayList() {
        objects = new Object[10];
        currantLength = 10;
        numberOfElements = 0;
    }

    @Override
    public void add(T value) {
        if (numberOfElements == currantLength) {
            grow();
        }
        objects[numberOfElements] = value;
        numberOfElements++;
    }

    @Override
    public void add(T value, int index) {
        if (index > numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't write an element to ArrayList");
        }
        if (numberOfElements == currantLength) {
            grow();
        }
        for (int i = numberOfElements; i > index; i--) {
            objects[i] = objects[i - 1];
        }
        objects[index] = value;
        numberOfElements++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't get an element");
        }
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is not such index");
        }
        objects[index] = value;
        if (index == numberOfElements) {
            this.get(index);
        } else {
            objects[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + String.valueOf(index)
                    + " is not exist");
        }
        Object removedObject = objects[index];
        for (int i = index; i < numberOfElements - 1; i++) {
            objects[i] = objects[i + 1];
        }
        numberOfElements--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        Object removedObject = null;
        for (int i = 0; i < numberOfElements; i++) {
            if (element == null && objects[i] == null
                    || objects[i] != null && objects[i].equals(element)) {
                removedObject = objects[i];
                remove(i);
                return (T) removedObject;
            }
        }
        throw new java.util.NoSuchElementException("There is not such element.");
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfElements == 0) ? true : false;
    }

    private void grow() {
        Object[] newObject = new Object[currantLength + currantLength / 2];
        for (int i = 0; i < objects.length; i++) {
            newObject[i] = objects[i];
        }
        objects = newObject;
        currantLength = objects.length;
    }
}
