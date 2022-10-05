package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String");
        arrayList.add("Another string");
        arrayList.add(null);
        arrayList.add("Java");
        arrayList.add("Private");
        arrayList.add(null);

        System.out.println(arrayList.get(6));

    }
}
