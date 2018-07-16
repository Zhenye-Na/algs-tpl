/**
 *  Remove Duplicates and Sorting in Array
 *      Two pointers
 */


public int[] removeDuplicate(int[] candidates) {
    Arrays.sort(candidates);

    int index = 0;
    int length = candidates.length;

    for (int i = 0; i < length; i++) {
        if (candidates[i] != candidates[index]) {
          candidates[++index] = candidates[i];
        }
    }

    int[] nums = new int[index + 1];

    for (int i = 0; i < index + 1; i++) {
        nums[i] = candidates[i];
    }

    return nums;
}
