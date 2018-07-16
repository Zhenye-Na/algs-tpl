/**
总结：什么时候用回溯法?

  如果题目要求求出所有满足条件的解，一般来说是用回溯法，记住回溯法的模板，对不同的题目只需要修改
这个条件即可。
  回溯法的本质是在问题的解空间树上做深度优先搜索（DFS）。这节课主要讲了四个排列组合的问题，分别
是子集，带重复元素的子集，全排列，带重复元素的全排列。
*/


/**
1 Combination
1.1 Subsets
Problem: Given a set of distinct integers, S, return all possible subsets

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.

For example:
  If S = [1,2,3], a solution is: [[3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], []]

Solution:
  Use recursion to construct the solution.
  Tip: Use a tree structure to consider the recursion, e.g.:

  {}
  {1} {2} {3} {4}
  {1,2}{1,3}{1,4} {2,3}{2,4} {3,4} ...

*/


/**

写的时候要注意递归的三要素：
  1. 递归的定义。这里的helper函数定义为：将所有以当前cur子集开头的所有子集（包含当前cur）加入
     到结果res中。
  2. 递归的出口。即满足什么条件保存答案。这里对每个遍历得到的cur都保存答案。
  3. 递归的拆解。拆解为更小规模的问题。

*/


public class Solution {
    // Test cases:
    // [1,2,3,4,5,6]
    // []
    // null
    // [1]
    // [1,2,...,1000]
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.length == 0) {
            return result;
        }
        Arrays.sort(S);  // Sorting because result should be in non-descending order
        ArrayList<Integer> path = new ArrayList<Integer>();
        subsetsHelper(S, 0, path, result);
        return result;
    }

    private void subsetsHelper(int[] S,
                               int pos,
                               ArrayList<Integer> path,
                               ArrayList<ArrayList<Integer>> result) {

        result.add(new ArrayList<Integer>(path));

        for (int i = pos; i < S.length; i++) {
            path.add(S[i]);
            subsetsHelper(S, i + 1, path, result);
            //<Attention>:
            // shall not set "pos + 1" as the second argument,
            // because the logic is: after add the ith element,
            // you can only add the elements which has index greater than i.
            path.remove(path.size() - 1);
        }
    }
}






/**

1.2 Subsets II (Unique Subsets)
Problem: Given a collection of integers that might contain duplicates, S, return
         all possible subsets.

Note:
  Elements in a subset must be in non-descending order.
  The solution set must not contain duplicate subsets.

For example:
  If S = [1,2,2], a solution is:[[2], [1], [1,2,2], [2,2], [1,2], []]

Solution:

(1) Use the Subsets template to solve this problem.
(2) The input is a collection, which will allow duplicate int, but the output
    should be unique, which means each int is allow to appear no more than once.
    Therefore, the second problem for us is to remove the duplicated ints.
(3) In which cases, there would be duplicated ints if we use subsets template to
    solve this problem?

e.g: input: [1, 2(first), 2(second), 2(third)]
     output: [1, 2(first)] and [1, 2(second)], are "duplicated"
             [1, 2(first), 2(second)] and [1, 2(second), 2(third)], are "duplicated"

Then, we got the pattern to prevent duplicated cases in out output,
that is: When we try to get the next element from the sorted array and insert it
into our result set, we should first check whether this element is duplicated.
If it is, it will not be inserted, if there is an element equal to it and was
not inserted into the result set.

(4) To implement this pattern, when we insert duplicated elements, our insertion
should start from the first duplicated element in the sorted array, which means
we will only allow [1, 2(first)], [1, 2(first), 2(second)] not allow cases like:
[1, 2(second)].

*/


public class Solution {
    // Test cases:
    // []
    // null
    // [1,2,3]
    // [1,2,2,3]
    // [1]
    // [2,2]
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0) {
            return result;
        }
        Arrays.sort(num);
        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(num, result, path, 0);
        return result;
    }

    private void helper(int[] num, ArrayList<ArrayList<Integer>> result,
    ArrayList<Integer> path, int start) {
        result.add(new ArrayList<Integer>(path));

        for (int i = start; i < num.length; i++) {
            if (i != start && num[i] == num[i - 1]) {
                continue;
            }
            path.add(num[i]);
            helper(num, result, path, i + 1);    // calling itself: recursion
            path.remove(path.size() - 1);        // change the status back to how it was: backtracking
}





/**

2. Permutations

Description
  Given a list of numbers, return all possible permutations.
  You can assume that there is no duplicate numbers in the list.

Example
  For nums = [1,2,3], the permutations are:

  [
    [1,2,3],
    [1,3,2],
    [2,1,3],
    [2,3,1],
    [3,1,2],
    [3,2,1]
  ]

Challenge
  Do it without recursion.

*/



/**
* 本参考程序来自九章算法，由 @令狐冲 提供。版权所有，转发请注明出处。
* - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
* - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
* - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
* - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
*/

public class Solution {
    /*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }

        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);

        return results;
    }

    private void dfs(int[] nums,
                     boolean[] visited,
                     List<Integer> permutation,
                     List<List<Integer>> results) {
        if (nums.length == permutation.size()) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}





/**
* 本参考程序来自九章算法，由 @九章算法 提供。版权所有，转发请注明出处。
* - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
* - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
* - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
* - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
*/

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> results = new ArrayList<>();
         if (nums == null) {
             return results;
         }

         if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
         }

         List<Integer> permutation = new ArrayList<Integer>();
         Set<Integer> set = new HashSet<>();
         helper(nums, permutation, set, results);

         return results;
    }

    // 1. 找到所有以permutation 开头的排列
    public void helper(int[] nums,
                       List<Integer> permutation,
                       Set<Integer> set,
                       List<List<Integer>> results) {
        // 3. 递归的出口
        if (permutation.size() == nums.length) {
            results.add(new ArrayList<Integer>(permutation));
            return;
        }


        // [3] => [3,1], [3,2], [3,4] ...
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }

            permutation.add(nums[i]);
            set.add(nums[i]);
            helper(nums, permutation, set, results);
            set.remove(nums[i]);
            permutation.remove(permutation.size() - 1);
        }

    }
}



// Non-Recursion
class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> permutations
             = new ArrayList<List<Integer>>();
        if (nums == null) {

            return permutations;
        }

        if (nums.length == 0) {
            permutations.add(new ArrayList<Integer>());
            return permutations;
        }

        int n = nums.length;
        ArrayList<Integer> stack = new ArrayList<Integer>();

        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }

            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }

            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums[stack.get(i)]);
            }
            permutations.add(permutation);
        }

        return permutations;
    }
}
