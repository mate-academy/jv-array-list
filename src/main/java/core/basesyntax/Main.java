package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> newList = new ArrayList<>();
        newList.add("11");
        newList.add("22");
        newList.add("33");
        newList.add("44");
        newList.add("55");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.addAll(newList);
        list.remove(2);
        list.add("33", 0);
        list.add("34", 0);
        list.set("333", 0);
        list.remove("33");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));
        System.out.println(list.get(5));
        System.out.println(list.get(6));
        System.out.println(list.get(7));
        System.out.println(list.get(8));
        System.out.println(list.get(9));
        System.out.println("size - " + list.size());
        System.out.println(list.isEmpty());
    }
}
