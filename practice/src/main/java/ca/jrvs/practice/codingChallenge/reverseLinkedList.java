package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.codingChallenge.model.ListNode;

// Ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-c6fb81f2d39b4c14b9c55adf9e65fe57
public class reverseLinkedList {

    /**
     * Big-O: O(n)
     * Justification: This just makes one iteration over every node to
     *  make the reversals
     */
    public ListNode solution(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prevNode = head;
        
        if (head.next == null) {
            return head;
        }
        ListNode curNode = head.next;
        
        if (curNode.next == null) {
            curNode.next = prevNode;
            prevNode.next = null;
            return curNode;
        }
        ListNode nextNode = curNode.next;
        
        prevNode.next = null;
        
        while (nextNode != null) {
            curNode.next = prevNode;
            
            prevNode = curNode;
            curNode = nextNode;
            nextNode = nextNode.next;
        }
        curNode.next = prevNode;
        
        return curNode;
    }    
}
