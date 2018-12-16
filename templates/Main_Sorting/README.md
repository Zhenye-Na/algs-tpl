# 必须掌握的3中排序算法

## Quick Sort



## Merge Sort




## Heap Sort

Time Complexity

- Time complexity of heapify is `O(Logn)`.
- Time complexity of `createAndBuildHeap()` is `O(n)`.
- **overall time complexity** of Heap Sort is `O(nLogn)`.

- Heap Sort for decreasing order using `min heap`
- Heap Sort for increasing order using `max heap`


```python
class Solution:
    """
    @param A: an integer array
    @return: nothing
    """
    def sortIntegers2(self, A):
        # write your code here
        n = len(A)

        # create and build heap
        for i in xrange(n, -1, -1):
            self.heapify(A, n, i)

        # extract elements at the root
        for i in xrange(n - 1, 0, -1):

            # Swap
            A[i], A[0] = A[0], A[i]

            # Re-Heapify
            self.heapify(A, i, 0)


    def heapify(self, A, n, i):
        # Ascending order -> Max Heap
        largest = i
        left    = 2 * i + 1
        right   = 2 * i + 2


        if left < n and A[largest] < A[left]:
            largest = left

        if right < n and A[largest] < A[right]:
            largest = right

        # Swap root with left or right if needed
        if largest != i:

            # Swap
            A[i], A[largest] = A[largest], A[i]

            # Re-Heapify
            self.heapify(A, n, largest)
```
