package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(9,3);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list.get(12));
        list.set(2, 12);
        list.remove(12);
        list.remove(9);
        System.out.println(list.get(11));
        System.out.println(list.size());
        System.out.println(list.get(12));
        System.out.println(list.get(3));
        System.out.println(list.size());
        list.remove(5);
        System.out.println(list.size());
    }
}
