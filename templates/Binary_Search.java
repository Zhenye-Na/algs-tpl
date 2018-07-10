/**
 *  Binary Search template
 *  @param nums an ineteger arrray sorted in ascending order.
 *  @param target an integer
 *  @return position of the integer in the array
 */


public class Solution {

    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;

            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (target == nums[start]) {
            return start;
        }

        if (target == nums[end]) {
            return end;
        }

        return -1;

    }
}
