package ca.jrvs.practice.codingChallenge.model;

public class ListNode {
    int val;
    public ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}