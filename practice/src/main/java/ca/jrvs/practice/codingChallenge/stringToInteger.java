package ca.jrvs.practice.codingChallenge;

// Ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-511c2d1d879049f5bd0b89075e4880bd
public class stringToInteger {
    /**
     * Big-O: O(n)
     * Justification: This program just iterates through the entire string once
     */
    public int solution(String s) {
        char[] charArr = s.trim().toCharArray();
        boolean isNegative = false;
        int length = charArr.length;
        int i = 0;

        if (i>=length) {
            return 0;
        } else if (charArr[i] == '-') {
            isNegative = true;
            i++;
        } else if (charArr[i] == '+') {
            i++;
        } else if (!Character.isDigit(charArr[i])) {
            return 0;
        }

        Long sum = Long.valueOf(0);
        if (isNegative) {
            while (i < length && Character.isDigit(charArr[i])) {
                sum = (sum * 10L) - Long.valueOf(Character.getNumericValue(charArr[i]));

                if (sum < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
                i++;
            }

        } else {
            while (i < length && Character.isDigit(charArr[i])) {
                sum = (sum * 10L) + Long.valueOf(Character.getNumericValue(charArr[i]));

                if (sum > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                i++;
            }
        }
        return sum.intValue();
    }
}
