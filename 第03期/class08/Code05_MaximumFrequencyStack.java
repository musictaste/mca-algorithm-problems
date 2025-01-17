package class08;

import java.util.ArrayList;
import java.util.HashMap;

// 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
// 实现 FreqStack 类:
// FreqStack() 构造一个空的堆栈。
// void push(int val) 将一个整数 val 压入栈顶。
// int pop() 删除并返回堆栈中出现频率最高的元素。
// 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
// 测试链接 : https://leetcode.cn/problems/maximum-frequency-stack/
public class Code05_MaximumFrequencyStack {

	class FreqStack {

		// 出现的最大次数
		private int topTimes;
		// 每层节点
		// HashMap -> 可以替换为 ArrayList，ArrayList更快一些
		private HashMap<Integer, ArrayList<Integer>>
		cntValues = new HashMap<>();
		
		
		// 每一个数出现了几次
		private HashMap<Integer, Integer> valueTopTime = new HashMap<>();

		public void push(int val) {
			// 当前数词频+1
			valueTopTime.put(val, valueTopTime.getOrDefault(val, 0) + 1);
			// 当前数是什么词频
			int curTopTimes = valueTopTime.get(val);
			// 没有当前层，建起这一层
			if (!cntValues.containsKey(curTopTimes)) {
				cntValues.put(curTopTimes, new ArrayList<>());
			}
			// 建完后的层，将当前词频加入到列表中去
			ArrayList<Integer> curTimeValues = cntValues.get(curTopTimes);
			curTimeValues.add(val);
			// 更新最大次数
			topTimes = Math.max(topTimes, curTopTimes);
		}

		public int pop() {
			// 最大词频的那一层的链表(动态数组)
			ArrayList<Integer> topTimeValues = cntValues.get(topTimes);
			// 最大词频的层移除节点
			int ans = topTimeValues.remove(topTimeValues.size() - 1);
			// 如果最大词频的层变空，需要移除最大层，并更新出现的最大次数信息
			if (topTimeValues.size() == 0) {
				cntValues.remove(topTimes--);
			}
			// 更新词频表，如果元素出现次数为0，需要移除元素的记录
			int times = valueTopTime.get(ans);
			if (times == 1) {
				valueTopTime.remove(ans);
			} else {
				valueTopTime.put(ans, times - 1);
			}
			return ans;
		}
	}

}
