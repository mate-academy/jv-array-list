package core.basesyntax;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (int) (oldCapacity * GROWTH_FACTOR);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void checkElementExistence(T element) {
        if (!contains(element)) {
            throw new NoSuchElementException("Element not found: " + element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                @SuppressWarnings("unchecked")
                T element = (T) elements[cursor];
                cursor++;
                return element;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size == elements.length) {
            ensureCapacity(size + 1);
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void add(int index, T element) {
        checkIndexBounds(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] objects = list.toArray();
        for (Object object : objects) {
            add((T) object);
        }

    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        ensureCapacity(size + c.size());
        int i = size;
        for (T element : c) {
            elements[i] = element;
            i++;
        }
        size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexBounds(index);
        ensureCapacity(size + c.size());
        int numElementsToShift = size - index;
        if (numElementsToShift > 0) {
            System.arraycopy(elements, index, elements, index + c.size(), numElementsToShift);
        }
        for (T element : c) {
            elements[index++] = element;
            size++;
        }
        return true;
    }

    @Override
    public T remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + o);
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = (T) elements[index];
        int numElementsToShift = size - index - 1;
        if (numElementsToShift > 0) {
            System.arraycopy(elements, index + 1, elements, index, numElementsToShift);
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                remove(i);
                modified = true;
                i--;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(i);
                modified = true;
                i--;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        checkIndexBounds(index);
        elements[index] = value;
    }

    @Override
    public T set(int index, T element) {
        checkIndexBounds(index);
        T previousElement = (T) elements[index];
        elements[index] = element;
        return previousElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndexBounds(index);
        return new ListIterator<T>() {
            private int cursor = index;
            private int lastReturnedIndex = -1;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                lastReturnedIndex = cursor;
                @SuppressWarnings("unchecked")
                T element = (T) elements[cursor++];
                return element;
            }

            @Override
            public boolean hasPrevious() {
                return cursor > 0;
            }

            @Override
            public T previous() {
                if (cursor <= 0) {
                    throw new NoSuchElementException();
                }
                lastReturnedIndex = --cursor;
                @SuppressWarnings("unchecked")
                T element = (T) elements[cursor];
                return element;
            }

            @Override
            public int nextIndex() {
                return cursor;
            }

            @Override
            public int previousIndex() {
                return cursor - 1;
            }

            @Override
            public void remove() {
                if (lastReturnedIndex < 0) {
                    throw new IllegalStateException();
                }
                ArrayList.this.remove(lastReturnedIndex);
                if (lastReturnedIndex < cursor) {
                    cursor--;
                }
                lastReturnedIndex = -1;
            }

            @Override
            public void set(T element) {
                if (lastReturnedIndex < 0) {
                    throw new IllegalStateException();
                }
                ArrayList.this.set(lastReturnedIndex, element);
            }

            @Override
            public void add(T element) {
                ArrayList.this.add(cursor++, element);
                lastReturnedIndex = -1;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        checkIndexBounds(fromIndex);
        checkIndexBounds(toIndex - 1);
        ArrayList<T> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }
}
