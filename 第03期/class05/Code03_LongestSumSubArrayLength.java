package class05;

import java.util.HashMap;

// 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度
// 如果不存在任意一个符合要求的子数组，则返回 0
// 测试链接 : https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k/
public class Code03_LongestSumSubArrayLength {

	public int maxSubArrayLen(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key : 某个前缀和 value : 前缀和出现的最早位置
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 非常重要 ! 防止少算一些可能性
		// 提前塞一条记录，前缀和为0，出现的位置是-1
		map.put(0, -1);
		int ans = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			// sum : 0.....i 整体的和
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				// 0.......730 前缀和1000 k = 100
				// 0...17 前缀和900
				//         18..730 累加和是100
				// 长度 = i - 最早位置
				ans = Math.max(i - map.get(sum - k), ans);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
			// 如果出现，不要更新最早出现的位置下标
		}
		return ans;
	}

	// 不提前塞一条数据的解法
	public int maxSubArrayLen2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key : 某个前缀和 value : 最早位置
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 非常重要 ! 防止少算一些可能性；不提前塞一条数据的解法
//		map.put(0, -1);
		int ans = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			// 不提前塞记录，需要判断 前缀和是否等于k，符合的范围是0到i位置，数组长度i+1
			if (sum == k) {
				ans = Math.max(i + 1, ans);
			} else {
				if (map.containsKey(sum - k)) {
					ans = Math.max(i - map.get(sum - k), ans);
				}
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return ans;
	}

}
