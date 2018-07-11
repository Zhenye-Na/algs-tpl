#include <iostream>
#include <stack>
using namespace std;

// 把数组分为两部分，轴temp左边的部分都小于轴右边的部分
int partition(int* nums, int low, int high)
{
	int temp = nums[low];		//任选元素作为轴，这里选首元素
	while (low < high)
	{
		while (low < high && nums[high] >= temp) high--;
		nums[low] = nums[high];
		while (low < high && nums[low] <= temp) low++;
		nums[high] = nums[low];
	}
	//此时low == high
	nums[low] = temp;
	return low;
}

// 递归
void quickSort(int* nums, int low, int high)
{
	if (low < high)
	{
		int mid = partition(nums, low, high);
		quickSort(nums, low, mid - 1);
		quickSort(nums, mid + 1, high);
	}
}

// 非递归（利用栈）
// 其实就是用栈保存每一个待排序子串的首尾元素下标，
// 下一次while循环时取出这个范围，对这段子序列进行partition操作
void quickSort(int* nums, int low, int high)
{
	if (low >= high) return;
	stack<int> sta;
	sta.push(low);
	sta.push(high);
	while (!sta.empty())
	{
		int j = sta.top();
		sta.pop();
		int i = sta.top();
		sta.pop();
		int mid = partition(nums, i, j);
		if (i < mid - 1)
		{
			sta.push(i);
			sta.push(mid - 1);
		}
		if (mid + 1 < j)
		{
			sta.push(mid + 1);
			sta.push(j);
		}
	}
}

int main()
{
	int nums[] = { 4, 2, 3, 9, 5, 6, 8, 7, 1, 0 };
	quickSort(nums, 0, 9);

	for (int i = 0; i < 10; ++i)
		cout << nums[i] << " ";
	cout << endl;

	system("pause");
	return 0;
}
