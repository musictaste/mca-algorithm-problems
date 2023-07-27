package class04;

// 高频：最长递增子序列算法
// 本题测试链接 : https://leetcode.cn/problems/longest-increasing-subsequence
public class Code03_LIS {

	// 时间复杂度：O(N^2)，因为操作过程是等差数列
	public static int common(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		// ...arr[0]
		dp[0] = 1;
		int maxLen = 1;
		for (int i = 1; i < n; i++) {
			int preLen = 0;
			// i位置之前所有的位置都遍历，得到之前情况的最长长度
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					preLen = Math.max(preLen, dp[j]);
				}
			}
			dp[i] = preLen + 1;
			maxLen = Math.max(maxLen, dp[i]);
		}
		return maxLen;
	}

	// 优化方案
	// 时间复杂度：O(N*logN)
	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] ends = new int[arr.length];
		// ends[i] : 所有长度为i+1的递增子序列，最小结尾！
		// ends是从0开始的，跟思路流程中做个转化
		// 0.....n-1
		// 0 1...
		ends[0] = arr[0];
		int max = 1;
		// ends填到了哪
		int right = 0;
		for (int i = 1; i < arr.length; i++) {
			int l = 0;
			int r = right;
			// 二分，知道ends数组中大于当前数字的ends数组中最左的位置
			while (l <= r) {
				int m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// l : 就是返回的下标
			// 下面两句，要么改写了ends数组某个位置的值，要么在ends数组中新增一个数字
			ends[l] = arr[i];
			right = Math.max(right, l);
			// 省掉了dp数组
			max = Math.max(max, l + 1);
		}
		return max;
		// 代码能不能变成right+1 ? 可以的
//		return right+1;
	}

	public static void main(String[] args) {
		int[] arr = {3,1,5,2,4,3,5,0};
		int res = lengthOfLIS(arr);
		System.out.println(res);
	}

}