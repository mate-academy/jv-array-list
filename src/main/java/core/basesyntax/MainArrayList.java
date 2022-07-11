package core.basesyntax;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class MainArrayList {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            stringArrayList.add(new String(random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString()));
        }
        System.out.println(stringArrayList);


    }
}
