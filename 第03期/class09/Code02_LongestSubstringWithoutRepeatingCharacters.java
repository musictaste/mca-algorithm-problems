package class09;

import java.util.Arrays;

// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
// 测试链接 : https://leetcode.cn/problems/longest-substring-without-repeating-characters/
public class Code02_LongestSubstringWithoutRepeatingCharacters {

	// 滑动窗口
	// 有点动态规划的意思了
	// 两种可能性：
	// 1.dp[i-1]:i-1位置，往左能推的最远距离，也就是i-1位置不重复的最长子串的长度
	// 2.i位置的数，上次出现的位置
	// 两种可能性取最小值，也就是离你最近的那种情况
	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		// 字符上次出现的位置；
		int[] preIndex = new int[256];
		Arrays.fill(preIndex,-1);

		// 设置字符串0位置的信息，0位置字符上一次出现的位置是0；不重复的最长子串长度是1(ans=1)
		preIndex[str[0]] = 0;
		int N = str.length;
		int ans = 1;
		// 不需要dp信息，因为只记录上一位置的信息，不需要dp[]数组，优化为一个变量
		int pre = 1;
		for (int i = 1; i < N; i++) {
			// 可能性1：i位置字符上次出现的位置k，i-k就是不重复的最长子串长度
			// 可能性2：i-1位置的不重复最长长度+1
			pre = Math.min(i - preIndex[str[i]], pre + 1);
			// 更新ans信息
			ans = Math.max(ans, pre);
			// 更新当前字符上一次出现的位置信息
			preIndex[str[i]] = i;
		}
		return ans;
	}

	// 建议将课上的代码在这里补一下，那个更容易理解，上面的版本是优化了dp数据的版本

}
