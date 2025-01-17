package class06;

// n对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手
// 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的ID
// 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)
// 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起
// 每次交换可选择任意两人，让他们站起来交换座位
// 测试链接 : https://leetcode.cn/problems/couples-holding-hands/
public class Code02_CouplesHoldingHands {

	public int minSwapsCouples(int[] row) {
		// 数字几个  2 * n   8   4   16  8
		// 人编号：0 1   2 3  4 5    v
		// 组号：  0     1    2     v/2
		// n : 人数
		// 情侣组  : n / 2
		int n = row.length;
		UnionFind uf = new UnionFind(n / 2);
		for (int i = 0; i < n; i += 2) {
			// 左 右 左 右
			// 0  1  2 3  4
			uf.union(row[i] / 2, row[i + 1] / 2);
		}
		// 有多少组情侣 - 并查集的集合个数
		return n / 2 - uf.sets();
	}

	public static class UnionFind {
		public int[] father;
		public int[] size;
		public int[] help;
		// 当前有多少个集合？
		public int sets;

		public UnionFind(int n) {
			father = new int[n];
			size = new int[n];
			help = new int[n];
			for (int i = 0; i < n; i++) {
				father[i] = i;
				size[i] = 1;
			}
			sets = n;
		}

		private int find(int i) {
			int hi = 0;
			while (i != father[i]) {
				help[hi++] = i;
				i = father[i];
			}
			while (hi != 0) {
				father[help[--hi]] = i;
			}
			return i;
		}

		public void union(int i, int j) {
			int fi = find(i);
			int fj = find(j);
			if (fi != fj) {
				if (size[fi] >= size[fj]) {
					father[fj] = fi;
					size[fi] += size[fj];
				} else {
					father[fi] = fj;
					size[fj] += size[fi];
				}
				// 集合个数--
				sets--;
			}
		}

		public int sets() {
			return sets;
		}

	}

}
