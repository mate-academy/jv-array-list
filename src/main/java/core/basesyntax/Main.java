package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13, 12);
        list.add(150, 5);
        list.remove(5);
        list.add(null);
        list.remove(null);
        System.out.println(list.get(11));
        list.set(200, 6);
        System.out.println(list.get(6));
        list.remove(150); // The element doesn't exist!!!
    }
}
