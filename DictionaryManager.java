package test;

import test.Dictionary;
import test.IOSearcher;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    static Map<String, Dictionary> dictionaries = null;

    public boolean query(String... args) {
        String word = args[args.length - 1];
        get();
        // add the dictionary to the map
        for (int i = 0; i < args.length - 1; i++) {
            if(!(dictionaries.containsKey(args[i]))){
                dictionaries.put(args[i], new Dictionary(args[i]));
            }
            if(dictionaries.get(args[i]).query(word)){
                return true;
            }
        }
        return false;
    }

    public boolean challenge(String... args) {
        int flag;
        String word = args[args.length - 1];
        get();
        // add the dictionary to the map
        for (int i = 0; i < args.length - 1; i++) {
            if(!(dictionaries.containsKey(args[i]))){
                dictionaries.put(args[i], new Dictionary(args[i]));
            }
            if(dictionaries.get(args[i]).challenge(word)){
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return dictionaries.size();
    }

    public static DictionaryManager get() {
        if (dictionaries == null) {
            dictionaries = new HashMap<>();
        }
        return new DictionaryManager();
    }
}
