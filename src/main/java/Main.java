import core.basesyntax.ArrayList;
import core.basesyntax.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("Line 1");
        stringArrayList.add("Line 2");
        stringArrayList.add("Line 3");
        stringArrayList.add("Line 4");
        stringArrayList.add("Line 5");
        stringArrayList.add("Line 6");
        stringArrayList.add("Line 7");
        stringArrayList.add("Line 8");
        stringArrayList.add("Line 9");
        stringArrayList.add("Line 10");
        stringArrayList.add("Line 11");
        print(stringArrayList);
        System.out.println(stringArrayList.size());
    }

    private static void print(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
