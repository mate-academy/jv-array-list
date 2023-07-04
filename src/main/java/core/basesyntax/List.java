package core.basesyntax;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> {
    boolean contains(Object o);

    Iterator<T> iterator();

    Object[] toArray();

    @SuppressWarnings("unchecked")
    <E> E[] toArray(E[] a);

    boolean add(T value);

    void add(T value, int index);

    void add(int index, T element);

    void addAll(List<T> list);

    boolean addAll(Collection<? extends T> c);

    boolean addAll(int index, Collection<? extends T> c);

    boolean containsAll(Collection<?> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    T get(int index);

    void set(T value, int index);

    T set(int index, T element);

    T remove(int index);

    T remove(T element);

    int size();

    boolean isEmpty();

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int index);

    List<T> subList(int fromIndex, int toIndex);
}
