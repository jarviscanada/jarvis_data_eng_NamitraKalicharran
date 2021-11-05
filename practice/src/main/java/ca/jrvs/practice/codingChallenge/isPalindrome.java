package ca.jrvs.practice.codingChallenge;

// ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-9bcfdf4f98574436b10632e60551e592
public class isPalindrome {
    /**
     * Big O: O(n)
     * Justification: The most expensive operation is to
     *      reverse the string. The fastest this can be achieved
     *      is by visiting each character once.
     */
    public boolean solution(String str) {
        str = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(str).reverse().toString();
        
        if (str.equals(reversed)) {
            return true;
        } else {
            return false;
        }
    }
}
