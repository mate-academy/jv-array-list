package core.basesyntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        List<Integer> list1 = new ArrayList<>();
        for (int i = 20; i < 40; i++) {
           list1.add(i);
        }
        list.addAll(list1);
        System.out.println(list.toString());
    }
    /*
       public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }
     */
}
