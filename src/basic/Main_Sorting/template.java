/**
* 本参考程序来自九章算法，由 @Su 提供。版权所有，转发请注明出处。
* - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
* - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
* - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
* - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
*/

public class Solution {
    /*
     * @param A: an integer array
     * @return:
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        /* merge sort */
        // mergeSort(A);

        /* quick sort */
        // quickSort(A);

        /* heap sort */
        // heapSort(A);
    }


    /**
     * Merge Sort.
     */
    public void mergeSort(int[] nums) {
        int[] tmp = new int[nums.length];
        mergeSort(nums, tmp, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int[] tmp, int start, int end) {
        if (start >= end) {
            return;
        }

        // divide
        int mid = start + (end - start) / 2;
        mergeSort(nums, tmp, start, mid);
        mergeSort(nums, tmp, mid + 1, end);

        // merge
        int index      = start;
        int leftStart  = start;
        int rightStart = mid + 1;

        while (leftStart <= mid && rightStart <= end) {
            if (nums[leftStart] <= nums[rightStart]) {
                tmp[index++] = nums[leftStart++];
            } else {
                tmp[index++] = nums[rightStart++];
            }
        }

        while (leftStart <= mid) {
            tmp[index++] = nums[leftStart++];
        }

        while (rightStart <= end) {
            tmp[index++] = nums[rightStart++];
        }

        for (int i = start; i <= end; i++) {
            nums[i] = tmp[i];
        }
    }


    /**
     * Quick sort.
     */
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int left  = start;
        int right = end;
        int mid   = start + (end - start) / 2;
        int pivot = nums[mid];

        // overview sorted
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] > pivot) {
                right--;
            }

            // swap left >= p with right <= p to make left <= p right >= p
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }

        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }


    /**
     * Heap sort.
     */
    public void heapSort(int[] nums) {
        // build max heap: for each root node bottom to top and right to left
        for (int i = nums.length / 2; i >= 0; i--) {
            maxHeapify(nums, i, nums.length - 1);
        }

        // swap first and adjust the new root to right position
        for (int i = nums.length - 1; i > 0; i--) {
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            // after each iteration, largest goes to ith, next end at i - 1
            maxHeapify(nums, 0, i - 1);
        }
    }

    private void maxHeapify(int[] nums, int start, int end) {
        int parent = start;

        // top to bottom
        while (parent <= end) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            int child = -1;

            if (left <= end && right <= end) {
                // large value as swap candidate
                child = (nums[left] >= nums[right] ? left : right);
            } else if (left <= end) {
                child = left;
            } else {
                return;
            }

            // max heap root >= left && root >= right
            if (nums[parent] >= nums[child]) {
                return;
            }

            // swap
            int tmp = nums[parent];
            nums[parent] = nums[child];
            nums[child] = tmp;

            // update parent index
            parent = child;
        }
    }
}
