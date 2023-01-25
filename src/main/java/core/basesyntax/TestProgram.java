package core.basesyntax;

public class TestProgram {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(null);
        arrayList.add(4);
        System.out.println(arrayList);
        arrayList.add(5);
        System.out.println(arrayList);
        arrayList.add(6,4);
        arrayList.add(7,3);
        arrayList.add(3);
        System.out.println(arrayList);
        arrayList.remove(3);
        System.out.println(arrayList);
        arrayList.remove(null);
        System.out.println(arrayList);
    }
}
