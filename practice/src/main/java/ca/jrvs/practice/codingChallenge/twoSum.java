package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

// ticket: https://www.notion.so/jarvisdev/Two-Sum-0fec56793b3f4cc88bf5b048b13cc49d
class twoSum {
    public int[] solution(int[] nums, int target) {
        // Allows for instant retrival of values
        HashMap<Integer, Integer> vals = new HashMap<Integer, Integer>();

        Integer diff;
        Integer second_id;

        /**
         * Big-O: O(n)
         * Justification: We're making one pass through nums
         *    and each insertion into our vals HashMap happens at O(1)
         */
        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            second_id = vals.get(diff);

            if (second_id != null) {
                int[] result = {i, second_id};
                return result;
            } else {
                vals.put(nums[i], i);
            }
        }

        return null;
    }
}
