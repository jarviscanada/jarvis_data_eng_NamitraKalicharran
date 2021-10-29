package ca.jrvs.practice.codingChallenge;

import java.util.Map;

// ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-33d29d1cbb434d4e8d1120458ce2b976
public class compareMaps {
    /**
     * Big O: O(n)
     * Justification: the Map.equals API just makes one pass through each key
     *      in m1 and m2 to see if they're equal or if the values are equal.
     *      Value retrieval is O(1) so it doesn't add any additional time complexity
     */
    public <K, V> boolean solution(Map<K, V> m1, Map<K, V> m2) {
        return m1.equals(m2);
    }
}
