# 必须掌握的3中排序算法

## Quick Sort

```python
import random

class Solution:
    """
    @param A: an integer array
    @return: nothing
    """
    def sortIntegers2(self, A):
        # write your code here
        if not A:
            return A
        self.quickSort(A, 0, len(A) - 1)


    def quickSort(self, A, start, end):
        if start >= end:
            return

        pivotIndex = random.randint(start, end)
        pivot = A[pivotIndex]

        left, right = start, end

        while left <= right:

            while left <= right and A[left] < pivot:
                left += 1

            while left <= right and A[right] > pivot:
                right -= 1

            if left <= right:
                A[left], A[right] = A[right], A[left]

                left  += 1
                right -= 1

        self.quickSort(A, start, right)
        self.quickSort(A, left, end)

```


## Merge Sort

```python
class Solution:
    """
    @param A: an integer array
    @return: nothing
    """
    def sortIntegers2(self, A):
        # write your code here
        tmp = [0] * len(A)
        self.mergeSort(A, tmp, 0, len(A)-1)


    def mergeSort(self, A, tmp, start, end):
        # base case
        if start >= end:
            return

        mid = (end - start) / 2 + start

        # Divide
        self.mergeSort(A, tmp, start, mid)
        self.mergeSort(A, tmp, mid + 1, end)


        # Conquer / Merge
        idx, leftStart, rightStart = start, start, mid + 1

        while leftStart <= mid and rightStart <= end:
            if A[leftStart] <= A[rightStart]:
                tmp[idx] = A[leftStart]
                leftStart += 1
            else:
                tmp[idx] = A[rightStart]
                rightStart += 1
            idx += 1

        while leftStart <= mid:
            tmp[idx] = A[leftStart]
            idx += 1
            leftStart += 1


        while rightStart <= end:
            tmp[idx] = A[rightStart]
            idx += 1
            rightStart += 1


        for i in range(start, end+1):
            A[i] = tmp[i]
```


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
        for i in range(n, -1, -1):
            self.heapify(A, n, i)

        # extract elements at the root
        for i in range(n - 1, 0, -1):

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
