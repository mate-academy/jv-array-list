package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("One");
        stringArrayList.add("Two");
        stringArrayList.add("Three");

        stringArrayList.remove(null);
        System.out.println(stringArrayList);
        System.out.println(stringArrayList.size());
    }
}
