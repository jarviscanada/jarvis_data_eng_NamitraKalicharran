package ca.jrvs.practice.codingChallenge;


// Ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-511c2d1d879049f5bd0b89075e4880bd
public class stringToInteger {
    public int solution(String s) {
        int i = 0;
        while (s.charAt(i) == ' ') {
            i += 1;
        }
        int result = Integer.parseInt(s.substring(i, s.length()-1));
        return result;

    }
}
