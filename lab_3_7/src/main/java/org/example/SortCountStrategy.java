package org.example;

import java.util.Arrays;

public class SortCountStrategy implements CountStrategy {
    @Override
    public CountResult count(int[] array) {
        CountResult res = new CountResult();

        if (array == null || array.length == 0) {
            return res;
        }

        int[] copyArray = Arrays.copyOf(array, array.length);
        Arrays.sort(copyArray);

        int a = 0;
        int b = 1;

        while (b < copyArray.length) {
            if (copyArray[a] != copyArray[b]) {
                res.add(copyArray[a], b - a);
                a = b;
            }
            b++;
        }
        res.add(copyArray[a], b - a);

        return res;
    }
}
