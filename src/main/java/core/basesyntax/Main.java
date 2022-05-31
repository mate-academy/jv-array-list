package core.basesyntax;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test");
        arrayList.add("for");
        arrayList.add("my");
        arrayList.add("darling");
        arrayList.add("Mate");
        arrayList.add("Academy");


        ArrayList<String> newArrayList = new ArrayList<>();
        newArrayList.add("Academy");
        newArrayList.add("Kiev");
        newArrayList.add("Ukraine");
        newArrayList.add("Welcome");

        arrayList.addAll(newArrayList);
        System.out.println(arrayList);
        System.out.println(arrayList.size());
//        System.out.println(newArrayList);


    }
}
