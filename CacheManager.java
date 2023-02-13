package test;

import java.util.LinkedHashSet;

public class CacheManager {
    LinkedHashSet<String> words;
    CacheReplacementPolicy policy;
    int maxSize;
    int currentSize;

    public CacheManager(int maxSize, CacheReplacementPolicy p) {
        words = new LinkedHashSet<String>();
        policy = p;
        this.maxSize = maxSize;
        currentSize = 0;
    }

    public boolean query(String word) {
        return words.contains(word);
    }

    public void add(String word) {
        if (currentSize == maxSize) {
            String removedWord = policy.remove();
            policy.add(word);
            words.add(word);
            words.remove(removedWord);
        } else {
            words.add(word);
            policy.add(word);
            currentSize++;
        }

    }
}
