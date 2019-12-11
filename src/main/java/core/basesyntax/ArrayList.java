package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private Object[] elementData;
    private Object[] emptyData = {};
    private int lastIndex = 0;
    private static final int DEFAULT_LIST_CAPACITY = 10;

    public ArrayList() {
        elementData = emptyData;
    }

    public ArrayList(int length) {
        if (length > 0) {
            elementData = new Object[length];
        } else if (length == 0) {
            elementData = emptyData;
        } else {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
    }

    @Override
    public void add(T value) {
        if (elementData.length == 0 || lastIndex == elementData.length) {
            elementData = grow();
            add(value);
        } else {
            elementData[lastIndex++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (elementData.length == lastIndex) {
            elementData = grow();
        }
        if (index > -1 && index < lastIndex) {
            add((T) elementData[lastIndex]);
            for (int i = lastIndex - 1; i > index - 1; i--) {
                if (i == index) {
                    set(value, i);
                } else {
                    set((T) elementData[i - 1], i);
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elementData.length) {
            elementData = grow();
            addAll(list);
        } else {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index > -1 && index < lastIndex) {
            return (T) elementData[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index > -1 && index < lastIndex) {
            if (index > elementData.length) {
                elementData = grow();
            }
            elementData[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (elementData.length == lastIndex) {
            elementData = grow();
        }
        if (index > -1 && index < lastIndex) {
            for (int i = index; i < lastIndex; i++) {
                if (i == index) {
                    value = (T) elementData[i];
                }
                set((T) elementData[i + 1], i);
            }
            lastIndex--;
            return value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Your index is lesser that 0");
        }
    }

    @Override
    public T remove(T t) {
        int i = 0;
        for (; i < lastIndex; i++) {
            if (elementData[i] == t || elementData[i].equals(t)) {
                break;
            }
            if (i == lastIndex - 1) {
                throw new NoSuchElementException("Check your index");
            }
        }
        return remove(i);
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        if (elementData == emptyData || lastIndex == 0) {
            return true;
        }
        for (int i = 0; i < lastIndex; i++) {
            if (elementData[i] != null) {
                return false;
            }
        }
        return true;
    }

    private Object[] grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = (elementData.length * 3) / 2 + 1;
            return elementData = Arrays.copyOf(elementData, newCapacity);
        }
        return elementData = Arrays.copyOf(elementData,
                Math.max(DEFAULT_LIST_CAPACITY, lastIndex + 1));
    }
}
