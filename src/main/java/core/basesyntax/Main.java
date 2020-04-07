package core.basesyntax;

public class Main {
    public static void main(String[] args) {
//        ArrayList<String> arr = new ArrayList<>();
//        arr.add("3");
//        arr.add(null);
//        arr.add("12");
//        for(int i = 0; i < arr.size(); i++){
//            System.out.println(arr.get(i));
//        }
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList.add("String");
        arrayList.add(null);
        arrayList.add("Java");
        arrayList1.add("Private");
        arrayList1.add("String");
        arrayList1.add(null);
        arrayList1.add("Java");
        arrayList1.add("Private");
        arrayList.addAll(arrayList1);
        //System.out.println(arrayList.remove(2));
        for(int i = 0; i < arrayList.size(); i++){
            System.out.println(arrayList.get(i));
        }
    }
}
