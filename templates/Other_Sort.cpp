#include <iostream>
#include <vector>
#include <stack>
using namespace std;

/*
Insertion Sort, Merge Sort, Quick Sort, Heap Sort
注意：以下所有数组的参数范围都是左闭右开
*/

void exchange(int &a, int &b)
{
	int temp = a;
	a = b;
	b = temp;
}

void printVec(const vector<int> &vec)
{
	for (int i = 0; i < vec.size(); ++i)
		cout << vec[i] << " ";
	cout << endl;
}

/*-------------------------------------
Insertion Sort
--------------------------------------*/
void insertionSort(vector<int> &vec)
{
	if (vec.empty()) return;
	for (int i = 1; i < vec.size(); ++i)
	{
		int key = vec[i];
		int j = i - 1;
		while (j >= 0 && vec[j] > key)
		{
			vec[j + 1] = vec[j];
			--j;
		}
		vec[j + 1] = key;
	}
}

/*--------------------------------------
Merge Sort
---------------------------------------*/
void merge(vector<int> &vec, int p, int q, int r)
{
	vector<int> temp;
	int i = p;
	int j = q;
	while (i < q && j < r)
	{
		if (vec[i] < vec[j])
		{
			temp.push_back(vec[i]);
			++i;
		}
		else
		{
			temp.push_back(vec[j]);
			++j;
		}
	}
	while (i < q)
	{
		temp.push_back(vec[i]);
		++i;
	}
	while (j < r)
	{
		temp.push_back(vec[j]);
		++j;
	}
	for (int k = 0; k < temp.size(); ++k)
	{
		vec[p + k] = temp[k];
	}
}

void mergeSort(vector<int> &vec, int p, int r)
{
	if (p < r - 1)
	{
		int mid = (p + r) / 2;
		mergeSort(vec, p, mid);
		mergeSort(vec, mid, r);
		merge(vec, p, mid, r);
	}
}

void mergeSort(vector<int> &vec)
{
	mergeSort(vec, 0, vec.size());
}

/*--------------------------------------
Quick Sort
---------------------------------------*/
//#define REC
int partition(vector<int> &vec, int p, int r)
{
	int pivot = vec[p];
	int i = p;
	for (int j = p + 1; j < r; ++j)
	{
		if (vec[j] < pivot)
		{
			exchange(vec[j], vec[i + 1]);
			++i;
		}
	}
	exchange(vec[p], vec[i]);
	return i;
}

int binary_partition(vector<int> &vec, int p, int r)
{
	int pivot = vec[p];
	int left = p;
	int right = r - 1;
	while (left < right)
	{
		while (left < right && vec[right] > pivot) --right;
		vec[left] = vec[right];
		while (left < right && vec[left] < pivot) ++left;
		vec[right] = vec[left];
	}
	vec[left] = pivot;
	return left;
}

void quickSort(vector<int> &vec, int p, int r)
{
#ifdef REC
	if (p < r - 1)
	{
		int mid = partition(vec, p, r);
		quickSort(vec, p, mid);
		quickSort(vec, mid + 1, r);
	}
#else
	if (p >= r - 1) return;
	stack<int> sta;
	sta.push(p);
	sta.push(r);
	while (!sta.empty())
	{
		int right = sta.top();
		sta.pop();
		int left = sta.top();
		sta.pop();
		int mid = partition(vec, left, right);
		if (left < mid - 1)
		{
			sta.push(left);
			sta.push(mid);
		}
		if (mid + 1 < right - 1)
		{
			sta.push(mid + 1);
			sta.push(right);
		}
	}
#endif
}

void quickSort(vector<int> &vec)
{
	quickSort(vec, 0, vec.size());
}

/*--------------------------------------
Heap Sort
---------------------------------------*/
inline int left(int x)
{
	return x * 2 + 1;
}

inline int right(int x)
{
	return x * 2 + 2;
}

// big root heap
void maxHeapify(vector<int> &vec, int i, int heapSize)
{
	int pLeft = left(i);
	int pRight = right(i);
	int pLargest = i;
	if (pLeft < heapSize && vec[pLargest] < vec[pLeft])
		pLargest = pLeft;
	if (pRight < heapSize && vec[pLargest] < vec[pRight])
		pLargest = pRight;
	if (pLargest != i)
	{
		exchange(vec[pLargest], vec[i]);
		maxHeapify(vec, pLargest, heapSize);
	}
}

void heapSort(vector<int> &vec, int p, int r)
{
	int heapSize = r - p;
	// build heap
	for (int i = p + (r - p) / 2; i >= 0; --i)
		maxHeapify(vec, i, heapSize);
	while (heapSize > 0)
	{
		exchange(vec[p], vec[heapSize - 1]);
		--heapSize;
		maxHeapify(vec, p, heapSize);
	}
}

void heapSort(vector<int> &vec)
{
	heapSort(vec, 0, vec.size());
}

int main()
{
	vector<int> vec({ 3, 1, 5, 2, 7, 0, 6, 4 });

	insertionSort(vec);
	//mergeSort(vec);
	//quickSort(vec);
	//heapSort(vec);

	printVec(vec);

	system("pause");
	return 0;
}
