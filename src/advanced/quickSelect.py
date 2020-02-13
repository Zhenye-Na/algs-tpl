"""
quickSelect 模板

考虑下面几个问题:

* 最坏时间复杂度?
* 为什么要选择 (left + right) / 2 作为 pivot
* 为什么 pivot 既不属于左边也不属于右边
* 为什么是 left <= right 而不是 left < right
"""

def quickSelect(nums, start, end):
    # template
    left, right = 0, len(nums) - 1
    pivot = nums[(start + end) // 2]

    # 注意是 left <= right
    while left <= right:
        while left <= right and nums[left] < pivot:
            left += 1
        while left <= right and nums[right] > pivot:
            right -= 1
        if left <= right:
            nums[left], nums[right] = nums[right], nums[left]
            left, right = left + 1, right + 1
