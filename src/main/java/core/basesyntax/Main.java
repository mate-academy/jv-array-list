package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        myList.add(10);
        myList.add(11);
        myList.add(12);
        myList.add(13);
        myList.add(14);
        myList.add(15);
        myList.add(16);
        myList.add(100, 9);
        myList.set(999, 0);
        System.out.println(myList.size());
        System.out.println(myList);
        System.out.println(myList.get(15));
    }
}
