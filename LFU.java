package test;

import java.util.*;

public class LFU implements CacheReplacementPolicy {
    LinkedHashMap<String, Integer> words;

    public LFU() {
        words = new LinkedHashMap<String, Integer>();
    }

    @Override
    public void add(String word) {
        Integer temp = words.remove(word);
        if (temp != null) {
            words.put(word, temp + 1);
            return;
        }
        words.put(word, 1);
    }

    @Override
    public String remove() {
        String[] temp = new String[2];
        words.forEach((word, val) -> {
            if (temp[0] == null) {
                temp[0] = word;
                temp[1] = String.valueOf(val);
            } else {
                if (Integer.parseInt(temp[1]) > val) {
                    temp[0] = word;
                    temp[1] = String.valueOf(val);
                }
            }
        });
        words.remove(temp[0]);
        return temp[0];
    }
}
