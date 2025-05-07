package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Hello");
        arrayList.add("World!");
        arrayList.remove(0);
        arrayList.remove("World!");
        System.out.println(arrayList.size());
    }
}
