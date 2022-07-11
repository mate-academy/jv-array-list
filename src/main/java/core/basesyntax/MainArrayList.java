package core.basesyntax;

import java.util.Random;

public class MainArrayList {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 3;
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            stringArrayList.add(new String(random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString()));
        }
        System.out.println(stringArrayList);
        System.out.println(stringArrayList.get(14));
        System.out.println(stringArrayList.get(15));
        System.out.println(stringArrayList.remove(15));
        System.out.println(stringArrayList);
    }
}
