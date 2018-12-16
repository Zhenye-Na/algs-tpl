# Python program for implementation of heap Sort
# https://www.geeksforgeeks.org/heap-sort/

# Heapify: similari to build max-heap, but assumes part of array is already Sorted
    # 1. Create max-heap
    # 2. Swap largest item with the last -> Remove largest item
    # 3. Place item in sorted partition



# To heapify subtree rooted at index i.
# n is size of heap
def heapify(arr, n, i):
    largest = i       # Initialize largest as root
    l = 2 * i + 1     # left  = 2 * i + 1
    r = 2 * i + 2     # right = 2 * i + 2

    # See if left child of root exists and is
    # greater than root
    if l < n and arr[i] < arr[l]:
        largest = l

    # See if right child of root exists and is
    # greater than root
    if r < n and arr[largest] < arr[r]:
        largest = r

    # Change root, if needed
    if largest != i:
        # swap nodes
        arr[i], arr[largest] = arr[largest], arr[i]

        # Heapify the root.
        heapify(arr, n, largest)


# The main function to sort an array of given size
def heapSort(arr):
    n = len(arr)

    # Build a maxheap.
    for i in range(n, -1, -1):
        heapify(arr, n, i)

    # One by one extract elements
    for i in range(n - 1, 0, -1):
        # swap first and last node
        arr[i], arr[0] = arr[0], arr[i]

        # create max heap on reduced array
        heapify(arr, i, 0)


# Driver code to test above
arr = [ 12, 11, 13, 5, 6, 7]
heapSort(arr)
n = len(arr)
print ("Sorted array is")
for i in range(n):
    print ("%d" %arr[i]),
# This code is contributed by Mohit Kumra








class Solution:
    """
    @param A: an integer array
    @return: nothing
    """
    def sortIntegers2(self, A):
        # write your code here
        n = len(A)

        for i in xrange(n, -1, -1):
            self.heapify(A, n, i)

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
