package core.basesyntax;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add(null);
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.remove(2);


        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.get(3));



    }
}
