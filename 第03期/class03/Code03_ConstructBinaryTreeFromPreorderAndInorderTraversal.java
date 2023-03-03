package class03;

import java.util.HashMap;

// 根据先序遍历和中序遍历，构造二叉树
// 给定两个整数数组 preorder 和 inorder
// 其中 preorder 是二叉树的先序遍历
// inorder 是同一棵树的中序遍历
// 请构造二叉树并返回其根节点。
// 测试链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Code03_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	// 提交时不提交这个类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int v) {
			val = v;
		}
	}

	// 提交如下的方法
	// pre和in真的是某棵二叉树的先序和中序数组
	// 原始二叉树里，无重复值
	public static TreeNode buildTree(int[] pre, int[] in) {
		// 根据中序遍历数组生成一个map，<节点值、中序数组中的位置>,目的：通过先序的头结点，找到中序数组头结点的位置，减少每次都需要遍历中序数组
		HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			valueIndexMap.put(in[i], i);
		}
		return process(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
	}

	// pre[pl....pr]
	// in [il....ir]
	// 子树！
	public static TreeNode process(int[] pre, int pl, int pr, int[] in, int il, int ir, HashMap<Integer, Integer> valueIndexMap) {
		// pre[pl...pr] pre数组的第一个节点就是头结点
		TreeNode head = new TreeNode(pre[pl]);
		// pre: [ 7 3 4 5 6 8 ]
		//        4 5 6 7 8 9
		//
		// in: [ 3 4 7 6 5 8 ]
		//       2 3 4 5 6 7
		int headIndex = valueIndexMap.get(pre[pl]);
		int leftSize = headIndex - il; // 2
		int rightSize = ir - headIndex; // 3
		if (leftSize > 0) {
			head.left = process(pre, pl + 1, pl + leftSize, in, il, il + leftSize - 1, valueIndexMap);
		}
		if (rightSize > 0) {
			head.right = process(pre, pr - rightSize + 1, pr, in, ir - rightSize + 1, ir, valueIndexMap);
		}
		return head;
	}

}
