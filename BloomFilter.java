package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;

public class BloomFilter {
    private final ArrayList<MessageDigest> params = new ArrayList<>();
    private final BitSet bytes;
    int size;

    public BloomFilter(int s, String... algs) {
        size = s;
        bytes = new BitSet(s);
        for(String a : algs){
            try {
                params.add(MessageDigest.getInstance(a));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(String s) {
        byte[] bts;
        for (MessageDigest p : params) {
                bts = p.digest(s.getBytes());
                BigInteger a = new BigInteger(bts);
                int test = Math.abs(a.intValue() % bytes.size());
                bytes.set(test);
        }
    }

    public boolean contains(String s) {
        byte[] bts;
        for (MessageDigest p : params) {
                bts = p.digest(s.getBytes());
                BigInteger a = new BigInteger(bts);
                int test = Math.abs(a.intValue() % bytes.size());
                if (!bytes.get(test)) {
                    return false;
                }
        }
        return true;
    }

    @Override
    public String toString() {
        /*
         * Appends the string representation of the long argument to this sequence.
         * The argument is converted to a string as if by the method String.valueOf, and
         * the characters of that string are then appended to this sequence.
         * Parameters:
         * lng - a long.
         * Returns:
         * a reference to this object.
         */
        StringBuilder bitArray = new StringBuilder();
        for (int i = 0; i < bytes.length(); i++) {
            if (!bytes.get(i)) {
                bitArray.append("0");
            } else {
                bitArray.append("1");
            }
        }
        return bitArray.toString();
    }
}
