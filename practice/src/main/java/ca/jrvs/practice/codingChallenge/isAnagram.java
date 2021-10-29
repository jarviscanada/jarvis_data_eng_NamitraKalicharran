package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;


// ticket: https://www.notion.so/jarvisdev/Valid-Anagram-d5c6c3cb498a4e769e85bf8d29448b18
public class isAnagram {

    /**
     * Big O: O(n)
     * Justification: This only makes one iteration through
     *      the length of both s and t strings
     *      before making a HashMap comparison
     */
    public boolean solution(String s, String t){
        // Will store (letter: count) for s and t
        HashMap<Character, Integer> sMap = new HashMap<Character, Integer>();
        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        
        for (Integer i=0; i < s.length(); i++) {
            if (sMap.get(s.charAt(i)) == null) {
                sMap.put(s.charAt(i), 1);
            } else {
                int s_new_val = sMap.get(s.charAt(i)) + 1;
                sMap.put(s.charAt(i), s_new_val);
            }
        }
            
        for (Integer i=0; i < t.length(); i++) {
            if (tMap.get(t.charAt(i)) == null) {
                tMap.put(t.charAt(i), 1);
            } else {
                int t_new_val = tMap.get(t.charAt(i)) + 1;
                tMap.put(t.charAt(i), t_new_val);
            }
        }
        return sMap.equals(tMap);
    }
}
