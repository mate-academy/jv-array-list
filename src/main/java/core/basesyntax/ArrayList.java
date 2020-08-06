package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] result;
    private int count;

    public ArrayList() {
        this.result = new Object[DEFAULT_CAPACITY];
        this.count = 0;
    }

    @Override
    public void add(T value) {
        if (count >= result.length) {
            result = Arrays.copyOf(result, (result.length * 3) / 2);
        }
        result[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index == count) {
            add(value);
        } else {
            indexCheck(index);
            if (count >= result.length) {
                result = Arrays.copyOf(result, (result.length * 3) / 2);
            }
            System.arraycopy(result, index, result, index + 1, count - index);
            result[index] = value;
            count++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (count + list.size() >= result.length) {
            result = Arrays.copyOf(result, (result.length * 3) / 2);
        }
        int i;
        for (i = 0; i < list.size(); i++) {
            result[count++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) result[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        result[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T temp = (T) result[index];
        System.arraycopy(result, index + 1, result, index, count - index);
        result[--count] = null;
        return temp;
    }

    @Override
    public T remove(T t) {
        int i = 0;
        for (; i < count; i++) {
            if (Objects.equals(result[i], t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public void indexCheck(int index) {
        if (index >= count || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
