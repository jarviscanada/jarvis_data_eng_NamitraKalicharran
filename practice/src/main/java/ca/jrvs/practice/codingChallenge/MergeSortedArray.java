package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;

public class MergeSortedArray {
    public void solution (int[] nums1, int m, int[] nums2, int n) {        
        ArrayList<Integer> result = new ArrayList<Integer>();
        int nId = 0;
        int mId = 0;
        
        // mId is our index while we iterate over nums1
        // nId is our index while we iterate over nums2
        while (nId < n && mId < m) {
            if (nums1[mId] < nums2[nId]) {
                result.add(nums1[mId]);
                mId++;
            } else if (nums1[mId] > nums2[nId]) {
                result.add(nums2[nId]);
                nId++;
            } else if (nums1[mId] == nums2[nId]) {
                result.add(nums1[mId]);
                result.add(nums2[nId]);
                mId++;
                nId++;
            }
        }
        
        // Either mId or nId have reached the end of their array
        if (mId == m) {
            for (int i=nId; i<n; i++) {
                result.add(nums2[i]);
            }
        } else if (nId == n) {
            for (int i=mId; i<m; i++) {
                result.add(nums1[i]);
            }
        }
        
        // mutate nums1 for result
        // overwrites values at each index
        for (int j=0; j<n+m; j++) {
            int val = result.get(j);
            nums1[j] = val;
        }
    }
}
