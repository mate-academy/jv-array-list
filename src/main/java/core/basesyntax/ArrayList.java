package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
@SuppressWarnings("checkstyle:SummaryJavadoc")
public class ArrayList<T> implements List<T> {
    private Object[] arrayList;
    private int countElements;
    private int listSize;

    public ArrayList() {
        this.listSize = 10;
        this.arrayList = new Object[listSize];
        this.countElements = 0;
    }

    private int indexOf(T t) {
        for (int i = 0; i < arrayList.length; i++) {
            if (t.equals(arrayList[i])) {
                return i;
            }
        }
        return -1;
    }

    private void resize() {
        listSize *= 1.5;
        Object[] tempList = new Object[listSize];
        System.arraycopy(arrayList, 0, tempList, 0, countElements);
        arrayList = tempList;
    }

    private void resize(int sizeOfIncrease) {
        listSize += sizeOfIncrease * 1.5;
        Object[] tempList = new Object[listSize];
        System.arraycopy(arrayList, 0, tempList, 0, countElements);
        arrayList = tempList;
    }

    @Override
    public void add(T value) {
        if (countElements == listSize) {
            resize();
        }
        arrayList[countElements] = value;
        countElements++;
    }

    @Override
    public void add(T value, int index) {
        if (countElements == listSize) {
            resize();
        }
        if (index >= countElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index < countElements) {
            System.arraycopy(arrayList, index, arrayList, index + 1, countElements - index + 1);
            arrayList[index] = value;
            countElements++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (countElements + list.size() > listSize) {
            resize(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= countElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= countElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= countElements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        countElements--;
        T temp = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - index - 1);
        arrayList[arrayList.length - 1] = null;
        return temp;
    }

    @Override
    public T remove(T t) {
        if (indexOf(t) == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < arrayList.length; i++) {
            if ((t != null) && (arrayList[i].equals(t))) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return countElements;
    }

    @Override
    public boolean isEmpty() {
        return countElements == 0;
    }
}
