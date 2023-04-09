package class08;

public class Code01_Trie {

	// 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
	// 提交Trie类可以直接通过
	// 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
	// 这个链接上要求实现的功能和课上讲的完全一样
	// 该前缀树的路用数组实现
	class Trie {

		class Node {
			// 该节点通过了几次
			public int pass;
			// 有多少个字符串，以该节点结尾
			public int end;
			// 走向下级的路，如果node为空，则代表没路
			public Node[] nexts;

			public Node() {
				pass = 0;
				end = 0;
				nexts = new Node[26];
			}
		}

		private Node root;

		public Trie() {
			root = new Node();
		}

		// 给你一个字符串，添加str一次
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] str = word.toCharArray();
			Node node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
				path = str[i] - 'a'; // 由字符，对应成走向哪条路
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node();
				}
				// 往下跳
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		// 给你一个字符串，删除str一次
		public void erase(String word) {
			// 先确定字符串确定存在，再沿途删除
			if (countWordsEqualTo(word) != 0) {
				char[] chs = word.toCharArray();
				Node node = root;
				node.pass--;
				int path = 0;
				for (int i = 0; i < chs.length; i++) {
					path = chs[i] - 'a';
					// 如果发现pass==0，将后面的节点彻底断连，让jvm将没用的节点删除，节省空间
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		// 给你一个字符串str，查一下str出现了几次
		public int countWordsEqualTo(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				// 如果中间没路了，那么说明出现0次
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			// 走到最后，返回end
			return node.end;
		}

		// 给你一个字符串str，查一下有多少个字符串以str开头
		public int countWordsStartingWith(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

}
