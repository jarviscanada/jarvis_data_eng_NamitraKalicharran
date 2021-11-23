package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MissingNumber {
    public int Solution(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        
        for (int i=1; i<=nums.length ; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }
}
