package class05;

import java.util.HashMap;

// 测试链接 : https://leetcode.cn/problems/lru-cache/
// 提交以下这个类
public class Code05_LRUCache {

    public static class Node {
        public int key;
        public int val;
        public Node last;
        public Node next;

        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    public static class DoubleLinkedList {
        private Node head;
        private Node tail;

        public DoubleLinkedList() {
            head = null;
            tail = null;
        }

        // 插入节点到链表尾部
		// 如果节点为空，直接返回
		// 如果头结点为空，头尾节点就是该节点
		// 如果头结点不为空，添加到链表尾部
		public void addNode(Node newNode) {
            if (newNode == null) {
                return;
            }
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail; // 向前的指针
                tail = newNode;
            }
        }

        // 移动节点到链表尾部：当前节点前后节点重连，再将该节点添加到链表尾部
		// 如果是尾节点，直接返回
		// 如果是头节点，头结点的下个节点是头结点
		// 如果是中间节点，前后节点重连
		public void moveNodeToTail(Node x) {
            if (tail == x) {
                return;
            }
            if (head == x) { // x在头部
                head = x.next;
                head.last = null;
            } else {
                x.last.next = x.next;
                x.next.last = x.last;
            }
            x.last = tail;
            x.next = null;
            tail.next = x;
            tail = x;
        }

        // 删除链表头部节点
		// 如果头节点是尾结点，那么设置头尾节点都为空
		// 否则头节点的下个节点为头节点；当前节点前后指针断连
		public Node removeHead() {
            if (head == null) {
                return null;
            }
            Node ans = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = ans.next;
                ans.next = null;
                head.last = null;
            }
            return ans;
        }

    }

    //  A -> Node 根据node直接知道该节点在链表中的位置，因为存放的是该节点的内存地址
    private HashMap<Integer, Node> keyNodeMap;
    // Node 串成的双向链表
    private DoubleLinkedList nodeList;
    // 这个结构的容量!
    private final int capacity;

    public Code05_LRUCache(int cap) {
        keyNodeMap = new HashMap<>();
        nodeList = new DoubleLinkedList();
        capacity = cap;
    }

	// get查询数据时，哈希表中是否有对应的记录，
	// 如果没有，返回-1
	// 如果有，将该记录移动到链表尾部
	public int get(int key) {
        // 哈希表是否有该记录
        if (keyNodeMap.containsKey(key)) {
            Node ans = keyNodeMap.get(key);
            // 移动节点到链表尾部
            nodeList.moveNodeToTail(ans);
            return ans.val;
        }
        return -1;
    }

	// put插入数据时
	// 如果数据已经存在，将该记录移动到链表尾部
	// 如果内存已满，删除最早的记录(链表头部的节点)，然后添加记录到链表尾部
	// 如果内存没满，添加记录到链表尾部
	public void put(int key, int value) {
        if (keyNodeMap.containsKey(key)) {
            // 如果已经有该记录，该节点移动到尾部
            Node node = keyNodeMap.get(key);
            node.val = value;
            nodeList.moveNodeToTail(node);
        } else {
            // 之前没有A的记录，新增!
            // 1) 结构满了
            // 2) 结构没有满
            if (keyNodeMap.size() == capacity) {
                removeMostUnusedCache();
            }
            Node newNode = new Node(key, value);
            keyNodeMap.put(key, newNode);
            // 链表尾部添加节点
            nodeList.addNode(newNode);
        }
    }

    // 移除最没用的记录，也就是移除链表头节点
	// 删除链表头节点 + 哈希表中删除该记录
    private void removeMostUnusedCache() {
        Node removeNode = nodeList.removeHead();
        keyNodeMap.remove(removeNode.key);
    }

}

