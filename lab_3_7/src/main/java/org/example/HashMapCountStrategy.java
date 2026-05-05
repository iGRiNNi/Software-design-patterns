package org.example;

import java.util.HashMap;
import java.util.Map;

public class HashMapCountStrategy implements CountStrategy {
    @Override
    public CountResult count(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : array) {
            int currCount = map.getOrDefault(num, 0);

            map.put(num, ++currCount);
        }

        CountResult res = new CountResult();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getKey(), entry.getValue());
        }

        return res;
    }
}
