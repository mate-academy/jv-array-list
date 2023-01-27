package core.basesyntax;

public class TestProgram {
    public static void main(String[] args) {
        java.util.ArrayList<Integer> standardList = new java.util.ArrayList<>();
        standardList.add(5);
        standardList.add(1, 66);
        standardList.add(3, 67);
        System.out.println(standardList);
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
        //arrayList.remove(10);
    }
}
