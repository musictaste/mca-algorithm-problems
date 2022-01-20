package mca_03_about_experience;

// 测试链接 : https://leetcode.com/problems/4-keys-keyboard/
public class Code02_4KeysKeyboard {

	// 可以证明：
	// 来到i的时候，包括i在内最多有连续4次粘贴行为
	// 不可能更多，如果有连续5次粘贴，一定就不再是最优解
	// 假设开始时，A的数量为S，看如下的变化过程，我们称这是行为一：
	// 开始  全选  复制(粘贴板S个A)  粘贴  粘贴  粘贴  粘贴  粘贴
	// S      S         S         2*S  3*S   4*S  5*S  6*S
	// 但是，注意看如下的行为二：
	// 开始  全选  复制(粘贴板S个A)  粘贴  全选  复制(粘贴板2S个A) 粘贴   粘贴
	// S      S         S         2*S  2*S        2*S        4*S   6*S
	// 行为一，经历8步，最后是6*S个A
	// 行为二，经历8步，最后是6*S个A
	// 但是行为二在粘贴板上有2S个A，而行为一在粘贴板上有S个A
	// 所以行为一没有行为二优
	// 以此说明：来到i的时候，包括i在内最多有连续4次粘贴行为
	// 那么就尝试：连续1次、连续2次、连续3次、连续4次粘贴行为即可
	public static int maxA(int n) {
		int[] dp = new int[n + 1];
		for (int i = 1; i <= 6 && i <= n; i++) {
			dp[i] = i;
		}
		for (int i = 7; i <= n; i++) {
			dp[i] = Math.max(
					Math.max(dp[i - 3] * 2, dp[i - 4] * 3),
					Math.max(dp[i - 5] * 4, dp[i - 6] * 5));
		}
		return dp[n];
	}

}
