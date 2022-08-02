package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> newArray = new ArrayList<>();
        newArray.add("!");
        newArray.add("!");
        newArray.add("!");
        newArray.add(null);
        newArray.add("!");
        newArray.add("123");
        newArray.add("!");
        newArray.add("!");
        newArray.add("!");
        newArray.add("!");
        newArray.add("null");
        newArray.add("!");
        newArray.add("!");
        System.out.println(newArray);
        System.out.println(newArray.get(1));
    }
}
