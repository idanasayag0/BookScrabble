package test;

import java.util.LinkedHashMap;

public class LRU  implements CacheReplacementPolicy{
    LinkedHashMap<String,Integer> words;

    public LRU (){
        words = new LinkedHashMap<String,Integer>();
    }

    @Override
    public void add(String word) {
        words.remove(word);
        words.put(word,1);
    }

    @Override
    public String remove() {
        String tempWord = words.entrySet().iterator().next().getKey();
        words.remove(tempWord);
        return tempWord;
    }

}
